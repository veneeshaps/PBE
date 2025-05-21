package com.epam.training_portal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.epam.training_portal.base.BasePage;

public class DashboardPage extends BasePage {

    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//div[@aria-label='Sign in with manasvi_p@epam.com work or school account.']")
    private WebElement signInWorkAccountButton;

    @FindBy(css = "a.flex.items-center.p-2.text-[#303240].rounded-lg.hover\\:bg-gray-100.bg-[#EBEDF5]")
    private WebElement dashboardLink;

    @FindBy(css = "li:nth-child(2) a")
    private WebElement profilesLink;

    @FindBy(css = "li:nth-child(3) a")
    private WebElement eventsLink;

    @FindBy(css = "li:nth-child(4) a")
    private WebElement attendanceLink;

    @FindBy(css = "li:nth-child(5) a")
    private WebElement userDashboardLink;

    @FindBy(css = "li:nth-child(7) a")
    private WebElement benchLeaderboardLink;

    @FindBy(css = "li:nth-child(8) a")
    private WebElement reportsLink;

    @FindBy(css = "li:nth-child(9) a")
    private WebElement resumeLink;

    @FindBy(css = "button.flex.items-center.justify-center.w-full.hover\\:bg-gray-100.rounded-lg.p-2.cursor-pointer.gap-2")
    private WebElement collapseMenuButton;

    // Methods

    public void clickOnSignInWorkAccountButton() {
        clickElement(signInWorkAccountButton, "Sign In Work Account Button");
    }

    public void clickOnDashboardLink() {
        clickElement(dashboardLink, "Dashboard Link");
    }

    public void clickOnProfilesLink() {
        clickElement(profilesLink, "Profiles Link");
    }

    public void clickOnEventsLink() {
        clickElement(eventsLink, "Events Link");
    }

    public void clickOnAttendanceLink() {
        clickElement(attendanceLink, "Attendance Link");
    }

    public void clickOnUserDashboardLink() {
        clickElement(userDashboardLink, "User Dashboard Link");
    }

    public void clickOnBenchLeaderboardLink() {
        clickElement(benchLeaderboardLink, "Bench Leaderboard Link");
    }

    public void clickOnReportsLink() {
        clickElement(reportsLink, "Reports Link");
    }

    public void clickOnResumeLink() {
        clickElement(resumeLink, "Resume Link");
    }

    public void clickOnCollapseMenuButton() {
        clickElement(collapseMenuButton, "Collapse Menu Button");
    }

    public boolean isDashboardLinkDisplayed() {
        return isElementDisplayed(dashboardLink);
    }

    public boolean isMenuCollapsed() {
        String collapseButtonState = collapseMenuButton.getAttribute("aria-expanded");
        return collapseButtonState != null && collapseButtonState.equalsIgnoreCase("false");
    }
    public void clickOnUserDashboard(){ clickElement(userDashboardLink, "UserDashboard Link"); }

}