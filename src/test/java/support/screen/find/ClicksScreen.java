package support.screen.find;

import intern.Instances;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import support.tbi.JRobot;

public class ClicksScreen {
    public void twice(){
        Instances.getExecuteClassInstance().execute(() -> {
            Instances.getSleepScreenClassInstance().until(200);
            JRobot.robotClick();
            JRobot.robotClick();
        });
        Instances.getScreenshotClassInstance().print();
        Instances.getReportClassInstance().stepPass(Instances.getMessageClickTwice() + Instances.getScreenLastLocatorLog());
    }

    public void thrice(){
        Instances.getExecuteClassInstance().execute(() -> {
            Instances.getSleepScreenClassInstance().until(200);
            JRobot.robotClick();
            JRobot.robotClick();
            JRobot.robotClick();
        });
        Instances.getScreenshotClassInstance().print();
        Instances.getReportClassInstance().stepPass(Instances.getMessageClickThrice() + Instances.getScreenLastLocatorLog());
    }
}
