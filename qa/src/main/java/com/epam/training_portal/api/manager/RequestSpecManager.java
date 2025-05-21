package com.epam.training_portal.api.manager;

import com.epam.training_portal.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;


public class RequestSpecManager {

    private static RequestSpecification requestSpec;
    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {

            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getProperty("API_BASE_URL")) // Or ConfigReader.getProperty("API_BASE_URL")
                    .setContentType(ContentType.JSON)
                    .build();

            // Apply relaxed HTTPS validation if needed
            requestSpec = io.restassured.RestAssured.given().spec(requestSpec)
                    .relaxedHTTPSValidation().spec(requestSpec);
        }

        return requestSpec;
    }
}
