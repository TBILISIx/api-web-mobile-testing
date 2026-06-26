package com.solvd.api.restful;

import com.zebrunner.carina.api.http.HttpResponseStatusType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class RestfulCrudTest {

    @Test(description = "Post method request / creates object on specified rest api endpoint")
    public void postObjectDataTest() throws IOException {

        PostObjectData postObjectData = new PostObjectData("post-object-data.json", APPLICATION_JSON);

        postObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        postObjectData.callAPI();

    }

    @Test(description = "Put request Method to fully update object on specified id (on id we created with postObjectData)")
    public void putObjectDataTest() throws IOException {

        PostObjectData postObjectData = new PostObjectData("post-object-data.json", APPLICATION_JSON);

        postObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response response = postObjectData.callAPI();

        String objectId = response.jsonPath().getString("id");

        PutObjectData putObjectData = new PutObjectData("put-object-data.json", APPLICATION_JSON);

        putObjectData.replaceUrlPlaceholder("id", objectId);

        putObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        putObjectData.callAPI();

    }

    @Test(description = "Put request Method to fully update object on specified id (on id we created with postObjectData)")
    public void patchObjectDataTest() throws IOException {

        PostObjectData postObjectData = new PostObjectData("post-object-data.json", APPLICATION_JSON);

        postObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response response = postObjectData.callAPI();

        String objectId = response.jsonPath().getString("id");

        PatchObjectData patchObjectData = new PatchObjectData("patch-object-data.json", APPLICATION_JSON);

        patchObjectData.replaceUrlPlaceholder("id", objectId);

        patchObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        patchObjectData.callAPI();

    }

    @Test(description = "deletes Object on specified id")
    public void deleteObjectDataTest() throws IOException {

        PostObjectData postObjectData = new PostObjectData("post-object-data.json", APPLICATION_JSON);

        postObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        Response response = postObjectData.callAPI();

        String objectId = response.jsonPath().getString("id");

        DeleteObjectData deleteObjectData = new DeleteObjectData();

        deleteObjectData.replaceUrlPlaceholder("id", objectId);

        deleteObjectData.expectResponseStatus(HttpResponseStatusType.OK_200);

        deleteObjectData.callAPI();

    }

}



