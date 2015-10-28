package com.example.j2objc_demo.ios;


import com.example.j2objc_demo.ios.Block.BlockDemoViewController;
import com.example.j2objc_demo.ios.CallNative.CallNativeDemoViewController;
import com.example.j2objc_demo.ios.KVO.KVODemoViewController;
import com.example.j2objc_demo.ios.UIComponentDemo.UIComponentDemo;
import com.example.j2objc_demo.ios.util.Pair;

import java.util.ArrayList;

import apple.foundation.NSIndexPath;
import apple.uikit.NSIndexPathExtensions;
import apple.uikit.UIColor;
import apple.uikit.UIScreen;
import apple.uikit.UITableView;
import apple.uikit.UITableViewCell;
import apple.uikit.UITableViewCellAccessoryType;
import apple.uikit.UITableViewDataSource;
import apple.uikit.UITableViewDataSourceAdapter;
import apple.uikit.UITableViewDelegate;
import apple.uikit.UITableViewDelegateAdapter;
import apple.uikit.UITableViewStyle;
import apple.uikit.UIView;
import apple.uikit.UIViewAutoresizing;
import apple.uikit.UIViewController;


public class MainMenuViewController extends UIViewController {

    private String mTitle = "J2objc Demo";
    private final ArrayList<Pair<String, UIViewController>> mDemoTitles;
    private UITableViewDelegate mTableViewDelegate;
    private UITableViewDataSource mTableViewDataSource;

    public MainMenuViewController() {
        this.setTitle(mTitle);
        mDemoTitles = new ArrayList<>();
        Pair<String, UIViewController> pairUIComponents = new Pair<>("UI Components",
                (UIViewController) new UIComponentDemo());
        Pair<String, UIViewController> pairKVO = new Pair<>("KVO",
                (UIViewController) new KVODemoViewController());
        Pair<String, UIViewController> pairBlock = new Pair<>("Block Argument",
                (UIViewController) new BlockDemoViewController());
        Pair<String, UIViewController> pairCallObjc = new Pair<>("Call the native Objc",
                (UIViewController) new CallNativeDemoViewController());

        mDemoTitles.add(pairUIComponents);
        mDemoTitles.add(pairKVO);
        mDemoTitles.add(pairBlock);
        mDemoTitles.add(pairCallObjc);
    }

    @Override
    public void loadView() {
//        weak reference here, shouldn't be normal variable.
        mTableViewDelegate = new TableViewDelegate();
        mTableViewDataSource = new TableViewDataSource();

        UIView view = new UIView(UIScreen.getMainScreen().getBounds());
        view.setBackgroundColor(UIColor.white());
        view.autoresizesSubviews();

        UITableView rootTableView = new UITableView(view.getFrame(), UITableViewStyle.Plain);
        rootTableView.setAutoresizingMask(UIViewAutoresizing.FlexibleHeight | UIViewAutoresizing.FlexibleWidth);
        rootTableView.setDelegate(mTableViewDelegate);
        rootTableView.setDataSource(mTableViewDataSource);

        view.addSubview(rootTableView);
        setView(view);
    }

    @Override
    public void viewWillAppear(boolean animated) {
        getNavigationController().setDelegate(null);
        super.viewWillAppear(animated);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
    }

    @Override
    public void didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning();
    }

    /**
     * Implement the delegate by inheriting corresponding Adapter class
     */
    public class TableViewDelegate extends UITableViewDelegateAdapter {
        @Override
        public void didSelectRow(UITableView tableView, NSIndexPath indexPath) {
            tableView.deselectRow(indexPath, true);
            UIViewController demoViewController = null;
            int row = (int) NSIndexPathExtensions.getRow(indexPath);
            if (row < mDemoTitles.size()) {
                Pair<String, UIViewController> pair = mDemoTitles.get(row);
                mTitle = pair.getLeft();
                demoViewController = pair.getRight();
            }
            if (demoViewController != null) {
                getNavigationController().pushViewController(demoViewController, true);
            }
        }
    }

    public class TableViewDataSource extends UITableViewDataSourceAdapter {

        @Override
        public long getNumberOfSections(UITableView tableView) {
            return 1;
        }

        @Override
        public long getNumberOfRowsInSection(UITableView tableView, long section) {
            return mDemoTitles.size();
        }

        @Override
        public UITableViewCell getCellForRow(UITableView tableView, NSIndexPath indexPath) {
            UITableViewCell tableViewCell = new UITableViewCell();
            int row = (int) NSIndexPathExtensions.getRow(indexPath);
            if (row < mDemoTitles.size()) {
                Pair<String, UIViewController> pair = mDemoTitles.get(row);
                tableViewCell.getTextLabel().setText(pair.getLeft());
            }
            tableViewCell.setAccessoryType(UITableViewCellAccessoryType.DisclosureIndicator);
            return tableViewCell;
        }
    }
}

