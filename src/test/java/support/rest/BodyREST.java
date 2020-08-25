package support.rest;

import intern.Instances;
import org.json.JSONObject;

public class BodyREST {

    public void put(JSONObject json){
        Instances.setRestLastBody(json);
    }

}
