package com.epam.training_portal.stepdefinitions.api;

import com.epam.training_portal.api.routes.ApiEndpoints;
import com.epam.training_portal.context.TestContextAPI;
import com.epam.training_portal.api.requests.GetRequestHandler;
import com.epam.training_portal.api.requests.PostRequestHandler;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class BenchLeaderBoardSteps {

    private final TestContextAPI context; // Injected shared TestContextAPI to manage the context

    public BenchLeaderBoardSteps(TestContextAPI context) {
        this.context = context;
    }

    @Given("the API endpoint {string} is available")
    public void theAPIEndpointIsAvailable(String endpointKey) {
        // Map endpoint keys to their respective API endpoints
        Map<String, String> endpointMapping = Map.of(
                "LEADERBOARD_BENCH", ApiEndpoints.BenchLeaderBoard.LEADERBOARD_BENCH,
                "SCHEDULED_EVENT", ApiEndpoints.BenchLeaderBoard.SCHEDULED_EVENT,
                "CURRENT_WEIGHTAGES", ApiEndpoints.BenchLeaderBoard.CURRENT_WEIGHTAGES,
                "UPDATED_WEIGHTAGES", ApiEndpoints.BenchLeaderBoard.UPDATED_WEIGHTAGES,
                "EXPORT_BENCH", ApiEndpoints.BenchLeaderBoard.EXPORT_BENCH,
                "GET_EMPLOYEE", ApiEndpoints.AttendanceReports.GET_EMPLOYEE
        );

        // Validate if the provided key exists
        if (!endpointMapping.containsKey(endpointKey)) {
            throw new IllegalArgumentException("Invalid API endpoint key: " + endpointKey);
        }

        // Retrieve and store the resolved endpoint in the context
        String resolvedEndpoint = endpointMapping.get(endpointKey);
        context.addToPayload("apiEndpoint", resolvedEndpoint);

        System.out.println("Resolved API Endpoint: " + resolvedEndpoint);
    }


    @When("a GET request is made to fetch all profiles")
    public void aGETRequestIsMadeToFetchAllProfiles() {
        String endpointPath = (String) context.getFromPayload("endpointPath");
        Response response = GetRequestHandler.get(endpointPath);

        context.setResponse(response); // Store response in the shared context
    }

    @Then("the response should contain {int} profiles")
    public void theResponseShouldContainProfiles(int expectedProfileCount) {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        List<?> profiles = response.jsonPath().getList("$");
        Assert.assertEquals(profiles.size(), expectedProfileCount,
                "The number of profiles in the response does not match the expected count!");
    }

    @Then("each profile should contain the following fields:")
    public void eachProfileShouldContainTheFollowingFields(DataTable dataTable) {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        List<String> expectedFields = dataTable.asList();
        List<Map<String, Object>> profiles = response.jsonPath().getList("$");

        for (Map<String, Object> profile : profiles) {
            for (String field : expectedFields) {
                Assert.assertTrue(profile.containsKey(field),
                        "The field '" + field + "' is missing in one of the profiles!");
            }
        }
    }


    @When("a GET request is made to fetch all domains")
    public void aGETRequestIsMadeToFetchAllDomains() {
        String endpointPath = (String) context.getFromPayload("endpointPath");
        Response response = GetRequestHandler.get(endpointPath);

        context.setResponse(response); // Store response in the shared context
    }

    @Then("the response should contain {int} domains")
    public void theResponseShouldContainDomains(int expectedDomainCount) {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        List<?> domains = response.jsonPath().getList("$");
        Assert.assertEquals(domains.size(), expectedDomainCount,
                "The number of domains in the response does not match the expected count!");
    }

    @Then("each domain should contain the following fields:")
    public void eachDomainShouldContainTheFollowingFields(DataTable dataTable) {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        List<String> expectedFields = dataTable.asList(); // Extract expected fields
        List<Map<String, Object>> domains = response.jsonPath().getList("$");

        for (Map<String, Object> domain : domains) {
            for (String field : expectedFields) {
                Assert.assertTrue(domain.containsKey(field),
                        "The field '" + field + "' is missing in one of the domains!");
            }
        }
    }


    @When("a GET request is made to fetch the current weightages")
    public void aGETRequestIsMadeToFetchTheCurrentWeightages() {
        String endpointPath = (String) context.getFromPayload("endpointPath");
        Response response = GetRequestHandler.get(endpointPath);

        context.setResponse(response); // Store response in the shared context
    }

    @Then("the response should contain the following fields:")
    public void theResponseShouldContainTheFollowingFields(DataTable dataTable) {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        List<String> expectedFields = dataTable.asList(); // Extract expected fields
        List<Map<String, Object>> events = response.jsonPath().getList("$");

        for (Map<String, Object> event : events) {
            for (String field : expectedFields) {
                Assert.assertTrue(event.containsKey(field),
                        "The field '" + field + "' is missing in the response!");
            }
        }
    }

    @Then("the total percentage of all events should be less than or equal to 100")
    public void theTotalPercentageOfAllEventsShouldBeLessThanOrEqualTo100() {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        List<Map<String, Object>> events = response.jsonPath().getList("$");
        int totalPercentage = events.stream()
                .mapToInt(event -> (int) event.get("percentage"))
                .sum();
        Assert.assertTrue(totalPercentage <= 100,
                "The total percentage of all events exceeds 100!");
    }


    @When("a POST request is made with valid weightages summing to 100")
    public void aPOSTRequestIsMadeWithValidWeightages(DataTable dataTable) {
        String endpointPath = (String) context.getFromPayload("endpointPath");
        List<Map<String, Object>> weightages = convertDataTableToWeightageList(dataTable);

        Response response = PostRequestHandler.post(endpointPath, weightages);

        context.setResponse(response); // Store response in the shared context
    }

    @When("a POST request is made with invalid weightages not summing to 100")
    public void aPOSTRequestIsMadeWithInvalidWeightages(DataTable dataTable) {
        String endpointPath = (String) context.getFromPayload("endpointPath");
        List<Map<String, Object>> weightages = convertDataTableToWeightageList(dataTable);

        Response response = PostRequestHandler.post(endpointPath, weightages);

        context.setResponse(response); // Store response in the shared context
    }

    @Then("the response body should contain the message {string}")
    public void theResponseBodyShouldContainTheMessage(String expectedMessage) {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        String contentType = response.getContentType();
        if (contentType.contains("application/json")) {
            String actualMessage = response.jsonPath().getString("message");
            Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(),
                    "The JSON 'message' field does not match the expected value!");
        } else if (contentType.contains("text/plain")) {
            String actualResponseBody = response.getBody().asString();
            Assert.assertEquals(actualResponseBody.trim(), expectedMessage.trim(),
                    "The response body does not match the expected text!");
        } else {
            Assert.fail("Unexpected response Content-Type: " + contentType);
        }
    }


    @When("a GET request is made to the Export API with domainId as query parameter {string}")
    public void aGETRequestIsMadeToTheExportAPIWithDomainIdAsQueryParameter(String domainId) {
        String exportEndpoint = (String) context.getFromPayload("exportEndpoint");
        Response response = GetRequestHandler.getWithQuery(exportEndpoint, "domainId", domainId);

        context.setResponse(response); // Store response in the shared context
    }

    @Then("the response body should not be empty")
    public void theResponseBodyShouldNotBeEmpty() {
        Response response = context.getResponse(); // Retrieve response from the context
        Assert.assertNotNull(response, "Response is null");

        byte[] responseBody = response.getBody().asByteArray();
        Assert.assertTrue(responseBody.length > 0, "The response body is empty!");
    }

    // Utility method to convert DataTable into a List of Maps (for POST body)
    private List<Map<String, Object>> convertDataTableToWeightageList(DataTable dataTable) {
        return dataTable.asMaps(String.class, Object.class);
    }

    @Given("the Update Weightages API endpoint {string} is available")
    public void theUpdateWeightagesAPIEndpointIsAvailable(String path) {
        context.addToPayload("endpointPath", path); // Store endpoint in the shared context
    }

    @Given("the Export API endpoint {string} is available")
    public void theExportAPIEndpointIsAvailable(String path) {
        context.addToPayload("exportEndpoint", path); // Store endpoint in the shared context
    }

}