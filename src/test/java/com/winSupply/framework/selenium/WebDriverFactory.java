package com.winSupply.framework.selenium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.winSupply.framework.FrameworkException;
import com.winSupply.framework.Settings;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory class for creating the {@link WebDriver} object as required
 *
 * @author winSupply
 */
public class WebDriverFactory {
    private static Properties properties;

    private WebDriverFactory() {
        // To prevent external instantiation of this class
    }

    /**
     * Function to return the appropriate {@link WebDriver} object based on the
     * parameters passed
     *
     * @param browser The {@link Browser} to be used for the test execution
     * @return The corresponding {@link WebDriver} object
     */

    @SuppressWarnings("deprecation")
    public static WebDriver getWebDriver(Browser browser) {
        WebDriver driver = null;
        properties = Settings.getInstance();
        String platform = System.getProperty("DefaultPlatform");
        if (platform == null) {
            platform = properties.getProperty("DefaultPlatform");
        }
        String headless = properties.getProperty("HeadLess");

        switch (browser) {
            case CHROME:
            	// Takes the system proxy settings automatically


    			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    			ChromeOptions options = new ChromeOptions();
    			// disable ephemeral flash permissions flag
    			options.addArguments("--disable-features=EnableEphemeralFlashPermission");

//    	    options.addArguments("--disable-web-security");
//    			options.addArguments("--allow-running-insecure-content");

    			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    			/*Proxy proxy = new Proxy();
    			proxy.setHttpProxy("10.10.0.32:8080");*/

    			try {
    				/*String path=null;
    				switch (platform) {
    				case "WINDOWS":
    					path=properties.getProperty("ChromeDriverPath");
    					
    					break;
    				case "MAC":
    					path=properties.getProperty("MChromeDriverPath");
    					
    					break;

    				}*/
    				//System.setProperty("webdriver.chrome.driver", path);
    				
    				//driver = new ChromeDriver(capabilities);
    			//options.setCapability("proxy", proxy);
    			options.addArguments("--disable-web-security");
    			options.addArguments("--allow-running-insecure-content");
    			options.addArguments(" --ignore-certificate-errors");
    			options.addArguments("--allow-running-insecure-content");
    			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    			WebDriverManager.chromedriver().setup();
    				ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
    				//builder.usingDriverExecutable(new File(path));
    				builder.usingAnyFreePort();
    				ChromeDriverService service = builder
    					.build();
    				driver = new ChromeDriver(service, options);
    				
    			} catch (Exception e) {
    				e.printStackTrace();
    				System.out.println(e);
    			}

    			// driver.manage().deleteAllCookies();
    			break;

            case FIREFOX:
                // Takes the system proxy settings automatically

                /*switch (platform) {
                    case "WINDOWS":
                        System.setProperty("webdriver.gecko.driver", properties.getProperty("GeckoDriverPath"));
                        break;
                    case "MAC":
                        System.setProperty("webdriver.gecko.driver", properties.getProperty("MGeckoDriverPath"));
                        break;
                }*/


                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);

                DesiredCapabilities handlSSLErr = DesiredCapabilities.firefox();
                handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                WebDriverManager.firefoxdriver().setup();
                
                try {
                    if (headless.toUpperCase().equals("NO")) {
                        driver = new FirefoxDriver(handlSSLErr);
                    } else {
                        driver = new FirefoxDriver(firefoxOptions);
                    }


                } catch (Exception e) {
                    // TODO: handle exception

                    System.out.println("WebDriverFactory.getWebDriver()" + e);
                }

                break;

            case GHOST_DRIVER:
                // Takes the system proxy settings automatically (I think!)

                System.setProperty("phantomjs.binary.path", properties.getProperty("PhantomJSPath"));
                driver = new PhantomJSDriver();

                break;

            case INTERNET_EXPLORER:
                // Takes the system proxy settings automatically
            	WebDriverManager.iedriver().setup();
                //System.setProperty("webdriver.ie.driver", properties.getProperty("InternetExplorerDriverPath"));
                driver = new InternetExplorerDriver();
                break;

            case CHROME_HEADLESS:
                break;
            case EDGE:
                // Takes the system proxy settings automatically
            	WebDriverManager.edgedriver().setup();
                //System.setProperty("webdriver.edge.driver", properties.getProperty("EdgeDriverPath"));
                driver = new EdgeDriver();
                break;

            case SAFARI:
                // Takes the system proxy settings automatically

                driver = new SafariDriver();
                break;

            case PERFECTO_MOBILE:
                break;
            case PERFECTO_MOBILE_OS:
                break;
            case PERFECTO_MOBILE_DEFAULT:
                break;
            case PERFECTO_MOBILE_CHROME:
                break;
            case PERFECTO_MOBILE_SAFARI:
                break;
            default:
                throw new FrameworkException("Unhandled browser!");
        }

