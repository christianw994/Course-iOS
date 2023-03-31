package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import views.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    public static IOSDriver<MobileElement> driver;

    public DesiredCapabilities capabilities = new DesiredCapabilities();

    protected Helpers helpers;

    protected HomePageView homePageView;
    protected CalendarPageView calendarPageView;

    @BeforeMethod
    public void setUp() throws IOException {

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 14 Pro Max");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.APP, "com.apple.mobilecal");
        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);

        driver = new IOSDriver<>(new URL(GlobalVariables.localAppiumServerUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        helpers = new Helpers();

        homePageView = new HomePageView(driver);
        calendarPageView = new CalendarPageView(driver);
    }
}
