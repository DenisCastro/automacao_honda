package support.rest;

import intern.Instances;

public class HeadersREST {
    public void put(String key, String value){
        Instances.putRestLastHeaders(key, value);
    }

    public void putToken(String token){
        Instances.setRestLastToken(token);
    }
}
