package businesskeywords.WiseSmokeTest;

public enum SuccessFailureHeadersValidation {
    verifyCostAndPricesUpdatedSuccessMessage("Updates were Successful."),
    verifyItemMasterUpdatedSuccessMessage("Fields have recently been changed=>VERIFY CHANGES!"),
    verifyCustomerGroupAdditionSuccessMessage("Group Name successfully changed."),
    verifyCustomerAdditionToGroupSuccessMessage("Successfully added Customer. Add another or F12 Return."),
    verifyAdditionOfAContractSuccessMessage("Successfully added Contract. Add another or F12 Return."),
    verifyAdditionOfIndividualItemsToContractSuccessMessage("Successfully added item. Add another or F12 Return."),
    verifyLoadSpecialPricingConfirmationMessage("Confirm the load of special pricing for"),
    verifyLoadSpecialPricingSuccessMessage("Load Special Pricing completed. F12 to Return.");

    private final String message;

    public String getOutcomeMessage() {
        return message;
    }

    SuccessFailureHeadersValidation(String message) {
        this.message = message;
    }
}
