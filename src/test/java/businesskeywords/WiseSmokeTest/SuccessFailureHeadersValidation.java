package businesskeywords.WiseSmokeTest;

public enum SuccessFailureHeadersValidation {
    verifyCostAndPricesUpdatedSuccessMessage("Updates were Successful."),
    verifyItemMasterUpdatedSuccessMessage("Fields have recently been changed=>VERIFY CHANGES!"),
    verifyCustomerGroupAdditionSuccessMessage("Group Name successfully changed."),
    verifyCustomerAdditionToGroupSuccessMessage("Successfully added Customer. Add another or F12 Return."),
    verifyAdditionOfAContractSuccessMessage("Successfully added Contract. Add another or F12 Return."),
    verifyAdditionOfIndividualItemsToContractSuccessMessage("Successfully added item. Add another or F12 Return."),
    verifyLoadSpecialPricingConfirmationMessage("Confirm the load of special pricing for"),
    verifyLoadSpecialPricingSuccessMessage("Load Special Pricing completed. F12 to Return."),
    verifyLegacyPriceSheetUpdateSuccessMessage("price sheet information has been updated."),
    verifyLegacyPriceSheetRunComparisonReportsSuccessMessage("price sheet comparison reports have been run."),
    verifyLegacyPriceSheetImmediateProcessValidationMessage("Immediate Processing cannot be completed until the program is able to access all files needed  Please have all users return to a main menu then press F9 to process this sheet immediately To process with a nightly press F12 to return to the previous screen and enter a process date"),

    verifyPurchasingOrderTemplateCreationSuccessMessage("template successfully created."),
    verifyTheWorksheetRefreshedSuccessMessage("Worksheet refreshed successfully."),
    verifyWarningMessageForInvalidManufactureCode("Invalid Manufacturer Code."),
    verifyWarningMessageForInvalidProductCode("Invalid Product Code."),
    verifyWarningMessageForInvalidVendorCode("Invalid Vendor Code."),
    verifyCancelTemplateWarningMessage("Are you sure you want to cancel the creation of this template?"),
    verifyMonthSupplyToOrderErrorMessage("Please enter Month Supply between 0.01 and 9.99."),
    verifyLeadTimeErrorMessage("Please enter a valid lead time from 1-999.");

    private final String message;

    public String getOutcomeMessage() {
        return message;
    }

    SuccessFailureHeadersValidation(String message) {
        this.message = message;
    }
}
