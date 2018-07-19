/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Dimension;
import utils.AppiumUtils;

/**
 * Page Object Class: This contains all the page objects of Musical ly login page
 *
 * @author prat3ik
 */
public class DashboardPO extends BasePO {

    /**
     * @param driver the appium driver created in the beforesuite method.
     */
    public DashboardPO(AppiumDriver driver) {
        super(driver);
    }

    public void waitTillDashboardIsDisplayed() {
        waitUtils.waitForElementToBeVisible(forYouTextView, driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/an1")
    AndroidElement forYouTextView;

    /**
     * It will return whether Dashboard is being properly displayed.
     *
     * @return
     */
    public boolean isDashboardDisplayed() {
        return forYouTextView.isDisplayed();
    }

    /**
     * This method will tap on User Profile.
     */
    public void tapOnUserProfile() {
        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth();
        int height = size.getHeight();
        AppiumUtils.clickOnPoint((int) (width * 0.97), (int) (height * 0.98), driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in']")
    AndroidElement loginLink;

    public LoginPO tapOnLoginLink() {
        loginLink.click();
        LoginPO loginPO = new LoginPO(driver);
        loginPO.waitTillPageIsDisplayed();
        return loginPO;
    }

    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    AndroidElement allowButton;

    @AndroidFindBy(xpath = "//*[@id='ath']")
    AndroidElement plusButton;

    public void tapOnPlusButton() {
        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth();
        int height = size.getHeight();
        waitUtils.staticWait(5000);
        AppiumUtils.clickOnPoint((int) (width * 0.5), (int) (height * 0.98), driver);
        //plusButton.click();
        waitUtils.staticWait(1000);
        if (AppiumUtils.isElementDisplayed(allowButton)) {
            allowButton.click();
            allowButton.click();
            allowButton.click();
        }
    }

    public void moveToPost(int postNo) {
        for (int i = 1; i < postNo; i++)
            AppiumUtils.verticalScroll(driver);
        waitUtils.staticWait(1000);
    }

    public SearchPO tapOnSearchButton() {
        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth();
        int height = size.getHeight();
        waitUtils.staticWait(5000);
        AppiumUtils.clickOnPoint((int) (width * 0.3), (int) (height * 0.98), driver);
        //plusButton.click();
        waitUtils.staticWait(1000);
        return new SearchPO(driver);
    }


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/aki")
    AndroidElement likeButton;

    public boolean isLikeButtonDisplayed() {
        return likeButton.isDisplayed();
    }

    public void tapOnLikeButton() {
        likeButton.click();
    }


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/yi")
    AndroidElement commentButton;

    protected boolean isCommentButtonDisplayed() {
        return commentButton.isDisplayed();
    }

    protected void tapOnCommentButton() {
        commentButton.click();
    }
}