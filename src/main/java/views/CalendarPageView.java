package views;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;
import util.Helpers;

import java.time.Duration;

public class CalendarPageView extends Helpers {

    protected IOSDriver<MobileElement> driver;

    @iOSXCUITFindBy(accessibility = "Calendar")
    private MobileElement calendarAppContainer;

    @iOSXCUITFindBy(accessibility = "Allow Once")
    private MobileElement allowPermissions;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`label == \"All-day\"`]")
    private MobileElement allDaySwitchButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Starts']/preceding-sibling::XCUIElementTypeButton[@name='Date and Time Picker'])/XCUIElementTypeButton[2]")
    private MobileElement startsWheelTimePicker;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeDatePicker")
    private MobileElement datePickerWheelElement;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[1]")
    private MobileElement hoursPickerWheelSelector;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[2]")
    private MobileElement minutesPickerWheelSelector;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[3]")
    private MobileElement amPmWheelSelector;

    public CalendarPageView(IOSDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean calendarPageViewLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarAppContainer)).isDisplayed();
    }

    public void allowLocationPermissions() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(allowPermissions)).click();
    }

    public void clickCreateNewEvent(String time) {

        new TouchAction(driver)
                .press(PointOption.point(getElementCoordinates(driver.findElementByXPath(String.format("//XCUIElementTypeOther[contains(@name, '%s')]", time)))[0], getElementCoordinates(driver.findElementByXPath(String.format("//XCUIElementTypeOther[@name='%s']", time)))[1]))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .release()
                .perform();
    }

    public void switchAllDayButtonTrue() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(allDaySwitchButton));
        if (allDaySwitchButton.getAttribute("value").equals("0"))
            allDaySwitchButton.click();
    }

    public void setHourMinuteDatePickerWheel(String hour, String min, String amPm) {
        startsWheelTimePicker.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(datePickerWheelElement));
        hoursPickerWheelSelector.sendKeys(hour);
        minutesPickerWheelSelector.sendKeys(min);
        amPmWheelSelector.sendKeys(amPm);
    }
}
