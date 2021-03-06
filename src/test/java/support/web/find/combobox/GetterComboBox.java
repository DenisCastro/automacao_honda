package support.web.find.combobox;

import intern.Instances;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class GetterComboBox {
    public int size(){
        //Select combobox = new Select(Instances.getWebLastElements().get(0));
        //List<WebElement> elementos = combobox.getOptions();
        final int[] size = {0};
        if (!Instances.getEach()) {
            Instances.getExecuteClassInstance().execute(()->{
                Select combobox = new Select(Instances.getWebLastElements().get(0));
                List<WebElement> elementos = combobox.getOptions();
                size[0] = elementos.size();});
            Instances.getScreenshotClassInstance().print();
            Instances.getReportClassInstance().stepPass(Instances.getMessageComboBoxGetSize() + Instances.getWebLastXpath());
        } else {
            for (WebElement element : Instances.getWebLastElements()) {
                Instances.getExecuteClassInstance().execute(()->{
                    Select combobox = new Select(element);
                    List<WebElement> elementos = combobox.getOptions();
                    size[0] += elementos.size();});
                Instances.getScreenshotClassInstance().print();
                Instances.getReportClassInstance().stepPass(Instances.getMessageComboBoxGetSize() + Instances.getWebLastXpath());
            }
            Instances.setEach(false);
        }
        return size[0];
    }

    public List<WebElement> elements(){
        final int[] size = {0};
        List<List<WebElement>> e = new ArrayList<>();
        if (!Instances.getEach()) {
            Instances.getExecuteClassInstance().execute(()->{
                Select combobox = new Select(Instances.getWebLastElements().get(0));
                e.add(combobox.getOptions());
            });
            Instances.getScreenshotClassInstance().print();
            Instances.getReportClassInstance().stepPass(Instances.getMessageComboBoxGetSize() + Instances.getWebLastXpath());
        } else {
            for (WebElement element : Instances.getWebLastElements()) {
                Instances.getExecuteClassInstance().execute(()->{
                    Select combobox = new Select(element);
                    e.add(combobox.getOptions());
                    });
                Instances.getScreenshotClassInstance().print();
                Instances.getReportClassInstance().stepPass(Instances.getMessageComboBoxGetSize() + Instances.getWebLastXpath());
            }
            Instances.setEach(false);
        }
        return e.get(0);
    }
}
