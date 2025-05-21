package com.epam.training_portal.stepdefinitions.api;

import com.epam.training_portal.context.TestContextAPI;
import com.epam.training_portal.api.requests.GetRequestHandler;
import com.epam.training_portal.api.routes.ApiEndpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class DashboardSteps {

    private final TestContextAPI context;  // Use TestContextAPI for shared state

    public DashboardSteps(TestContextAPI context) {
        this.context = context;
    }

    @Given("the API endpoint is {string}")
    public void setApiEndpoint(String endpointKey) {
        Map<String, String> endpointMapping = Map.of(
                "PENDING_FEEDBACK_EVENTS", ApiEndpoints.Dashboard.PENDING_FEEDBACK_EVENTS,
                "FEEDBACK_SENT_EVENTS_BY_NAME", ApiEndpoints.Dashboard.FEEDBACK_SENT_EVENTS_BY_NAME,
                "FEEDBACK_SENT_EVENTS", ApiEndpoints.Dashboard.FEEDBACK_SENT_EVENTS,
                "PENDING_FEEDBACK_EVENTS_BY_NAME", ApiEndpoints.Dashboard.PENDING_FEEDBACK_EVENTS_BY_NAME
        );

        if (!endpointMapping.containsKey(endpointKey)) {
            throw new IllegalArgumentException("Invalid API endpoint key: " + endpointKey);
        }

        // Store endpoint in shared context
        context.addToPayload("apiEndpoint", endpointMapping.get(endpointKey));
        System.out.println("Resolved API Endpoint: " + endpointMapping.get(endpointKey));
    }

    @Given("I use the API endpoint for {string}")
    public void setDashboardEndpoint(String endpointKey) {
        Map<String, String> endpointMapping = Map.of(
                "DASHBOARD_ANALYTICS", ApiEndpoints.Dashboard.ANALYTICS,
                "INVALID_ANALYTICS", ApiEndpoints.Dashboard.INVALID_ANALYTICS,
                "DASHBOARD_METRICS", ApiEndpoints.Dashboard.METRICS,
                "INVALID_METRICS", ApiEndpoints.Dashboard.INVALID_ANALYTICS

        );

        if (!endpointMapping.containsKey(endpointKey)) {
            throw new IllegalArgumentException("Invalid API endpoint key: " + endpointKey);
        }

        // Store endpoint in shared context
        context.addToPayload("apiEndpoint", endpointMapping.get(endpointKey));
        System.out.println("Resolved API Endpoint: " + endpointMapping.get(endpointKey));
    }

    @Given("I set the query parameters:")
    public void setQueryParameters(Map<String, String> queryParams) {
        context.addToPayload("queryParams", queryParams); // Store query parameters in context
        System.out.println("Query Parameters: " + queryParams);
    }


    @Then("the response should contain event data")
    public void validateEventDataPresence() {
        // Get the response from the context
        Response response = context.getResponse();
        Assert.assertNotNull(response, "Response is null");

        // Parse the response and validate 'hasEventData' is true
        boolean hasEventData = response.jsonPath().getBoolean("hasEventData");
        Assert.assertTrue(hasEventData, "Expected 'hasEventData' to be true, but found false.");
    }

    @Then("the response should indicate upcoming events")
    public void validateUpcomingEvents() {
        // Get the response from the context
        Response response = context.getResponse();
        Assert.assertNotNull(response, "Response is null");

        // Validate 'hasUpcomingEvents' field
        boolean hasUpcomingEvents = response.jsonPath().getBoolean("hasUpcomingEvents");
        Assert.assertFalse(hasUpcomingEvents, "Expected 'hasUpcomingEvents' to be false, but found true.");

        // Validate that 'upcomingEvents' list matches 'hasUpcomingEvents'
        List<?> upcomingEvents = response.jsonPath().getList("upcomingEvents");
        if (hasUpcomingEvents) {
            Assert.assertTrue(upcomingEvents != null && !upcomingEvents.isEmpty(),
                    "Expected 'upcomingEvents' to contain data, but it was empty or null.");
        } else {
            Assert.assertTrue(upcomingEvents.isEmpty(),
                    "Expected 'upcomingEvents' to be empty, but found data.");
        }
    }

    @Then("the first event status metric should have status {string} and count greater than {int}")
    public void validateFirstEventStatusMetric(String expectedStatus, int minCount) {
        // Get the response from the context
        Response response = context.getResponse();
        Assert.assertNotNull(response, "Response is null");

        // Parse the first 'eventStatusMetrics' object
        Map<String, Object> firstMetric = response.jsonPath().getMap("eventStatusMetrics[0]");
        Assert.assertNotNull(firstMetric, "No event status metrics found in the response.");

        // Validate the 'status' field
        String actualStatus = (String) firstMetric.get("status");
        Assert.assertEquals(actualStatus, expectedStatus,
                "Expected status '" + expectedStatus + "', but found '" + actualStatus + "'.");

        // Validate the 'count' field
        int actualCount = (int) firstMetric.get("count");
        Assert.assertTrue(actualCount > minCount,
                "Expected count to be greater than " + minCount + ", but found " + actualCount + ".");
    }

    @When("I send a GET request without query parameters")
    public void sendGetWithNoQueryParams(){
        String apiEndpoint = (String) context.getFromPayload("apiEndpoint");
        Response response = GetRequestHandler.get(apiEndpoint);
        context.setResponse(response);

    }

    @Given("I provide an invalid authorization token")
    public void provideInvalidAuthToken() {
        String apiEndpoint = (String) context.getFromPayload("apiEndpoint");

        Response response = GetRequestHandler.getWithAuth(apiEndpoint, "invalid_token");
        context.setResponse(response); // Store response in context
        System.out.println("Authorization Token: invalid_token");
    }

    @Given("I provide a valid authorization token {string}")
    public void provideValidAuthToken(String token) {
        String apiEndpoint = (String) context.getFromPayload("apiEndpoint");

        Response response = GetRequestHandler.getWithAuth(apiEndpoint, token);
        context.setResponse(response); // Store response in context
        System.out.println("Authorization Token: " + token);
    }


    @Then("the response data list should not be empty")
    public void validateNonEmptyDataList() {
        // Retrieve response from context
        Response response = context.getResponse();

        // Ensure response is not null
        Assert.assertNotNull(response, "Response is null");

        // Check if 'data' key exists and is not null
        boolean hasDataKey = response.jsonPath().getMap("$").containsKey("data");
        Assert.assertTrue(hasDataKey, "'data' key is missing in the response!");

        // Validate the size of the data list
        int actualDataSize = response.jsonPath().getList("data").size();
        Assert.assertTrue(actualDataSize > 0,
                "Expected a non-empty data list, but found " + actualDataSize + " elements.");
    }

    @Then("the response should contain event name {string}")
    public void validateEventName(String eventName) {
        Response response = context.getResponse(); // Retrieve response from context
        Assert.assertNotNull(response, "Response is null");

        String actualName = response.jsonPath().getString("data.find { it.name == '" + eventName + "' }.name");
        Assert.assertEquals(actualName, eventName,
                "Expected event name: " + eventName + ", but got: " + actualName);
    }

    @Then("the response should contain {string} as {int}")
    public void validateResponseField(String fieldName, int expectedValue) {
        Response response = context.getResponse(); // Retrieve response from context
        Assert.assertNotNull(response, "Response is null");

        int actualValue = response.jsonPath().getInt(fieldName);
        Assert.assertEquals(actualValue, expectedValue,
                "Expected value for field " + fieldName + ": " + expectedValue + ", but found: " + actualValue);
    }

    @Then("the response message should contain {string}")
    public void validateResponseMessage(String expectedMessage) {
        Response response = context.getResponse(); // Retrieve response from context
        Assert.assertNotNull(response, "Response is null");

        String actualMessage = response.jsonPath().getString("message");
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected message to contain: " + expectedMessage + ", but got: " + actualMessage);
    }

    @Then("the response message should be {string}")
    public void validateUnauthorizedErrorMessage(String expectedMessage) {
        Response response = context.getResponse(); // Retrieve response from context
        Assert.assertNotNull(response, "Response is null");

        String actualMessage = response.jsonPath().getString("message");
        Assert.assertEquals(actualMessage, expectedMessage,
                "Expected error message: " + expectedMessage + ", but found: " + actualMessage);
    }
}