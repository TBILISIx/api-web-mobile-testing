package com.solvd.api.github.get;

import com.solvd.api.github.GetUserData;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;
import org.hamcrest.Matchers;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class GetUserDataTest {

    private static final int GIORGI_USER_ID = 125301130;

    @Test(description = "Validates response from API endpoint to response schema (the response that is expected)", enabled = true)

    public void getUserDataResponseAndValidateItAgainstResponseTemplateTest() {

        GetUserData getUserData = new GetUserData();

        // sets authorization header - bearer token
        getUserData.setHeader("Authorization", Configuration.getRequired("api_token"));

        // sets response template path to validate live public api response data against it
        getUserData.setResponseTemplate("get-user-response.json");

        // on api call expect status code 200 - means good call
        getUserData.expectResponseStatus(HttpResponseStatusType.OK_200);

        // calls given api endpoint
        getUserData.callAPI();

        //  comparing logic
        getUserData.validateResponse(JSONCompareMode.STRICT);

    }

    @Test(description = "Tests unAuthorised GET request (request without bearer token)")

    public void getUserDataWithoutBearerTokenTest() {

        GetUserData getUserData = new GetUserData();

        getUserData.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);

        getUserData.callAPI();

    }

    @Test(description = "Tests unAuthorised GET request (request with invalid bearer token)")

    public void getUserDataWithInvalidBearerTokenTest() {

        GetUserData getUserData = new GetUserData();

        getUserData.setHeader("Authorization", "Terry A. Davis The man The OG 00001");

        getUserData.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);

        getUserData.callAPI();

    }

    @Test(description = "Expects Giorgi Id from Request response")

    public void getUserDataResponseExpectsGiorgiUserIdTest() {

        GetUserData getUserData = new GetUserData();

        getUserData.setHeader("Authorization", Configuration.getRequired("api_token"));

        getUserData.expectResponseStatus(HttpResponseStatusType.OK_200);

        getUserData.expectResponseContains("id", Matchers.equalTo(GIORGI_USER_ID));

        getUserData.callAPI();

    }

}