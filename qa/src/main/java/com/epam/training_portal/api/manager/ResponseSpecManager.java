package com.epam.training_portal.api.manager;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecManager {

    public static ResponseSpecification getResponseSpec(int expectedStatusCode){
        return new ResponseSpecBuilder()
                //.expectContentType(ContentType.JSON)
                .expectStatusCode(expectedStatusCode)
                .build();
    }

}
