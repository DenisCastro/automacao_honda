package support;

import intern.Instances;
import support.convert.Converter;
import support.db.Db;
import support.error.Error;
import support.log.Log;
import support.screen.driver.Screen;
import support.screen.find.ActionsScreen;
import support.screen.find.MoveScreen;
import support.screen.find.SendsScreen;
import support.screen.sleep.SleepScreen;
import support.tbi.JRobot;

public abstract class CoreScreen {

    public Converter convert(){
        return Instances.getConverterClassInstance();
    }

    public SleepScreen sleep(){
        return Instances.getSleepScreenClassInstance();
    }

    public Screen screen(){
        return Instances.getScreenClassInstance();
    }

    public ActionsScreen find(String caminhoDaImagem){
        return find(caminhoDaImagem, Instances.getScreenDefaultPrecision());
    }

    public static void click(){
        JRobot.robotClick();
    }

    public SendsScreen send(){
        return Instances.getSendsScreenClassInstance();
    }

    public ActionsScreen find(String caminhoDaImagem, float precision){
        Instances.setScreenLastPrecision(precision);
        Instances.setScreenSikuli();
        Instances.setScreenLastXpath(caminhoDaImagem);
        return Instances.getActionsScreenClassInstance();
    }

    /*public RegionScreen region(){

    }

    public AssertScreen assertThat(){

    }*/

    public MoveScreen move(){
        return Instances.getMoveScreenClassInstance();
    }

    public Log log(){
        return Instances.getLogClassInstance();
    }

    public Error error(){
        return Instances.getErrorClassInstance();
    }

    public Db db(){
        return Instances.getDbClassInstance();
    }

    protected CoreScreen help(){
        return this;
    }
}
