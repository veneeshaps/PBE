package com.epam.training_portal.stepdefinitions.api;

import com.epam.training_portal.api.manager.ResponseSpecManager;
import com.epam.training_portal.api.requests.GetRequestHandler;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.epam.training_portal.api.routes.ApiEndpoints;
import com.epam.training_portal.context.TestContextAPI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


public class QRCodeSteps {

    private TestContextAPI context;

    private Response response;

    public QRCodeSteps(TestContextAPI context) {
        this.context = context;
    }

    @When("I send a GET request with event ID {string}")
    public void iSendAGETRequestToWithEventID(String eventId) {
        response = GetRequestHandler.getWithQuery(ApiEndpoints.AttendanceReports.QR_GENERATION, "eventId", eventId);
        context.setResponse(response);

    }

    @Then("the status code is {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // assertEquals(expectedStatusCode, response.getStatusCode());
        response = context.getResponse();
        response.then().spec(ResponseSpecManager.getResponseSpec(expectedStatusCode));
    }


    @And("the response body should contain a url")
    public void theResponseShouldContainA() {
        assertNotNull("No qr found", response.jsonPath().getString("qrCode"));
    }

    @When("I send a GET request with invalid event ID {string}")
    public void iSendAGETRequestToWithInvalidEventID(String eventId) {
        response = GetRequestHandler.getWithQuery(ApiEndpoints.AttendanceReports.QR_GENERATION, "eventId", eventId);
        context.setResponse(response);
    }

    @And("the response body should contain an error message")
    public void theResponseBodyShouldContainAnErrorMessage() {
        System.out.println(response.jsonPath().getString("error")); // for my reference
        assertNotNull("No error message found", response.jsonPath().getString("error"));

    }

}