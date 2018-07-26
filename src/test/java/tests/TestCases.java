/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.*;
import utils.PropertyUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * This is the Main Test Cases Class, All the test cases are defined in this.
 *
 * @author prat3ik
 */
public class TestCases extends BaseTest {

    private final String USERNAME = PropertyUtils.getProperty("valid.email", "username");
    private final String PASSWORD = PropertyUtils.getProperty("valid.password", "password");
    private final String USERNAME_TO_BE_SEARCHED = PropertyUtils.getProperty("search.username", "username");
    private final int FAN_TO_BE_SELECTED = PropertyUtils.getIntegerProperty("search.username.fan.count", 3);
    private final String COMMENT1 = PropertyUtils.getProperty("fan.comment1", "Hello there1");
    private final String COMMENT2 = PropertyUtils.getProperty("fan.comment2", "Hello there2");
    private final String COMMENT3 = PropertyUtils.getProperty("fan.comment3", "Hello there3");


    int COMMENT_ICON_X = PropertyUtils.getIntegerProperty("Pixel.comment.icon_x", 980);
    int COMMENT_ICON_Y = PropertyUtils.getIntegerProperty("Pixel.comment.icon_y", 1235);

//    COMMENT_ICON_X = 5;
//    COMMENT_ICON_X = PropertyUtils.getIntegerProperty("OnePlus3T.comment.icon_x", 980);
//    COMMENT_ICON_Y = PropertyUtils.getIntegerProperty("OnePlus3T.comment.icon_y", 1235);

    private String COMMENT_TEXT = PropertyUtils.getProperty("comment.text", "Test1235");

    @BeforeTest
    @Override
    public void setUpPage() {
        if (EXECUTION_TYPE.equals("local")) {
            COMMENT_ICON_X = PropertyUtils.getIntegerProperty("OnePlus3T.comment.icon_x", 980);
            COMMENT_ICON_Y = PropertyUtils.getIntegerProperty("OnePlus3T.comment.icon_y", 1235);
        }

        ProcessBuilder pb = new ProcessBuilder("adb", "shell", "settings", "put", "system", "pointer_location", "1");
        Process pc = null;
        try {
            pc = pb.start();
            pc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish!");
    }

//    @Test
//    public void verifyValidUserCanLogin() {
//        final String username = "pratikchachpara@gmail.com";
//        final String password = "pratikmusicly";
//
//        DashboardPO dashboardPO = new DashboardPO(driver);
//        dashboardPO.tapOnUserProfile();
//        LoginPO loginPO = dashboardPO.tapOnLoginLink();
//        loginPO.login(username, password);
//        Assert.assertFalse(loginPO.isinvalidEmailOrPasswordErrorDisplayed(), "Email and Password is invalid");
//        //Assert.assertTrue(dashboardPO.isDashboardDisplayed(), "Dashboard did not appear, Login might not successful");
//    }
//
//    @Test
//    public void verifyUserCanUploadPost() {
//        final String username = "pratikchachpara@gmail.com";
//        final String password = "pratikmusicly";
//
//        String postText = "Hello, this is my sample post: " + System.currentTimeMillis();
//        DashboardPO dashboardPO = new DashboardPO(driver);
//        dashboardPO.tapOnPlusButton();
//        PostPO postPO = new VideoUtilityPO(driver).uploadRecordedVideo();
//        postPO.publishPost(postText);
//        LoginPO loginPO = dashboardPO.tapOnLoginLink();
//        loginPO.login(username, password);
//        Assert.assertFalse(loginPO.isinvalidEmailOrPasswordErrorDisplayed(), "Email and Password is invalid");
//        postPO.tapOnPostButton();
//        Assert.assertFalse(postPO.isPostScreenDisplayed(), "Post screen is still visible event after submitted the post");
//    }
//
//    @Test
//    public void verifyUserCanLikeThePost() {
//        final int postNo = 3;
//        DashboardPO dashboardPO = new DashboardPO(driver);
//        dashboardPO.waitTillDashboardIsDisplayed();
//        dashboardPO.moveToPost(postNo);
//        dashboardPO.tapOnLikeButton();
//        waitUtils.staticWait(4000);
//    }

    @Test
    public void verifyUserCanCommentOnFansOfUser() {

        ProcessBuilder pb = new ProcessBuilder("adb", "shell", "settings", "put", "system", "pointer_location", "1");
        Process pc = null;
        try {
            pc = pb.start();
            pc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish!");

        COMMENT_TEXT += System.currentTimeMillis() + "";
        DashboardPO dashboardPO = new DashboardPO(driver);
        dashboardPO.waitTillDashboardIsDisplayed();
        if (!EXECUTION_TYPE.equals("local")) {
            dashboardPO.tapOnUserProfile();
            LoginPO loginPO = dashboardPO.tapOnLoginLink();
            loginPO.login(USERNAME, PASSWORD);
            Assert.assertFalse(loginPO.isinvalidEmailOrPasswordErrorDisplayed(), "Email and Password is invalid");
        }

        SearchPO searchPO = dashboardPO.tapOnSearchButton();
        UserProfilePO userProfilePO = searchPO.serachAndSelectTheUser(USERNAME_TO_BE_SEARCHED);
        FansPO fansPO = userProfilePO.tapOnFansCount();

        fansPO.selectFans(FAN_TO_BE_SELECTED, Arrays.asList(COMMENT1, COMMENT2, COMMENT3));

    }

}
