/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.DashboardPO;
import pageobjects.LoginPO;
import pageobjects.PostPO;
import pageobjects.VideoUtilityPO;

/**
 * This is the Main Test Cases Class, All the test cases are defined in this.
 *
 * @author prat3ik
 */
public class TestCases extends BaseTest {

    @BeforeTest
    @Override
    public void setUpPage() {
    }

    @Test
    public void verifyValidUserCanLogin() {
        final String username = "pratikchachpara@gmail.com";
        final String password = "pratikmusicly";

        DashboardPO dashboardPO = new DashboardPO(driver);
        dashboardPO.tapOnUserProfile();
        LoginPO loginPO = dashboardPO.tapOnLoginLink();
        loginPO.login(username, password);
        Assert.assertFalse(loginPO.isinvalidEmailOrPasswordErrorDisplayed(), "Email and Password is invalid");
        //Assert.assertTrue(dashboardPO.isDashboardDisplayed(), "Dashboard did not appear, Login might not successful");
    }

    @Test
    public void verifyUserCanUploadPost() {
        final String username = "pratikchachpara@gmail.com";
        final String password = "pratikmusicly";

        String postText = "Hello, this is my sample post: " + System.currentTimeMillis();
        DashboardPO dashboardPO = new DashboardPO(driver);
        dashboardPO.tapOnPlusButton();
        PostPO postPO = new VideoUtilityPO(driver).uploadRecordedVideo();
        postPO.publishPost(postText);
        LoginPO loginPO = dashboardPO.tapOnLoginLink();
        loginPO.login(username, password);
        Assert.assertFalse(loginPO.isinvalidEmailOrPasswordErrorDisplayed(), "Email and Password is invalid");
        postPO.tapOnPostButton();
        postPO.tapOnpostNowConfirmationButton();
    }

    @Test
    public void verifyUserCanLikeThePost(){
        DashboardPO dashboardPO = new DashboardPO(driver);

    }
}
