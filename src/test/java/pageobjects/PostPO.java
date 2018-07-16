package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.AppiumUtils;

public class PostPO extends BasePO {
    protected PostPO(AppiumDriver driver) {
        super(driver);
    }

    public boolean isPostScreenDisplayed() {
        return AppiumUtils.isElementDisplayed(postTextField);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/a_p")
    AndroidElement postTextField;

    protected void setPostText(String postText) {
        postTextField.sendKeys(postText);
        driver.hideKeyboard();
    }


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/uj")
    AndroidElement postButton;

    public void tapOnPostButton() {
        postButton.click();
    }


    @AndroidFindBy(id = "android:id/button1")
    AndroidElement postNowConfirmationButton;

    public void publishPost(String postText) {
        setPostText(postText);
        tapOnPostButton();
//        postNowConfirmationButton.click();
    }

    public void tapOnpostNowConfirmationButton() {
        postNowConfirmationButton.click();
    }

}
