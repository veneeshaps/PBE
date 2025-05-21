package com.epam.training_portal.pages;

import com.epam.training_portal.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UserDashBoardPage extends BasePage {
    WebDriverWait wait;
    public UserDashBoardPage(WebDriver driver){
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath = "//a[@href='/user/dashboard']")
    private WebElement userDashboardLink;

    @FindBy(xpath = "//button[normalize-space()='Get Attendance']")
    private WebElement getAttendanceButton;

    public void clickUserDashboard() {
        userDashboardLink.click();
    }

    public boolean isGetAttendanceButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(getAttendanceButton));
        return getAttendanceButton.isDisplayed();
    }

}
