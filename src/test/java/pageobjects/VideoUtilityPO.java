package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class VideoUtilityPO extends BasePO {

    public VideoUtilityPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/sf")
    AndroidElement videoRecordButton;

    protected void recordVideo(int duration) {
        new TouchAction<>(driver).press(ElementOption.element(videoRecordButton)).waitAction(WaitOptions.waitOptions((Duration.ofSeconds(duration)))).release().perform();
//        new TouchAction<>(driver).longPress(longPressOptions()
//                .withElement(element(videoRecordButton))).waitAction(waitOptions(Duration.ofSeconds(duration)))
//               // .moveTo(element(videoRecordButton))
//                .perform().release();//
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ul")
    AndroidElement recordedVideoSaveButton;

    public PostPO uploadRecordedVideo() {
        recordVideo(5);
        waitUtils.waitForElementToBeVisible(recordedVideoSaveButton, driver);
        recordedVideoSaveButton.click();
//        waitUtils.waitForElementToBeVisible(nex);
        waitUtils.staticWait(5000);
        tapOnNextButton2();
        return new PostPO(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/v7")
    AndroidElement uploadButton;

    protected void tapOnUploadButton() {
        uploadButton.click();
    }

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']")
    AndroidElement firstVideo;

    protected void uploadFirstVideo() {
        tapOnUploadButton();
        firstVideo.click();
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ki")
    AndroidElement nextButton1;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    AndroidElement nextButton2;

    public void tapOnNextButton2(){
//        waitUtils.waitForElementToBeVisible(nextButton2, driver);
        nextButton2.click();
    }
}
