package com.solvd.api.github.get;

import com.solvd.api.github.GetUserFollowers;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetUserFollowersTest {

    @Test(description = "Expects 403 status code because users access token has no permission to view followers")

    public void GetUserFollowersResponseExpects403ForbiddenTest() {

        GetUserFollowers getUserFollowers = new GetUserFollowers();

        getUserFollowers.setHeader("Authorization", Configuration.getRequired("api_token"));

        getUserFollowers.expectResponseStatus(HttpResponseStatusType.FORBIDDEN_403);

        // Checks if response body contains "hospital" with hasItem

        getUserFollowers.expectResponseContains("message", Matchers.stringContainsInOrder("Resource not accessible by personal access token"));
        getUserFollowers.expectResponseContains("status", Matchers.equalTo("403"));

        getUserFollowers.callAPI();

    }

}