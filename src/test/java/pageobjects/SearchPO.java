package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.AppiumUtils;

import java.util.List;

public class SearchPO extends BasePO {

    /**
     * A base constructor that sets the page's driver
     * <p>
     * The page structure is being used within this test in order to separate the
     * page actions from the tests.
     * <p>
     * Please use the AppiumFieldDecorator class within the page factory. This way annotations
     * like @AndroidFindBy within the page objects.
     *
     * @param driver the appium driver created in the beforesuite method.
     */
    protected SearchPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/aiw")
    AndroidElement searchBox;

    protected void tapOnSearchBox() {
        searchBox.click();
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/mr")
    AndroidElement searchUsersBox;

    protected void tapOnSearchUsersBox() {
        searchUsersBox.click();
    }

    protected void searchUsername(String username) {
        searchUsersBox.sendKeys(username);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/aiw")
    AndroidElement searchButton;

    protected void tapOnSearchButton() {
        searchButton.click();
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/apk")
    List<AndroidElement> userIdElements;

    protected void selectUser(String userId) {
        waitUtils.staticWait(2000);
        waitUtils.waitForElementToBeVisible(searchButton, driver);
//        for (AndroidElement el : userIdElements)
//            System.out.println(el.getText());
        AppiumUtils.getElementFromText(driver, userIdElements, userId).click();
    }


    public UserProfilePO serachAndSelectTheUser(String username) {
        tapOnSearchBox();
        waitUtils.waitForElementToBeVisible(searchUsersBox, driver);
        tapOnSearchUsersBox();
        searchUsername(username);
        tapOnSearchButton();
        selectUser("@" + username + ",");
        return new UserProfilePO(driver);
    }


}
