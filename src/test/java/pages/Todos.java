package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

public class Todos extends pageBase {
    private WebDriver ldriver;

    public Todos(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    private static final String PARAMETER = "parameter";
    private String strXpathToDoItem = "//li//label[text()='" + PARAMETER + "']";
    private String strXpathToDoItemRadioButton = "//li//label[text()='" + PARAMETER + "']/preceding-sibling::input";

    @FindBy(id = "todo-input")
    private WebElement txtTodoInputField;

    @FindBy(xpath = "//li[@data-testid='todo-item']")
    private WebElement txtToDoItem;

    @FindBy(xpath = "//li[@data-testid='todo-item']")
    private List<WebElement> txtToDoItemsUnderAllTab;

    @FindBy(xpath = "//li[@data-testid='todo-item']")
    private List<WebElement> txtToDoItemsUnderActiveTab;

    @FindBy(xpath = "//li[@class='completed' and @data-testid='todo-item']")
    private List<WebElement> txtToDoItemsUnderCompleteTab;

    @FindBy(xpath = "//label[text()='Edit Todo Input']//preceding-sibling::input")
    private WebElement txtEditToDoItem;

    @FindBy(xpath = "//a[text()='Active']")
    private WebElement lnkActive;

    @FindBy(xpath = "//a[text()='Completed']")
    private WebElement lnkCompleted;

    @FindBy(xpath = "//a[text()='All']")
    private WebElement lnkAll;

    @FindBy(xpath = "//button[@class='clear-completed']")
    private WebElement btnClearCompleted;

    @FindBy(xpath = "//div[@class='view']//input[@type='checkbox']")
    private WebElement rdItem;

    @FindBy(xpath = "//div[@class='view']//input[@type='checkbox']")
    private List<WebElement> rdItems;

    @FindBy(xpath = "//button[@class='destroy']")
    private WebElement btnClose;

    @FindBy(xpath = "//button[@class='destroy']")
    private List<WebElement> btnCloseIcons;

    @FindBy(xpath = "//span[@class='todo-count']")
    private WebElement txtToDoActiveCount;

    @FindBy(id = "toggle-all")
    private WebElement chkToggleAll;

    /**
     * This method is used to add a to do item and verify the added item
     *
     * @param toDoItem to do item
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void addToDos(String toDoItem) {
        waitForElementVisible(ldriver, txtTodoInputField);// Ensure element is visible
        txtTodoInputField.sendKeys(toDoItem);
        txtTodoInputField.sendKeys(Keys.ENTER);

        captureScreenshots(ldriver, "addToDoItem");
        String actualToDoItem = txtToDoItem.getText();
        Assert.assertEquals(actualToDoItem, toDoItem);

        //Verify the added item displayed under active tab
        lnkActive.click();
        String actualToDoItemUnderActiveTab = txtToDoItemsUnderActiveTab.get(0).getText();
        Assert.assertEquals(actualToDoItemUnderActiveTab, toDoItem);
        captureScreenshots(ldriver, "addToDoItemUnderActiveTab");
    }

    /**
     * This method is used to edit a to do item and verify the edited item
     *
     * @param editToDoItem item to edit
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void editToDos(String editToDoItem) {
        Actions actions = new Actions(ldriver);
        actions.doubleClick(txtToDoItem).perform();
        txtEditToDoItem.sendKeys(Keys.CONTROL + "a");
        txtEditToDoItem.sendKeys(Keys.DELETE);

        txtEditToDoItem.sendKeys(editToDoItem);
        txtEditToDoItem.sendKeys(Keys.ENTER);

        captureScreenshots(ldriver, "editToDoItem");
        String actualToDoItem = txtToDoItem.getText();
        Assert.assertEquals(actualToDoItem, editToDoItem, "Edited item is displayed successfully under All tab");

        //Verify the edited item displayed under active tab
        lnkActive.click();
        String actualToDoItemUnderActiveTab = txtToDoItemsUnderActiveTab.get(0).getText();
        Assert.assertEquals(actualToDoItemUnderActiveTab, editToDoItem, "Edited item is displayed successfully under Active tab");
        captureScreenshots(ldriver, "editToDoItemUnderActiveTab");
    }

    /**
     * This method is used to complete a to do item and verify the completed item
     *
     * @param toDoItem to do item
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void completeDoToItem(String toDoItem) {
        //First item is marked as completed
        rdItem.click();

        //Verify completed item cannot be displayed under the active tab
        Assert.assertTrue(waitForElementInvisible(ldriver,strXpathToDoItem.replace(PARAMETER,toDoItem )), "Item is not displayed under active tab as completed");

        //Verify the completed item displayed under complete tab
        lnkCompleted.click();
        String actualToDoItemUnderCompleteTab = txtToDoItemsUnderCompleteTab.get(0).getText();
        Assert.assertEquals(actualToDoItemUnderCompleteTab, toDoItem);

        //Verify the completed item displayed as checked under complete tab
        Assert.assertTrue(rdItem.isSelected(), "Completed item displayed as checked under complete tab");
        captureScreenshots(ldriver, "completeToDoItemUnderCompleteTab");

        //Users should be able to toggle a completed item back to active by clicking the checkbox again.
        //That task should again be displayed under the active tab and removed from the completed tab.
        rdItem.click();

        //Verify item is not displayed under the complete tab as it is marked as active
        Assert.assertTrue(waitForElementInvisible(ldriver, strXpathToDoItem.replace(PARAMETER,toDoItem)), "Item is not displayed under complete tab");
        captureScreenshots(ldriver, "itemNotDisplayedUnderCompleteTab");

        //Verify the item displayed under active tab as it is marked as active
        lnkActive.click();
        String actualToDoItemUnderActiveTab = txtToDoItemsUnderActiveTab.get(0).getText();
        Assert.assertEquals(actualToDoItemUnderActiveTab, toDoItem);
        captureScreenshots(ldriver, "itemAgainDisplayedUnderActiveTab");
    }

    /**
     * This method is used to delete a to do item under each tab and verify the item is not displayed under tab
     *
     * @param toDoItemList to do item list
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void deleteToDoItemUnderEachTab(String toDoItemList) {
        //delete a item under all tab and verify the item is not displayed under all tab
        addListOfToDos(toDoItemList);
        deleteToDoItemAndVerify( "all");

        //delete a item under active tab and verify the item is not displayed under active tab
        addListOfToDos(toDoItemList);
        lnkActive.click();
        deleteToDoItemAndVerify("active");

        //delete a item under complete tab and verify the item is not displayed under complete tab
        addListOfToDos(toDoItemList);
        //mark all items as completed
        chkToggleAll.click();
        captureScreenshots(ldriver, "markAllItemsAsCompleted");

        lnkCompleted.click();
        deleteToDoItemAndVerify("complete");
    }

    /**
     * This method is used to clear all completed to do items and verify the items are not displayed under complete tab
     *
     * @param toDoItemList to do item list
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void clearCompletedToDos(String toDoItemList){
        addListOfToDos(toDoItemList);

        //mark all items as completed
        chkToggleAll.click();
        captureScreenshots(ldriver, "markedItemsAsCompleted");

        //Verify item displayed as completed under complete tab
        lnkCompleted.click();
        Assert.assertTrue(txtToDoItemsUnderCompleteTab.size() == toDoItemList.split(",").length, "All the completed items are displayed under complete tab");
        captureScreenshots(ldriver, "itemDisplayedAsCompletedUnderCompleteTab");

        //clear all completed items
        btnClearCompleted.click();
        captureScreenshots(ldriver, "clearCompletedItems");
        Assert.assertTrue(waitForElementInvisible(ldriver, txtToDoItem), "Items are not displayed under complete tab");
    }

    /**
     * This method is used to view to do list and verify the items are displayed in the correct order and verify active item count displayed correctly
     *
     * @param toDoItemList to do item list
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void viewToDoList(String toDoItemList) {
        addListOfToDos(toDoItemList);

        //Verify item order with the newest at the bottom
        String[] itemList = toDoItemList.split(",");
        for (int i = 0; i < itemList.length; i++) {
            Assert.assertEquals(txtToDoItemsUnderAllTab.get(i).getText(), itemList[i]);
        }
        captureScreenshots(ldriver, "viewToDoList");

        //Verify user can see active item counter
        lnkActive.click();
        int activeItems = txtToDoItemsUnderActiveTab.size();
        Assert.assertTrue(txtToDoActiveCount.getText().contains(Integer.toString(activeItems)), "Active item counter is displayed successfully below the list");
        captureScreenshots(ldriver, "activeItemCounterDisplayed");

        //Verify active count displayed correctly as items completed
        rdItems.get(0).click();
        Assert.assertTrue(txtToDoActiveCount.getText().contains(Integer.toString(activeItems - 1)), "Active item counter is displayed correctly after marking an item as completed");
        captureScreenshots(ldriver, "activeItemCounterDisplayedAfterMarkingItemAsCompleted");
    }

    /**
     * This method is used to filter to do items based on tabs and verify the items are displayed under correct tab
     *
     * @param toDoItemList to do item list
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void filterToDo(String toDoItemList) {
        addListOfToDos(toDoItemList);

        //Verify all filter
        String[] itemList = toDoItemList.split(",");
        for (int i = 0; i < itemList.length; i++) {
            Assert.assertEquals(txtToDoItemsUnderAllTab.get(i).getText(), itemList[i]);
        }
        captureScreenshots(ldriver, "addedItemsDisplayedUnderAllTab");

        //Verify active filter
        lnkActive.click();
        for (int i = 0; i < itemList.length; i++) {
            Assert.assertEquals(txtToDoItemsUnderActiveTab.get(i).getText(), itemList[i]);
        }
        captureScreenshots(ldriver, "addedItemsDisplayedUnderActiveTab");

        //Verify completed filter
        String completeItem = txtToDoItemsUnderActiveTab.get(0).getText();
        rdItems.get(0).click();
        lnkCompleted.click();
        WebElement completedItem = ldriver.findElement(By.xpath(strXpathToDoItem.replace(PARAMETER, completeItem)));
        Assert.assertEquals(completedItem.getText(), completeItem, "Item is displayed under complete tab");

        //Verify again active filter, completed item should not be displayed
        lnkActive.click();
        Assert.assertTrue(waitForElementInvisible(ldriver, strXpathToDoItem.replace(PARAMETER, completeItem)), "Item is not displayed under active tab as it is completed");

        //Verify all filter again, all items should be displayed. Completed item should be displayed as checked
        lnkAll.click();
        Assert.assertTrue(txtToDoItemsUnderAllTab.size() == toDoItemList.split(",").length, "All the items are displayed under all tab");

        WebElement rdTodoItem = ldriver.findElement(By.xpath(strXpathToDoItemRadioButton.replace(PARAMETER, completeItem)));
        Assert.assertTrue(rdTodoItem.isSelected(), "Completed item displayed as checked under all tab");
        captureScreenshots(ldriver, "AllItemsFilterWithCompletedItem");
    }

    /**
     * This method is used to select all and unselect all to do items and verify the items are displayed under correct tab
     *
     * @param toDoItemList to do item list
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void selectUnselectAllToDos(String toDoItemList) {
        addListOfToDos(toDoItemList);

        //Mark all todos as completed
        chkToggleAll.click();
        captureScreenshots(ldriver, "markAllItemsAsCompleted");
        lnkCompleted.click();
        String[] itemList = toDoItemList.split(",");
        for (int i = 0; i < itemList.length; i++) {
            Assert.assertEquals(txtToDoItemsUnderActiveTab.get(i).getText(), itemList[i]);
        }
        captureScreenshots(ldriver, "verified all items are marked as completed");

        //Mark all completed todos as active
        chkToggleAll.click();
        captureScreenshots(ldriver, "markAllItemsAsActive");
        lnkAll.click();
        for (int i = 0; i < itemList.length; i++) {
            Assert.assertEquals(txtToDoItemsUnderActiveTab.get(i).getText(), itemList[i]);
        }
        captureScreenshots(ldriver, "verified all items are marked as active");
    }

    /**
     * This method is used to add list of to do items
     *
     * @param toDoItemList to do item list
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    private void addListOfToDos(String toDoItemList) {
        String[] itemList = toDoItemList.split(",");
        for (String item : itemList) {
            txtTodoInputField.sendKeys(item);
            txtTodoInputField.sendKeys(Keys.ENTER);
        }
        captureScreenshots(ldriver, "addListOfToDoItems");
    }

    /**
     * This method is used to delete a to do item and verify the item is not displayed under tab
     *
     * @param tabName tab name
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    private void deleteToDoItemAndVerify(String tabName) {
        //delete first item and verify
        String itemGoingToDelete = txtToDoItem.getText();

        moveToElement(ldriver, txtToDoItem);
        btnClose.click();
        captureScreenshots(ldriver, "deleteToDoItemUnder"+tabName+"tab");

        //verify item is deleted
        Assert.assertTrue(waitForElementInvisible(ldriver, strXpathToDoItem.replace(PARAMETER,itemGoingToDelete)), "Item is not displayed under "+tabName+" tab");

        //delete remaining items
        for (WebElement item : btnCloseIcons) {
            moveToElement(ldriver, txtToDoItem);
            item.click();
            captureScreenshots(ldriver, "deleteAllToDoItemUnder"+tabName+" tab");
            if (btnCloseIcons.size() == 0) {
                break;
            }
        }
    }

}
