package support.rest;

import intern.Instances;

public class ResponseREST {

    public ResponseStatusREST status(){
        return Instances.getResponseStatusRESTClassInstance();
    }

    public ResponseBodyREST body(){
        return Instances.getResponseBodyRESTClassInstance();
    }

    public ResponseResponseTimeREST responseTime(){
        return Instances.getResponseResponseTimeRESTClassInstance();
    }
}
