package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import utils.AppiumUtils;

import java.util.List;

public class FansPO extends BasePO {
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
    protected FansPO(AppiumDriver driver) {
        super(driver);
    }

    /**
     * This method will return the Fan from FanId(Starting with '@')
     *
     * @param fanId
     * @return
     */
    protected AndroidElement getFan(String fanId) {
        List<AndroidElement> fansIdElements = driver.findElements(By.id("com.zhiliaoapp.musically:id/al4"));
        boolean isFanFound = false;
        String lastFan1 = "";
//        System.out.println(fansIdElements);
        while (!isFanFound) {
//            for (AndroidElement el : fansIdElements) {
//                System.out.println("SIZE:::"+fansIdElements.size());
//                System.out.println(el.getText());
//            }

            lastFan1 = fansIdElements.get(fansIdElements.size() - 1).getText();
            for (AndroidElement el : fansIdElements) {
                if (fanId.equals(el.getText())) {
                    return el;
                }
            }
            AppiumUtils.verticalScroll(driver);
            fansIdElements = driver.findElementsById("com.zhiliaoapp.musically:id/al4");
            String lastFan2 = fansIdElements.get(fansIdElements.size() - 1).getText();
            if (lastFan1.equals(lastFan2)) {
                System.out.println("Breaking the loop");
                break;
            }
        }
        return null;
    }

    protected AndroidElement getFan(int fanIndex) {
        List<AndroidElement> fansIdElements = driver.findElements(By.id("com.zhiliaoapp.musically:id/al4"));
        return fansIdElements.get(fanIndex);
    }

    /**
     * This method will select the Fan from FanId(Starting with '@')
     *
     * @param fanId
     */
    public UserProfilePO selectFan(String fanId) {
        getFan(fanId).click();
        System.out.println(fanId + " : selected");
        return new UserProfilePO(driver);
    }


    public void selectFans(int noOfFans, List<String> commentsForFans) {
        List<AndroidElement> fansIdElements = driver.findElements(By.id("com.zhiliaoapp.musically:id/al4"));
        System.out.println("Test");
        for(AndroidElement el : fansIdElements){
            System.out.println(el);
        }
        System.out.println("Elements::"+ fansIdElements);
        for (int i = 0; i < noOfFans; i++) {
            System.out.println(fansIdElements.get(i).getText() + " : selecting...");
            fansIdElements.get(i).click();
            new UserProfilePO(driver).commentOnFirstVideo(commentsForFans.get(i));

        }
    }
}
