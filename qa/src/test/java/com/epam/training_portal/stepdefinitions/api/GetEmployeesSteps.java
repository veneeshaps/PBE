package com.epam.training_portal.stepdefinitions.api;

import com.epam.training_portal.api.routes.ApiEndpoints;
import com.epam.training_portal.context.TestContextAPI;
import io.cucumber.java.en.Given;

import java.util.Map;

public class GetEmployeesSteps {

    private final TestContextAPI context;

    public GetEmployeesSteps(TestContextAPI context) {
        this.context = context;
    }


    @Given("the endpoint {string} is configured")
    public void theEndpointIsConfigured(String endpointKey) {
        Map<String, String> endpointMapping = Map.of(
                "GET_EMPLOYEE", ApiEndpoints.AttendanceReports.GET_EMPLOYEE
        );
        if (!endpointMapping.containsKey(endpointKey)) {
            throw new IllegalArgumentException("Invalid API endpoint key: " + endpointKey);
        }

        // Retrieve and store the resolved endpoint in the context
        String resolvedEndpoint = endpointMapping.get(endpointKey);
        context.addToPayload("apiEndpoint", resolvedEndpoint);

        System.out.println("Resolved API Endpoint: " + resolvedEndpoint);
    }
}
