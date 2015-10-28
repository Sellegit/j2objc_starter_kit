
import com.domain.appname.ios.ViewController;

import apple.foundation.NSDictionary;
import apple.uikit.UIApplication;
import apple.uikit.UIApplicationDelegateAdapter;
import apple.uikit.UIScreen;
import apple.uikit.UIWindow;

public class AppDelegate extends UIApplicationDelegateAdapter {

    private UIWindow mWindow = null;

    @Override
    public boolean didFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions) {
        mWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        mWindow.setRootViewController(new ViewController());
        mWindow.makeKeyAndVisible();
        return true;
    }

}
