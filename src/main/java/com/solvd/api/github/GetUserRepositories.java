package com.solvd.api.github;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/user/repos", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)

public class GetUserRepositories extends AbstractApiMethodV2 {

    public GetUserRepositories() {

        // public method api url
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));

    }

}
