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
    verifyLegacyPriceSheetImmediateProcessValidationMessage("Immediate Processing cannot be completed until the program is able to access all files needed  Please have all users return to a main menu then press F9 to process this sheet immediately To process with a nightly press F12 to return to the previous screen and enter a process date");

    private final String message;

    public String getOutcomeMessage() {
        return message;
    }

    SuccessFailureHeadersValidation(String message) {
        this.message = message;
    }
}
