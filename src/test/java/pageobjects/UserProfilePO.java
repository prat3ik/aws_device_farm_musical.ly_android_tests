package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.AppiumUtils;

public class UserProfilePO extends BasePO {
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
    public UserProfilePO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/a4t")
    AndroidElement fansCountTextView;

    public AndroidElement getFansCountTextView() {
        return fansCountTextView;
    }

    public FansPO tapOnFansCount() {
        fansCountTextView.click();
        return new FansPO(driver);
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No videos yet']")
    AndroidElement noVideosYetTextView;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Private Account']")
    AndroidElement privateAccountTextView;

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ahb")
    AndroidElement firstVideoFromPost;

    protected UserVideoPostPO selectFirstVideoFromUserPosts() {
        waitUtils.staticWait(1500);
        waitUtils.waitForElementToBeVisible(fansCountTextView, driver);
        if (AppiumUtils.isElementDisplayed(firstVideoFromPost)) {
            System.out.println("Selecting first video from list:" + firstVideoFromPost);
            firstVideoFromPost.click();
            return new UserVideoPostPO(driver);
        } else if (AppiumUtils.isElementDisplayed(privateAccountTextView))
            System.out.println("This is Private Account, so you can not see any post for this user");
        else if (AppiumUtils.isElementDisplayed(noVideosYetTextView)) {
            System.out.println("There are no videos");
        }
        return null;
    }


    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ik")
    AndroidElement backButton;

    public void tapOnBackButton() {
        if (backButton.isDisplayed())
            backButton.click();
    }

    public void commentOnFirstVideo(int comment_icon_x, int comment_icon_y, String commentText) {
        UserVideoPostPO videoPostPO = selectFirstVideoFromUserPosts();
        System.out.println(videoPostPO);
        if (videoPostPO != null) {
            boolean isCommentIsDisabled = videoPostPO.tapOnCommentIcon(comment_icon_x, comment_icon_y);
            System.out.println("Is Comment Disabled:" + isCommentIsDisabled);
            if (isCommentIsDisabled) {
                videoPostPO.tapOnCloseCommentViewButton();
                videoPostPO.tapOnBackArrowFromPostScreen();
            }
            else
                videoPostPO.postComment(commentText);
        }
        this.tapOnBackButton();
    }
}