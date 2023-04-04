package businesskeywords.WiseSmokeTest;

import com.winSupply.core.Helper;
import com.winSupply.core.ReusableLib;
import com.winSupply.framework.Status;
import com.winSupply.framework.selenium.FrameworkDriver;
import commonkeywords.CommonActions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.*;
import supportLibraries.Utility_Functions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Capture extends ReusableLib {
    private final WebDriver webDriver;
    private final FrameworkDriver driver;
    private final CommonActions common;
    private final String url = "https://wisetest.winwholesale.com/wise?wrkstnid=&skin=wob";//"http://lcoservicedev.winwholesale.com/shipping-manifest-manager/#/ready-to-ship-planner/warehousing";
    public Capture(Helper helper) {
        super(helper);
        webDriver = helper.getGSDriver().getWebDriver();
        driver = helper.getGSDriver();
        common = new CommonActions(helper);
    }

    public void captureElements() {
        driver.get(url);
        waitForStopButtonClick();
        stopRecording();
    }
    public void waitForStopButtonClick() {
        String script = "const callback = arguments[arguments.length - 1];" +
                "const waitForStopRecording = () => {" +
                "  return new Promise((resolve) => {" +
                "    function messageListener(event) {" +
                "      if (event.origin !== window.location.origin) return;" +
                "      if (event.data.type === 'stopRecording') {" +
                "        console.log('stopRecording received');" +
                "        resolve(true);" +
                "        window.removeEventListener('message', messageListener);" +
                "      }" +
                "    }" +
                "    window.addEventListener('message', messageListener);" +
                "  });" +
                "};" +
                "waitForStopRecording().then(callback);";

        webDriver.manage().timeouts().setScriptTimeout(600, TimeUnit.SECONDS);
        ((JavascriptExecutor) webDriver).executeAsyncScript(script);
    }

    public void stopRecording() {

        int customTimeout = 30000;
        String capturedEventsJson = null;

        String script = "console.log('Listener added');" +
                "const callback = arguments[arguments.length - 1];" +
                "function messageListener(event) {" +
                "  if (event.origin !== window.location.origin) {" +
                "    console.log('Origin not matching');" +
                "    return;" +
                "  }" +
                "  console.log('before event.data.type');" +
                "  console.log('Received a message:', event.data.type);" +
                "  if (event.data.type === 'userActions') {" +
                "    console.log('event is correct');" +
                "    callback(JSON.stringify(event.data.events));" +
                "    window.removeEventListener('message', messageListener);" +
                "  }" +
                "}" +
                "window.addEventListener('message', messageListener);";

        try {
            capturedEventsJson = (String) ((JavascriptExecutor) webDriver).executeAsyncScript(script);
        } catch (ScriptTimeoutException e) {
            // Ignore the exception and keep trying
        }

        if (capturedEventsJson == null) {
            throw new RuntimeException("Failed to receive the captured events after " + customTimeout + " ms");
        }

        System.out.println(capturedEventsJson);

        try (FileWriter fileWriter = new FileWriter(Paths.get("capturedEvents.json").toFile())) {
            fileWriter.write(capturedEventsJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeRecordedActions() throws JSONException {
        driver.get(url);
        Path jsonFilePath = Paths.get("capturedEvents.json");

        if (!Files.exists(jsonFilePath)) {
            throw new RuntimeException("JSON file with captured events not found");
        }

        By webAppLoading = By.xpath("//i[@class='fa fa-spinner fa-spin fa-3x fa-fw win-spinner']");
        By wiseLoading = By.id("_pui_loading_animation");
        FileReader fileReader;
        try {
            fileReader = new FileReader(jsonFilePath.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONTokener tokener = new JSONTokener(fileReader);
        JSONArray userActions = new JSONArray(tokener);

        for (int i = 0; i < userActions.length(); i++) {
            waitForElementDisappear(webAppLoading, 5);
            waitForElementDisappear(wiseLoading, 5);
            JSONObject action = userActions.getJSONObject(i);
            String actionType = action.getString("type");
            String xpath = action.getString("xpath");
            String cssSelector = action.getString("cssSelector");
            String id = action.has("id") ? action.getString("id") : "";
            String name = action.has("name") ? action.getString("name") : "";
            String innerText = action.has("innerText") ? action.getString("innerText") : "";

            if (action.has("wait")) {
                int wait = action.getInt("wait");
                if (wait > 0) {
                    Utility_Functions.timeWait(wait);
                }
            }

            By elementLocator;

            if (!id.isEmpty()) {
                elementLocator = By.id(id);
            } else if (!name.isEmpty()) {
                elementLocator = By.name(name);
            } else {
                elementLocator = By.cssSelector(cssSelector);
            }

            List<WebElement> elements = driver.findElements(elementLocator);
            WebElement element;

            if (!elements.isEmpty()) {
                element = elements.get(0);
            } else {
                elementLocator = By.xpath(xpath);
                element = driver.findElement(elementLocator);
            }

            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            boolean isClickable = (boolean) js.executeScript(
                    "var elem = arguments[0], isClickable = false;" +
                            "if (elem && elem.offsetParent !== null && elem.offsetWidth > 0 && elem.offsetHeight > 0) {" +
                            "  isClickable = true;" +
                            "}" +
                            "return isClickable;", element);

            if (!isClickable) {
                elementLocator = By.xpath(xpath);
            }

            switch (actionType) {
                case "click" -> {
                    try {
                        click(elementLocator, "Click: " + elementLocator);
                    } catch (WebDriverException e) {
                        if (e.getMessage().contains("element click intercepted")) {
                            System.out.println("Inner Text" + innerText);
                            elementLocator = By.xpath("//*[text()='" + innerText + "']");
                            click(elementLocator, "Click: " + elementLocator);
                        } else
                            report.updateTestLog("Click", "Element is not clickable, and no innerText provided", Status.FAIL);
                    }
                }
                case "input" -> {
                    if (!action.has("value")) continue;
                    String inputValue = action.getString("value");
                    sendKeys(elementLocator, inputValue, " input into: " + elementLocator);
                }
                case "keydown" -> {
                    String key = action.getString("key");
                    Utility_Functions.actionKey(Keys.valueOf(key.toUpperCase()), driver);
                    report.updateTestLog("Send Key", "Sending Key: " + key, Status.PASS);
                }
                case "validateText" -> {
                    String text = action.getString("expectedText");
                    common.validateText(elementLocator, text, "Validating Text: "+ text);
                }
                case "mouseover" -> {
                    Utility_Functions.xmouseOver(driver, elementLocator);
                    report.updateTestLog("Mouseover", "Hovering Over Element", Status.PASS);
                }
            }
        }
    }
}
