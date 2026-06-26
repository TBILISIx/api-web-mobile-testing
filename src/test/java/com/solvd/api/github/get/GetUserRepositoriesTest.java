package com.solvd.api.github.get;

import com.solvd.api.github.GetUserRepositories;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetUserRepositoriesTest {

    @Test(description = "Expects hospital repo from users repositories")

    public void GetUserRepositoriesResponseExpectsHospitalRepositoryTest() {

        GetUserRepositories GetUserRepositories = new GetUserRepositories();

        GetUserRepositories.setHeader("Authorization", Configuration.getRequired("api_token"));

        GetUserRepositories.expectResponseStatus(HttpResponseStatusType.OK_200);

        // Gets response body [arrays of repositories] and checks if it contains "hospital" with hasItem

        GetUserRepositories.expectResponseContains("name", Matchers.hasItem("hospital"));

        GetUserRepositories.callAPI();

    }

}