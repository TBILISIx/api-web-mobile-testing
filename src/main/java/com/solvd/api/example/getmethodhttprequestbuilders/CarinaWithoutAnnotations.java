//package com.solvd.api;
//
//import com.zebrunner.carina.api.AbstractApiMethodV2;
//import com.zebrunner.carina.utils.config.Configuration;
//
//public class GetUserMethod extends AbstractApiMethodV2 {
//
//
//
//    // this is same method but without @annotations so we set data in constructor
//
//
//
//
//
//
//
//    public GetUserMethod() {
//        super(null, "users/get/rs.json","users/get/user.properties");
//
//        // replaceUrlPlaceholder replaces base_url placeholder in _api.properties with api.url from _config.properties.
//
//        replaceUrlPlaceholder("base_url", Configuration.getRequired("api.url"));
//
//        addProperty("name", "Say Hello World Neo");
//
//        // setHeaders gets access token from _config.properties and sets it as Authorization header.
//        // we can generate this token from github/settings/personal-access-tokens
//        setHeaders("Authorization", "Bearer " + Configuration.getRequired("api.token"));
//    }
//
//}
