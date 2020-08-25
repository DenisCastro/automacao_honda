package support.rest;

import intern.Instances;

public class ParametersREST {

    public void put(String key, String value){
        Instances.putRestLastParameters(key, value);
    }
}
