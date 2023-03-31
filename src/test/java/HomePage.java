import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;
import util.Helpers;

public class HomePage extends DriverSetup {

    @Test(testName = "Home Page")
    public void homePage() {

        Assert.assertTrue(homePageView.homePageViewLoaded(), "Home page view isn't loaded");

        homePageView.computeSum(10, 50);
        Assert.assertEquals(homePageView.getSumAnswer(), "60");

        homePageView.setSliderAppElemValue("0.7");
        //TODO: Report bug 70% shows as 72
        Assert.assertEquals(homePageView.getSliderAppElemValue(), "72%");

        Assert.assertFalse(homePageView.getDisabledButtonState());

        homePageView.clickTestGestureButton();
        Assert.assertTrue(homePageView.mapElement.isDisplayed(), "Map isn't displayed");
        Assert.assertFalse(homePageView.sumAnswerTextField.isDisplayed());

        helpers.swipe(driver, Helpers.DIRECTION.UP, 500);
        Assert.assertTrue(homePageView.sumAnswerTextField.isDisplayed());

        homePageView.clickShowAlertButton();
        homePageView.clickCancelPopupButton();


    }
}
