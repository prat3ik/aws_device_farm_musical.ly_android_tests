package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PostPO extends BasePO {
    protected PostPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/a_p")
    AndroidElement postTextField;

    protected void setPostText(String postText){
        postTextField.sendKeys(postText);
    }


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/uj")
    AndroidElement postButton;

    protected void tapOnPostButton(){
        postButton.click();
    }


    @AndroidFindBy(id = "android:id/button1")
    AndroidElement postNowConfirmationButton;

    public void publishPost(String postText){
        setPostText(postText);
        tapOnPostButton();
        postNowConfirmationButton.click();
    }

}
