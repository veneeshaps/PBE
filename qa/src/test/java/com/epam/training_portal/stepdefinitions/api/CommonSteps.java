package com.epam.training_portal.stepdefinitions.api;

import com.epam.training_portal.api.requests.GetRequestHandler;
import com.epam.training_portal.context.TestContextAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class CommonSteps {

    private final TestContextAPI context;

    public CommonSteps(TestContextAPI context) {
        this.context = context;
    }


    @When("I send a GET request")
    public void sendGetRequest() {
        // Retrieve the API endpoint from context
        String apiEndpoint = (String) context.getFromPayload("apiEndpoint");

        // Ensure the endpoint is not null
        if (apiEndpoint == null) {
            throw new IllegalArgumentException("API endpoint is null. Check if it was set in the context.");
        }

        // Retrieve query parameters (if any) from context
        Map<String, String> queryParams = (Map<String, String>) context.getFromPayload("queryParams");

        Response response;

        // Make GET request according to whether queryParams are present
        if (queryParams != null) {
            response = GetRequestHandler.getWithQueryParams(apiEndpoint, queryParams);
        } else {
            response = GetRequestHandler.get(apiEndpoint);
        }

        // Store the response in context
        context.setResponse(response);

        // Debugging output
        System.out.println("Response: " + response.getBody().asString());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Response response = context.getResponse();
        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @Then("the response total count should be {int}")
    public void validateTotalCount(int expectedCount) {
        Response response = context.getResponse(); // Retrieve response from context
        Assert.assertNotNull(response, "Response is null");

        int totalCount = response.jsonPath().getInt("totalCount");
        Assert.assertEquals(totalCount, expectedCount,
                "Expected total count: " + expectedCount + ", but got: " + totalCount);
    }

    @Then("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(String expectedContentType) {
        Response response = context.getResponse();
        Assert.assertNotNull(response, "Response is null");
        Assert.assertEquals(response.getContentType(), expectedContentType);
    }

    @Then("the response data list should be empty")
    public void validateEmptyDataList() {
        // Retrieve the response
        Response response = context.getResponse();
        Assert.assertNotNull(response, "Response is null");

        // Ensure the 'data' key is present in the response
        boolean hasDataKey = response.jsonPath().getMap("$").containsKey("data");
        Assert.assertTrue(hasDataKey, "'data' key is missing in the response!");

        // Parse and check list size
        int actualDataSize = response.jsonPath().getList("data").size();
        Assert.assertEquals(actualDataSize, 0,
                "Expected an empty data list, but found " + actualDataSize + " elements.");
    }
}
