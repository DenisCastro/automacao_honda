package support.rest;

import intern.Instances;

public class SendREST {
    public TypeOfREST endpoint(String endpoint){
        Instances.setRestLastEndPoint(endpoint);
        return Instances.getTypeOfRESTClassInstance();
    }
}
