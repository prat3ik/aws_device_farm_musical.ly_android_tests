package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
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

    public void waitTillVideoPostIsDisplayed() {
        waitUtils.staticWait(2000);
        //waitUtils.waitForElementToBeVisible(commentEditText, driver);
    }

    public boolean tapOnCommentIcon(int comment_icon_x, int comment_icon_y) {
        waitTillVideoPostIsDisplayed();
        AppiumUtils.clickOnPoint(comment_icon_x, comment_icon_y, driver);
        return AppiumUtils.isElementDisplayed(disabledCommentSectionTextView) || AppiumUtils.isElementDisplayed(limitedCommentsTextView);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='This user has disabled comment section']")
    AndroidElement disabledCommentSectionTextView;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Comments of this video have been limited']")
    AndroidElement limitedCommentsTextView;

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/yl")
    AndroidElement commentButton;


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/vp")
    AndroidElement postedFirstComment;

    public void postComment(String commentText) {
        commentEditText.sendKeys(commentText);
        waitUtils.staticWait(200);
        driver.getKeyboard();
        commentButton.click();
        waitUtils.staticWait(400);
        Assert.assertEquals(postedFirstComment.getText(), commentText, "Typed Comment didn't match");
    }

    public void moveBackTOUserProfileAfterOpeningComments() {
        driver.navigate().back();
        driver.navigate().back();
    }


}
