package pages.warehouse.ElectronicCommerce;

import org.openqa.selenium.By;

public class ElectronicCommercePage {

    public static By signOutLink = By.xpath("//a[contains(text(),'Sign Out')]/i");
    public static By createGroup = By.id("my-group-create-button");
    public static By cancelButton = By.id("my-group-create-cancel-button");
    public static By groupNameInput = By.id("grp_name");
    public static By toasterMessage = By.xpath("//div[contains(@class,'toast-message')]");
    public static By searchBox = By.id("search-desktop");
    public static By closeIcon = By.xpath("//header[@class='c-card__header ws-create-list--model__head']/button");
    public static By newGroupFromLink = By.id("group-text-link");
    public static By createList = By.id("my-list-create-button");
    public static By cancelList = By.id("my-list-create-cancel-button");
    public static By listName = By.id("list-name");
    public static By addTiGroup = By.id("new-group-create-id");
    public static By addGroup = By.xpath("//input[@placeholder='New Group Name']");
    public static By addSelectedToCart = By.id("add-selected-to-cart");
    public static By trashIcon = By.xpath("//a[@class='is-disabled']");
    public static By copyIcon = By.xpath("//a[@class='my-list-copy-button-link is-disabled']");
    public static By sortIcon = By.xpath("//a[@class='ws-sort-menu is-disabled']");
}
