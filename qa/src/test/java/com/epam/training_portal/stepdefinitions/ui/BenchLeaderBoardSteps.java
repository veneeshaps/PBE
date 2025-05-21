package com.epam.training_portal.stepdefinitions.ui;

import com.epam.training_portal.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import com.epam.training_portal.pages.BenchLeaderBoardPage;
import com.epam.training_portal.pages.DashboardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Objects;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BenchLeaderBoardSteps {

    private BenchLeaderBoardPage benchLeaderBoardPage;
    private DashboardPage dashboardPage;

    public BenchLeaderBoardSteps(){
        this.dashboardPage=new DashboardPage((DriverFactory.getDriver()));
        this.benchLeaderBoardPage =new BenchLeaderBoardPage(DriverFactory.getDriver());
    }
    @Given("That user is on the landing page")
    public void ThatUserIsOnTheLandingPage(){
        String actualTitle= benchLeaderBoardPage.getTitleText();
        String expectedTitle= " Pending Feedback Events ";
        expectedTitle= expectedTitle.trim();
        Assert.assertEquals(actualTitle,expectedTitle,"User not on dashboard");

    }

    @Then("user navigates to Bench Leaderboard")
    public void userNavigatesToBenchLeaderboard() {
        dashboardPage.clickOnBenchLeaderboardLink();
        //benchLeaderBoardPage.clickBenchLeaderBoard();
        String actualTitle = benchLeaderBoardPage.getTitleBenchLeaderboard();
        String expectedTitle = " Bench Leaderboard ";
        expectedTitle = expectedTitle.trim();
        Assert.assertEquals(actualTitle, expectedTitle,"User did not navigate to Bench LeaderBoard");
    }

    @Given("The user signs in by clicking onto their account link")
    public void theUserSignsInByClickingOntoTheirAccountLink() {
        benchLeaderBoardPage.clickAccount();
        String actualTitle= benchLeaderBoardPage.getTitleText();
        String expectedTitle= " Pending Feedback Events ";
        expectedTitle= expectedTitle.trim();
        Assert.assertEquals(actualTitle,expectedTitle,"User not on dashboard");

    }

    @When("The user click on Score Breakdown button")
    public void theUserClickOnScoreBreakdownButton() {
        benchLeaderBoardPage.clickScoreBreakdownButton();
        String actualTitle = benchLeaderBoardPage.getTitleScoreBreakdown();
        String expectedTitle = " Score Breakdown ";
        expectedTitle= expectedTitle.trim();
        Assert.assertEquals(actualTitle,expectedTitle,"Score Breakdown form not visible");

    }

    @Then("Score Breakdown form should be visible")
    public void scoreBreakdownFormShouldBeVisible() {
        benchLeaderBoardPage.getTitleScoreBreakdown();
        String actualTitle = benchLeaderBoardPage.getTitleScoreBreakdown();
        String expectedTitle = " Score Breakdown ";
        expectedTitle= expectedTitle.trim();
        Assert.assertEquals(actualTitle,expectedTitle,"Score Breakdown form not visible");

    }

    @Then("user enters {string} percentage as {string}")
    public void userEntersPercentageAs(String section, String value) throws InterruptedException {
        if (Objects.equals(section, "Codility")) benchLeaderBoardPage.setCodility(value);
        else if (Objects.equals(section, "Gen AI Kata")) benchLeaderBoardPage.setGenAIKata(value);
        else if (Objects.equals(section, "Tech Talk")) benchLeaderBoardPage.setTechTalk(value);
    }

    @And("user clicks on save button")
    public void userClicksOnSaveButton() {
        benchLeaderBoardPage.clickSaveButton();
        String actualText = benchLeaderBoardPage.getUpdateText();
        String expectedText = "Weightages updated successfully!";
        Assert.assertEquals(actualText,expectedText,"Scores could not get updated");

    }

    @When("I select the All Domains dropdown")
    public void iSelectTheAllDomainsDropdown() {
        benchLeaderBoardPage.clickDomainDropdown();
    }

    @Then("I select {string} from the dropdown")
    public void iSelectDomainFromTheDropdown(String domain) {
        // Dynamically select the domain
        benchLeaderBoardPage.selectDomain(domain);
    }

    @Then("the profiles displayed on the table should belong to the {string} domain")
    public void verifyProfilesDisplayedBelongToDomain(String domain) {
        // Verify that all rows in the table belong to the selected domain
        boolean allProfilesBelongToSelectedDomain = benchLeaderBoardPage.verifyProfilesBelongToDomain(domain);
        assertTrue("Not all profiles belong to the domain: " + domain, allProfilesBelongToSelectedDomain);
    }

    @Then("the total number of profiles displayed should match the available count for the {string} domain")
    public void verifyProfilesCountMatchesDomain(String domain) {
        // Fetch the count of rows displayed in the table
        int displayedCount = benchLeaderBoardPage.getDisplayedProfilesCountForDomain(domain);

        // Fetch the total count from pagination info
        int totalCountFromPagination = benchLeaderBoardPage.getExpectedProfilesCountFromPagination();

        // Assert that the displayed count matches the total count from pagination info
        assertEquals("Displayed profiles count does not match the total count in pagination info for domain: " + domain,
                totalCountFromPagination, displayedCount);
    }

    @When("I click on the Export button")
    public void iClickOnTheExportButton() {
        // Call the method to click on the Export button
        benchLeaderBoardPage.clickExportButton();
        System.out.println("Export button clicked.");
    }

    @Then("a {string} message should appear confirming the file download")
    public void aMessageShouldAppearConfirmingTheFileDownload(String expectedMessage) {
        assertTrue("The success message is not displayed!", benchLeaderBoardPage.isSuccessMessageDisplayed());
    }

    @Then("an error message should be displayed saying {string}")
    public void anErrorMessageShouldBeDisplayedSaying(String expectedText) {
        String actualText = benchLeaderBoardPage.getErrorText();
        assertEquals("The error message does not match the expected message!", expectedText, actualText);
        System.out.println("Error message validation passed: " + actualText);
    }

}
