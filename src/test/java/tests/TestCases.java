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

/**
 * This is the Main Test Cases Class, All the test cases are defined in this.
 *
 * @author prat3ik
 */
public class TestCases extends BaseTest {

    private final String USERNAME = PropertyUtils.getProperty("valid.email", "username");
    private final String PASSWORD = PropertyUtils.getProperty("valid.password", "password");
    private final String USERNAME_TO_BE_SEARCHED = PropertyUtils.getProperty("search.username", "username");
    private final String FAN_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan1", "@ashbare22");
    private final String FAN2_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan2", "username");
    private final String FAN3_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan3", "username");
    private final String FAN4_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan4", "username");
    private final String FAN5_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan5", "username");
    private final String FAN6_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan6", "username");
    private final String FAN7_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan7", "username");
    private final String FAN8_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan8", "username");
    private final String FAN9_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan9", "username");
    private final String FAN10_TO_BE_SELECTED = PropertyUtils.getProperty("username.fan10", "username");

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

        UserProfilePO fanProfilePO = fansPO.selectFan(FAN_TO_BE_SELECTED);
        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);

        fanProfilePO = fansPO.selectFan(FAN2_TO_BE_SELECTED);
        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);

//        fanProfilePO = fansPO.selectFan(FAN3_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);

//        fanProfilePO = fansPO.selectFan(FAN4_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);

//        fanProfilePO = fansPO.selectFan(FAN5_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);
//
        fanProfilePO = fansPO.selectFan(FAN6_TO_BE_SELECTED);
        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);

//        fanProfilePO = fansPO.selectFan(FAN7_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);
//
//        fanProfilePO = fansPO.selectFan(FAN8_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);
//
//        fanProfilePO = fansPO.selectFan(FAN9_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);
//
//        fanProfilePO = fansPO.selectFan(FAN10_TO_BE_SELECTED);
//        fanProfilePO.commentOnFirstVideo(COMMENT_ICON_X, COMMENT_ICON_Y, COMMENT_TEXT);

    }

}
