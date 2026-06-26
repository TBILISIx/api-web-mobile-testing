package com.solvd.api.restful;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Endpoint(url = "${base_url}/objects/${id}", methodType = HttpMethodType.PATCH)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)

public class PatchObjectData extends AbstractApiMethodV2 {

    public PatchObjectData(String resourceFile, String contentTypeHeader) throws IOException {

        replaceUrlPlaceholder("base_url", Configuration.getRequired("restful_api_url"));

        if (contentTypeHeader != null) {
            setHeader("Content-Type", contentTypeHeader);
        }

        if(resourceFile != null) {
            String body = new String(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resourceFile)).readAllBytes(),
                    StandardCharsets.UTF_8);

            setBodyContent(body);
        }



    }

}