        return driver;
    }

    private static DesiredCapabilities getProxyCapabilities() {
        properties = Settings.getInstance();
        String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

        Proxy proxy = new Proxy();
        proxy.setProxyType(ProxyType.MANUAL);
        proxy.setHttpProxy(proxyUrl);
        proxy.setFtpProxy(proxyUrl);
        proxy.setSslProxy(proxyUrl);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

        return desiredCapabilities;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object based on the parameters
     * passed
     *
     * @param browser        The {@link Browser} to be used for the test execution
     * @param browserVersion The browser version to be used for the test execution
     * @param platform       The {@link Platform} to be used for the test execution
     * @param remoteUrl      The URL of the remote machine to be used for the test
     *                       execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform,
                                               String remoteUrl) {
        // For running RemoteWebDriver tests in Chrome and IE:
        // The ChromeDriver and IEDriver executables needs to be in the PATH of
        // the remote machine
        // To set the executable path manually, use:
        // java -Dwebdriver.chrome.driver=/path/to/driver -jar
        // selenium-server-standalone.jar
        // java -Dwebdriver.ie.driver=/path/to/driver -jar
        // selenium-server-standalone.jar
        try {
            properties = Settings.getInstance();

            boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

            DesiredCapabilities desiredCapabilities = null;
            if (proxyRequired) {
                desiredCapabilities = getProxyCapabilities();
            } else {
                desiredCapabilities = new DesiredCapabilities();
            }

            desiredCapabilities.setBrowserName(browser.getValue());

            if (browserVersion != null) {
                desiredCapabilities.setVersion(browserVersion);
            }
            if (platform != null) {
                desiredCapabilities.setPlatform(platform);
            }
            switch (browser) {
                case FIREFOX:
                    desiredCapabilities = DesiredCapabilities.firefox();
                    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

                    break;
                default:
                    break;

            }

            desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
            // remote execution

            URL url = getUrl(remoteUrl);

            return new RemoteWebDriver(url, desiredCapabilities);

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }

    private static URL getUrl(String remoteUrl) {
        URL url;
        try {
            url = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new FrameworkException("The specified remote URL is malformed");
        }
        return url;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object based on the parameters
     * passed
     *
     * @param browser   The {@link Browser} to be used for the test execution
     * @param remoteUrl The URL of the remote machine to be used for the test
     *                  execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
        return getRemoteWebDriver(browser, null, null, remoteUrl);
    }

    /**
     * Function to return the {@link ChromeDriver} object emulating the device
     * specified by the user
     *
     * @param deviceName The name of the device to be emulated (check Chrome Dev
     *                   Tools for a list of available devices)
     * @return The corresponding {@link ChromeDriver} object
     */
    @SuppressWarnings("deprecation")
    public static WebDriver getEmulatedWebDriver(String deviceName) {
        DesiredCapabilities desiredCapabilities = getEmulatedChromeDriverCapabilities(deviceName);

        properties = Settings.getInstance();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("ChromeDriverPath"));

        return new ChromeDriver(desiredCapabilities);
    }

    private static DesiredCapabilities getEmulatedChromeDriverCapabilities(String deviceName) {
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return desiredCapabilities;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object emulating the device
     * specified by the user
     *
     * @param deviceName The name of the device to be emulated (check Chrome Dev
     *                   Tools for a list of available devices)
     * @param remoteUrl  The URL of the remote machine to be used for the test
     *                   execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getEmulatedRemoteWebDriver(String deviceName, String remoteUrl) {
        DesiredCapabilities desiredCapabilities = getEmulatedChromeDriverCapabilities(deviceName);
        desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
        // remote execution

        URL url = getUrl(remoteUrl);

        return new RemoteWebDriver(url, desiredCapabilities);
    }

    /**
     * Function to return the {@link ChromeDriver} object emulating the device
     * attributes specified by the user
     *
     * @param deviceWidth      The width of the device to be emulated (in pixels)
     * @param deviceHeight     The height of the device to be emulated (in pixels)
     * @param devicePixelRatio The device's pixel ratio
     * @param userAgent        The user agent string
     * @return The corresponding {@link ChromeDriver} object
     */
    @SuppressWarnings("deprecation")
    public static WebDriver getEmulatedWebDriver(int deviceWidth, int deviceHeight, float devicePixelRatio,
                                                 String userAgent) {
        DesiredCapabilities desiredCapabilities = getEmulatedChromeDriverCapabilities(deviceWidth, deviceHeight,
                devicePixelRatio, userAgent);

        properties = Settings.getInstance();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("ChromeDriverPath"));

        return new ChromeDriver(desiredCapabilities);
    }

    private static DesiredCapabilities getEmulatedChromeDriverCapabilities(int deviceWidth, int deviceHeight,
                                                                           float devicePixelRatio, String userAgent) {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", deviceWidth);
        deviceMetrics.put("height", deviceHeight);
        deviceMetrics.put("pixelRatio", devicePixelRatio);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        // mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1;
        // en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko)
        // Chrome/18.0.1025.166 Mobile Safari/535.19");
        mobileEmulation.put("userAgent", userAgent);

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return desiredCapabilities;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object emulating the device
     * attributes specified by the user
     *
     * @param deviceWidth      The width of the device to be emulated (in pixels)
     * @param deviceHeight     The height of the device to be emulated (in pixels)
     * @param devicePixelRatio The device's pixel ratio
     * @param userAgent        The user agent string
     * @param remoteUrl        The URL of the remote machine to be used for the test
     *                         execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getEmulatedRemoteWebDriver(int deviceWidth, int deviceHeight, float devicePixelRatio,
                                                       String userAgent, String remoteUrl) {
        DesiredCapabilities desiredCapabilities = getEmulatedChromeDriverCapabilities(deviceWidth, deviceHeight,
                devicePixelRatio, userAgent);
        desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
        // remote execution

        URL url = getUrl(remoteUrl);

        return new RemoteWebDriver(url, desiredCapabilities);
    }

}