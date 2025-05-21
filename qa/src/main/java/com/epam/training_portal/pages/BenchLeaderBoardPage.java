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

public class BenchLeaderBoardPage extends BasePage {
    WebDriverWait wait;
    public BenchLeaderBoardPage(WebDriver driver){
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath = "//a[@href='/leaderboard']")
    private WebElement leaderboardLink;

    @FindBy(css = "h3.text-xl.font-semibold")
    private WebElement pendingFeedbackHeader;

    @FindBy(xpath = "//a[@href='/leaderboard']")
    private WebElement benchLeaderboardDiv;

    @FindBy(xpath = "//div[@data-test-id='madala_veneesha@epam.com']")
    private WebElement parentDiv;

    @FindBy(xpath = "//button[normalize-space(text())='Score Breakdown']")
    private WebElement scoreBreakdownButton;

    @FindBy(xpath = "//h3[normalize-space(text())='Score Breakdown']")
    private WebElement scoreBreakdownHeader;

    @FindBy(xpath = "//label[normalize-space(text())='Codility (%)']/following-sibling::input[@formcontrolname='percentage']")
    private WebElement codilityInput;

    @FindBy(xpath = "//label[normalize-space(text())='Gen AI Kata (%)']/following-sibling::input[@formcontrolname='percentage']")
    private WebElement genAIInput;

    @FindBy(xpath = "//label[normalize-space(text())='Tech Talk (%)']/following-sibling::input[@formcontrolname='percentage']")
    private WebElement techTalkInput;

    @FindBy(xpath = "//button[normalize-space(text())='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[text()='Weightages updated successfully!']")
    private WebElement successMessage;

    @FindBy(xpath = "(//select)[1]")
    private WebElement domainDropdown;

    @FindBy(xpath = "//table/tbody/tr") // Table rows
    private List<WebElement> tableRows;

    @FindBy(xpath = "//button[.//text()[contains(., 'Export to Excel')]]")
    private WebElement exportButton;

    @FindBy(xpath = "//div[text()='File Downloaded successfully!']")
    private WebElement downloadSuccess;

    @FindBy(xpath = "//div[text()='Total must be equal to 100.']")
    private WebElement errorMessage;


    public void clickBenchLeaderBoard(){
        leaderboardLink.click();
    }

    public String getTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pendingFeedbackHeader));
        return pendingFeedbackHeader.getText().trim();

    }

    public String getTitleBenchLeaderboard() {
        wait.until(ExpectedConditions.visibilityOf(benchLeaderboardDiv));
        return benchLeaderboardDiv.getText().trim();
    }

    public void clickAccount() {
        wait.until(ExpectedConditions.visibilityOf(parentDiv));
        parentDiv.click();
    }

    public void clickScoreBreakdownButton() {
        wait.until(ExpectedConditions.visibilityOf(scoreBreakdownButton));
        scoreBreakdownButton.click();
    }

    public String getTitleScoreBreakdown(){
        wait.until(ExpectedConditions.visibilityOf(scoreBreakdownHeader));
        return scoreBreakdownHeader.getText().trim();

    }

    public void setCodility(String value) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(codilityInput));
        codilityInput.clear();
        codilityInput.sendKeys(value);
        //Thread.sleep(3000);
    }

    public void setGenAIKata(String value) {
        wait.until(ExpectedConditions.visibilityOf(genAIInput));
        genAIInput.clear();
        genAIInput.sendKeys(value);
    }

    public void setTechTalk(String value) {
        wait.until(ExpectedConditions.visibilityOf(techTalkInput));
        techTalkInput.clear();
        techTalkInput.sendKeys(value);
    }

    public void clickSaveButton(){
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        saveButton.click();

    }

    public String getUpdateText(){
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText().trim();

    }

    public void clickDomainDropdown(){
        wait.until(ExpectedConditions.visibilityOf(domainDropdown));
        domainDropdown.click();
    }

    // Method: Select a domain dynamically from the dropdown
    public void selectDomain(String domain) {
        String xpathForDomainOption;
        if (domain.equalsIgnoreCase("Java")) {
            xpathForDomainOption = "(//option)[3]"; // XPath for Java
        } else if (domain.equalsIgnoreCase(".NET")) {
            xpathForDomainOption = "(//option)[2]"; // XPath for .NET
        } else {
            throw new IllegalArgumentException("Domain " + domain + " is not supported.");
        }

        WebElement domainOption = driver.findElement(By.xpath(xpathForDomainOption));
        wait.until(ExpectedConditions.elementToBeClickable(domainOption));
        domainOption.click();
    }

    // Method: Verify all profiles belong to the selected domain
    public boolean verifyProfilesBelongToDomain(String domain) {
        List<WebElement> domainCells = driver.findElements(By.xpath("//td[@class='highlight-domain' and text()='" + domain + "']"));

        // Validate all rows contain the correct domain
        for (WebElement cell : domainCells) {
            String cellDomainText = cell.getText().trim();
            if (!cellDomainText.equals(domain)) {
                return false; // If one cell doesn't match, validation fails
            }
        }
        return true; // All rows match the domain
    }

    // Method: Get the count of rows displayed for the selected domain
    public int getDisplayedProfilesCountForDomain(String domain) {
        return driver.findElements(By.xpath("//td[@class='highlight-domain' and text()='" + domain + "']")).size();
    }

    // Method: Extract the total count from the pagination info dynamically
    public int getExpectedProfilesCountFromPagination() {
        // Locator for pagination info
        By paginationInfoLocator = By.xpath("//div[@class='pagination-info' and contains(text(), 'Showing')]");

        // Wait for the element and retrieve its text
        WebElement paginationInfoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paginationInfoLocator));
        String paginationText = paginationInfoElement.getText(); // Example: "Showing 1 to 5 of 5 entries"

        // Extract the total count using a regex
        return Integer.parseInt(paginationText.replaceAll(".*of\\s(\\d+)\\sentries.*", "$1")); // Extract number after 'of'
    }
    public void clickExportButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exportButton));
        exportButton.click();
    }

    // Method: Check if Success Message is Displayed
    public boolean isSuccessMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(downloadSuccess));
        System.out.println(downloadSuccess.isDisplayed());
        return downloadSuccess.isDisplayed(); // Return true if the message is shown
    }

    // Method: Get the Text of the Success Message
    public String getSuccessMessageText() {
        wait.until(ExpectedConditions.visibilityOf(downloadSuccess));
        return downloadSuccess.getText().trim(); // Fetch the message text
    }

    public String getErrorText(){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().trim();

    }
}
