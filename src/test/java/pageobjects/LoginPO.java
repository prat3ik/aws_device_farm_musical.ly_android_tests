package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
import utils.AppiumUtils;

public class LoginPO extends BasePO {
    protected LoginPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/azw")
    AndroidElement usernameTextField;

    public void waitTillPageIsDisplayed() {
        waitUtils.waitForElementToBeVisible(usernameTextField, driver);
    }

    protected void setUsenameTextField(String username) {
        usernameTextField.sendKeys(username);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/b04")
    AndroidElement passwordTextField;

    protected void setPasswordTextField(String password) {
        passwordTextField.sendKeys(password);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/b0j")
    AndroidElement loginButton;

    protected void tapOnLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        setUsenameTextField(username);
        setPasswordTextField(password);
        tapOnLoginButton();
    }


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/b0q")
    AndroidElement invalidEmailOrPasswordErrorTextView;

    public boolean isinvalidEmailOrPasswordErrorDisplayed() {
        return AppiumUtils.isElementDisplayed(invalidEmailOrPasswordErrorTextView);
    }

}
