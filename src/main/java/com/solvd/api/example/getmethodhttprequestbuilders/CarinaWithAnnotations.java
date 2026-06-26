//package com.solvd.api;
//
//import com.zebrunner.carina.api.AbstractApiMethodV2;
//import com.zebrunner.carina.api.annotation.Endpoint;
//import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
//import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
//import com.zebrunner.carina.api.http.HttpMethodType;
//import com.zebrunner.carina.api.http.HttpResponseStatusType;
//import com.zebrunner.carina.utils.config.Configuration;
//
//
/// / expected approach to use at home.
//
//@Endpoint(url = "${base_url}/user", methodType = HttpMethodType.GET)
//@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
//@ResponseTemplatePath(path = "users/get/rs.json")
//
//public class GetUserMethod2 extends AbstractApiMethodV2 {
//
//    public GetUserMethod2(String username) {
//
//        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
//        setHeader("Authorization", Configuration.getRequired("api_token"));
//    }
//}