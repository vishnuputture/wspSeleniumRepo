package businesskeywords.WiseSmokeTest;

public enum PageHeadersValidation {
    verifyCostAndPricesHeader("COST & PRICES");

    private final String headerText;

    public String getHeaderText() {
        return headerText;
    }

    PageHeadersValidation(String headerText) {
        this.headerText = headerText;
    }
}
