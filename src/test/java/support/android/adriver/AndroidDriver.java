package support.android.adriver;

import intern.Instances;

public class AndroidDriver {
    public Capabilities capabilities(){
        return Instances.getCapabilitiesClassInstance();
    }
}
