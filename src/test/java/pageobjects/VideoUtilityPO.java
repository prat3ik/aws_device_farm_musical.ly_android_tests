package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

public class VideoUtilityPO extends BasePO {

    public VideoUtilityPO(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/sf")
    AndroidElement videoRecordButton;

    protected void recordVideo(int duration) {
        new TouchAction(driver).press(PointOption.point((int) (videoRecordButton.getSize().getHeight() * 0.5),
                (int) (videoRecordButton.getSize().getWidth() * 0.5))).
                waitAction(waitOptions(ofSeconds(duration))).release().perform();
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ul")
    AndroidElement recordedVideoSaveButton;

    public PostPO uploadRecordedVideo() {
        recordVideo(4);
        recordedVideoSaveButton.click();
        nextButton2.click();
        return new PostPO(driver);
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/v7")
    AndroidElement uploadButton;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']")
    AndroidElement firstVideo;

    protected void tapOnUploadButton() {
        uploadButton.click();
    }

    protected void uploadFirstVideo() {
        tapOnUploadButton();
        firstVideo.click();
    }

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ki")
    AndroidElement nextButton1;

    @AndroidFindBy(id = "com.zhiliaoapp.musically:id/ic")
    AndroidElement nextButton2;
}
