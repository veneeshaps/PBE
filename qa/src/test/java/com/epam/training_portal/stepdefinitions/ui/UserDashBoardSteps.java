package com.epam.training_portal.stepdefinitions.ui;

import com.epam.training_portal.pages.UserDashBoardPage;
import com.epam.training_portal.utils.DriverFactory;
import com.epam.training_portal.pages.BenchLeaderBoardPage;
import com.epam.training_portal.pages.DashboardPage;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class UserDashBoardSteps {

    private UserDashBoardPage userDashBoardPage;
    private BenchLeaderBoardPage benchLeaderBoardPage;
    private DashboardPage dashboardPage;

    public UserDashBoardSteps(){
        this.dashboardPage=new DashboardPage((DriverFactory.getDriver()));
        this.benchLeaderBoardPage =new BenchLeaderBoardPage(DriverFactory.getDriver());
        this.userDashBoardPage =new UserDashBoardPage(DriverFactory.getDriver());
    }
    @Then("user navigates to User Dashboard")
    public void userNavigatesToUserDashboard() {
        userDashBoardPage.clickUserDashboard();
        boolean isVisible = userDashBoardPage.isGetAttendanceButtonVisible();
        Assert.assertTrue(isVisible, "Get Attendance button is not visible. User might not be on the User Dashboard.");
    }

}
