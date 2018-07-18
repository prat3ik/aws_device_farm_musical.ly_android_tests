package pageobjects;

import io.appium.java_client.AppiumDriver;

public class FansPO extends  BasePO {
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
}
