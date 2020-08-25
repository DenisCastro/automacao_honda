package support.log;

import com.aventstack.extentreports.markuputils.Markup;
import intern.Instances;
import java.util.LinkedHashMap;
import java.util.List;

public class Log {

    public void setLocator(LinkedHashMap<String, String> locator) {
        Instances.setLocator(locator);
    }

    public LinkedHashMap<String, String> getLocator() {
        return Instances.getLocator();
    }

    public void type(String text){
        Instances.getReportClassInstance().stepInfo(text);
    }

    public void pass(String text){
        Instances.getReportClassInstance().stepPass(text);
    }

    public void typeThrowable(Markup text){
        Instances.getReportClassInstance().stepInfoThrowable(text);
    }
}
