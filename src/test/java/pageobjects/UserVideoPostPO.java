package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.AppiumUtils;

public class UserVideoPostPO extends BasePO {
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
    protected UserVideoPostPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/yj")
    AndroidElement commentEditText;

    public void waitTillVideoPostIsDisplayed(){
        waitUtils.waitForElementToBeVisible(commentEditText, driver);
    }

    public void tapOnCommentIcon(int comment_icon_x, int comment_icon_y) {
        waitTillVideoPostIsDisplayed();
        AppiumUtils.clickOnPoint(comment_icon_x, comment_icon_y, driver);
    }
}
