import com.domain.appname.shared.AppShared;

import apple.foundation.NSDictionary;
import apple.uikit.UIApplication;
import apple.uikit.UIApplicationDelegateAdapter;
import apple.uikit.UIScreen;
import apple.uikit.UIViewController;
import apple.uikit.UIWindow;
import avantx.ios.AvantIos;
import avantx.ios.router.PageControllerResolver;
import avantx.shared.router.Router;
import avantx.shared.ui.page.Page;

public class AppDelegate extends UIApplicationDelegateAdapter {

    private UIWindow mWindow = null;

    @Override
    public boolean didFinishLaunching(UIApplication application, NSDictionary<?, ?> launchOptions) {
        AvantIos.init();
        AppShared.init();
        mWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        mWindow.setRootViewController(getDefaultViewController());
        mWindow.makeKeyAndVisible();
        return true;
    }

    
    private UIViewController getDefaultViewController() {
        String rawRoute = Router.getResolver().getDefaultRoute();
        Page page = Router.getResolver().newPage(rawRoute);
        return PageControllerResolver.resolve(page);
    }

}
