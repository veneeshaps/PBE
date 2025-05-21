package com.epam.training_portal.context;

import com.epam.training_portal.pages.DashboardPage;
import com.epam.training_portal.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

public class TestContextUI {

    private WebDriver driver;

    // Lazy-loaded Page Objects
    private DashboardPage dashboardPage;

    /**
     * Constructor initializes driver from DriverFactory.
     * Lazy Initialization ensures driver will only be fetched when needed.
     */
    public TestContextUI() {
        if (driver == null) {
            this.driver = DriverFactory.getDriver(); // Thread-safe WebDriver instance
        }
    }

    /**
     * Initializes and returns the DashboardPage instance.
     */
    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(driver); // Lazy initialization
        }
        return dashboardPage;
    }

    /**
     * Getter for WebDriver instance.
     */

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Quits the driver, ensuring cleanup after the test.
     */
    public void quitDriver() {
        DriverFactory.quitDriver();
    }
}
//package com.epam.training_portal.context;
//
//import com.epam.training_portal.pages.*;
//import com.epam.training_portal.utils.DriverFactory;
//import org.openqa.selenium.WebDriver;
//
//public class TestContextUI {
//    private final WebDriver driver;
//
//    private DashboardPage dashBoard;
//    private Object requestPayload;
//    public TestContextUI() {
//        this.driver = DriverFactory.getDriver();
//    }
//
//    public Object getRequestPayload() {
//        return requestPayload;
//    }
//
//    public void setRequestPayload(Object requestPayload) {
//        this.requestPayload = requestPayload;
//    }
//
//    public DashboardPage getLandingPage() {
//
//        if (dashBoard == null) dashBoard = new DashboardPage(driver);
//        return dashBoard;
//    }
//}
