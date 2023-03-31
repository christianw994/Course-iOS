import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;

public class CalendarApp extends DriverSetup {

    @Test(testName = "Calendar")
    public void calendar() {

        calendarPageView.allowLocationPermissions();
        Assert.assertTrue(calendarPageView.calendarPageViewLoaded(), "Home page view isn't loaded");

        calendarPageView.clickCreateNewEvent("7:00 PM");
//        calendarPageView.switchAllDayButtonTrue();
        calendarPageView.setHourMinuteDatePickerWheel("7", "45", "AM");
    }
}
