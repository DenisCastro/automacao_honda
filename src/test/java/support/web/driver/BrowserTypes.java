package support.web.driver;

import intern.Instances;

public class BrowserTypes {
    public ChromeCapabilities chrome(){
        Instances.setWebdriverType(Instances.chrome);
        return Instances.getChromeCapabilitiesClassInstance();
    }

    public void phantomJs(){
        Instances.setWebdriverType(Instances.phantomjs);
    }

    public void fireFox(){
        Instances.setWebdriverType(Instances.firefox);
    }

    public IEOptions internetExplorer(){
        Instances.setWebdriverType(Instances.internetExplorer);
        return Instances.getIEOptionsClassInstance();
    }
}
