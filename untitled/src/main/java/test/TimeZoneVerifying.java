package test;

import core.BaseTest;
import org.testng.Assert;
import pages.LiveScorePage;
import utils.Constants;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TimeZoneVerifying extends BaseTest {
    public static void main(String[] args) throws Exception {
        BaseTest base = new BaseTest();
        LiveScorePage Action = new LiveScorePage();
        Random random = new Random();

        String eventTime;
        String eventDay;
        String timezoneValue;
        String newTimeEvent;

        // Step 1
        base.setUp();

        // Step 2
        Action.clickCrossButton();
        Action.clickNextDayEventButton();
        Action.clickEventForm();

        // Step 3
        eventTime = Action.getEventTimeValue();
        eventDay = Action.getEventDayValue();

        // Step 4
        Action.clickOnLeftMenuButton();

        // Step 5
        Action.clickOnSettingsMenuButton();

        // Step 6
        Action.setRandomTimezone(random.nextInt(6) + 3);
        timezoneValue = Action.getTimezoneValue();
        Action.clickApplyButton();

        // Step 7
        newTimeEvent = Action.getEventTimeValue();
        Assert.assertTrue(Action.compareTimeEvent(eventTime, timezoneValue, newTimeEvent), "Test passed");

        base.tearDown();
    }
}
