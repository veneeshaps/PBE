package com.epam.training_portal.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected Logger logger;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger = LogManager.getLogger(this.getClass());
    }

    // Common helper method to click on an element with validation and logging
    protected void clickElement(WebElement element, String elementName) {
        try {
            waitForElementToBeVisible(element, 10);
            element.click();
            logger.info("Clicked on: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to click on: " + elementName, e);
            throw new RuntimeException("Failed to click on: " + elementName, e);
        }
    }

    // Common method to wait explicitly for an element to be visible
    protected void waitForElementToBeVisible(WebElement element, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Element not visible after waiting: " + element, e);
            throw new RuntimeException("Element not visible: " + element, e);
        }
    }

    // Common method to check if an element is displayed
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not found or not displayed: " + element);
            return false;
        }
    }

    // Common method to set a value in a dropdown using Select class
    protected void setDropdownValue(WebElement dropdownElement, String value, String dropdownDescription) {
        try {
            waitForElementToBeVisible(dropdownElement, 5);
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByVisibleText(value);
            logger.info("Selected value '" + value + "' from " + dropdownDescription);
        } catch (Exception e) {
            logger.error("Failed to set value '" + value + "' for dropdown: " + dropdownDescription, e);
            throw new RuntimeException("Failed to set value '" + value + "' for dropdown: " + dropdownDescription, e);
        }
    }
}