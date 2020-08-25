package support.rest;

import intern.Instances;

public class TypeOfREST {

    public void get(){
        Instances.setRestLastTypeOfRequest("get");
    }
    public void post(){
        Instances.setRestLastTypeOfRequest("post");
    }
    public void put(){
        Instances.setRestLastTypeOfRequest("put");
    }
    public void delete(){
        Instances.setRestLastTypeOfRequest("delete");
    }
}
