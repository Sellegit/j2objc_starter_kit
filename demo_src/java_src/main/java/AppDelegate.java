
import com.example.j2objc_demo.ios.MainMenuViewController;

import apple.foundation.NSDictionary;
import apple.uikit.UIApplication;
import apple.uikit.UIApplicationDelegateAdapter;
import apple.uikit.UINavigationController;
import apple.uikit.UIScreen;
import apple.uikit.UIViewController;
import apple.uikit.UIWindow;

public class AppDelegate extends UIApplicationDelegateAdapter {

    private UIWindow mWindow = null;

    @Override
    public boolean didFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions) {
        mWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        UIViewController viewController = new MainMenuViewController();
        UINavigationController navigationController = new UINavigationController(viewController);
        mWindow.setRootViewController(navigationController);
        mWindow.makeKeyAndVisible();
        return true;
    }

}
