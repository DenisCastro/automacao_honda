package support.screen.find;

import intern.Instances;
import org.openqa.selenium.NoSuchElementException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import support.tbi.JRobot;

public class ActionsScreen {

    public ActionsScreen precision(float precision){
        Instances.setScreenLastPrecision(precision);
        return this;
    }

    public ClicksScreen click() {
        Instances.getExecuteClassInstance().execute(() -> {
            try {
                Instances.getScreenSikuli().mouseMove(new Pattern(Instances.getScreenLastLocator()).similar(Instances.getScreenLastPrecision()));
                JRobot.robotClick();
            } catch (FindFailed findFailed) {
                throw new NoSuchElementException("O localizador " + Instances.getScreenLastLocator() + " não retornou nenhuma imagem\n"+findFailed.getMessage());
            }
        });
        Instances.getScreenshotClassInstance().print();
        Instances.getReportClassInstance().stepPass(Instances.getMessageClick() + Instances.getScreenLastLocatorLog());
        return Instances.getClicksScreenClassInstance();
    }

    public MoveScreen move() {
        Instances.getExecuteClassInstance().execute(() -> {
            try {
                Instances.getScreenSikuli().mouseMove(new Pattern(Instances.getScreenLastLocator()).similar(Instances.getScreenLastPrecision()));
            } catch (FindFailed findFailed) {
                throw new NoSuchElementException("O localizador " + Instances.getScreenLastLocator() + " não retornou nenhuma imagem\n"+findFailed.getMessage());
            }
        });
        Instances.getScreenshotClassInstance().print();
        Instances.getReportClassInstance().stepPass(Instances.getMessageMove() + Instances.getScreenLastLocatorLog());
        return Instances.getMoveScreenClassInstance();
    }

    public boolean exists() {
        final boolean[] existe = {true};
        Instances.getExecuteClassInstance().execute(() -> {
            try {
                Instances.getScreenSikuli().mouseMove(new Pattern(Instances.getScreenLastLocator()).similar(Instances.getScreenLastPrecision()));
                existe[0] = true;
            } catch (FindFailed findFailed) {
                existe[0] = false;
            }
        });
        Instances.getScreenshotClassInstance().print();
        Instances.getReportClassInstance().stepPass(Instances.getMessageExists() + Instances.getScreenLastLocatorLog());
        return existe[0];
    }
}
