package views;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class HomePageView {

    protected IOSDriver<MobileElement> driver;

    @iOSXCUITFindBy(accessibility = "Compute Sum")
    private MobileElement computeSumButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"TextField1\"`]")
    private MobileElement integerATextField;

    @iOSXCUITFindBy(accessibility = "IntegerB")
    private MobileElement integerBTextField;

    @iOSXCUITFindBy(accessibility = "Answer")
    public MobileElement sumAnswerTextField;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSlider[`label == \"AppElem\"`]")
    private MobileElement appElemSliderButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"disabled button\"`]")
    private MobileElement disabledButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"Test Gesture\"`]")
    private MobileElement testGestureButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeMap")
    public MobileElement mapElement;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"show alert\"`]")
    private MobileElement showAlertButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert")
    private MobileElement coolTitleAlert;

    @iOSXCUITFindBy(accessibility = "Cancel")
    private MobileElement cancelPopupButton;

    public HomePageView(IOSDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean homePageViewLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(computeSumButton)).isDisplayed();
    }

    public void clickComputeSumButton() {
        computeSumButton.click();
    }

    public void computeSum(int integerA, int integerB) {
        integerATextField.click();
        integerATextField.sendKeys(Integer.toString(integerA));
        integerBTextField.click();
        integerBTextField.sendKeys(Integer.toString(integerB));
        driver.hideKeyboard();
        computeSumButton.click();
    }

    public String getSumAnswer() {
        return sumAnswerTextField.getAttribute("value");
    }

    public void setSliderAppElemValue(String percentage) {
        appElemSliderButton.sendKeys(percentage);
    }

    public String getSliderAppElemValue() {
        return appElemSliderButton.getAttribute("value");
    }

    public boolean getDisabledButtonState() {
        return disabledButton.isEnabled();
    }

    public void clickTestGestureButton() {
        testGestureButton.click();
    }

    public void clickShowAlertButton() {
        showAlertButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(coolTitleAlert));
    }

    public void clickCancelPopupButton() {
        cancelPopupButton.click();
    }

    public boolean validateIfElementWithTextIsDisplayed(IOSDriver<MobileElement> driver, String text) {
        try {
            if (new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.xpath(String.format("(//XCUIElementTypeStaticText[@name=\"%s\"])[last()]", text))))) != null)
                return true;
        } catch (Exception e) {
            //Placeholder, exception should be not be thrown - element was found
        }
        return false;
    }
}
