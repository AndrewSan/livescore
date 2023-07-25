package pages;

import core.PageTools;
import org.openqa.selenium.By;
import utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LiveScorePage extends PageTools {

    private By crossButton = By.xpath("(//span//img[@data-nimg='intrinsic'])[1]");
    private By nextDayEventButton = By.xpath("(//div[@class]//a[4])[2]");
    private By firstEventForm = By.xpath("/html/body/div[2]/div[2]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/div/div/div/div[1]/div[2]/a/div");
    private By eventTimeValue = By.xpath("//span[@id='score-or-time']");
    private By eventDayValue = By.xpath("//span[@id='SEV__status']");
    private By leftMenuButton = By.xpath("(//span[@id='burger-menu-open']//img)[2]");
    private By settingsMenuButton = By.xpath("//a[@id='burger-menu__settings']");
    private By timezoneValueLabel = By.xpath("//label[@id='TZ_SELECT-label']");
    private By timezoneSelectItem = By.xpath("(//div[@class='Ui selectItem'])[%s]");
    private By applyButton = By.xpath("//button[@data-testid='settings-form_apply-button']");

    public boolean compareTimeEvent(String startTime, String newTime, String result) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date defaultDate = sdf.parse(Constants.DEFAULT_TIME);
        Date firstDate = sdf.parse(startTime);
        Date secondDate = sdf.parse(newTime);

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long bufTime = Math.abs(diffInMillies - defaultDate.getTime());
        long diff = TimeUnit.MILLISECONDS.toHours(bufTime);
        String buf = diff + "";
        return result.contains(buf);
    }

    public void clickCrossButton(){
        if(isElementVisible(crossButton))
            click(crossButton);
    }

    public void clickNextDayEventButton(){
        waitForElementVisibility(nextDayEventButton);
        click(nextDayEventButton);
    }

    public void clickEventForm(){
        waitForElementVisibility(firstEventForm);
        click(firstEventForm);
    }

    public String getEventTimeValue(){
        waitForElementVisibility(eventTimeValue);
        return getElementsText(eventTimeValue).toString().replace("[", "").replace("]", "");
    }

    public String getEventDayValue(){
        waitForElementVisibility(eventDayValue);
        return getElementsText(eventDayValue).toString().replace("[", "").replace("]", "");
    }

    public void clickOnLeftMenuButton(){
        waitForElementVisibility(leftMenuButton);
        click(leftMenuButton);
    }

    public void clickOnSettingsMenuButton(){
        waitForElementVisibility(settingsMenuButton);
        click(settingsMenuButton);
    }

    public void setRandomTimezone(Integer number){
        waitForElementVisibility(timezoneValueLabel);
        click(timezoneValueLabel);
        waitForElementClickable(timezoneSelectItem, number);
        click(timezoneSelectItem, number);
    }

    public void clickApplyButton(){
        waitForElementVisibility(applyButton);
        click(applyButton);
    }

    public String getTimezoneValue(){
        waitForElementVisibility(timezoneValueLabel);
        return getElementsText(timezoneValueLabel).toString().replace("[UTC -", "").replace("]", "");
    }

}
