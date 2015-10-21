package com.domain.appname.ios;

import apple.coregraphics.CGPoint;
import apple.coregraphics.CGRect;
import apple.coregraphics.CGSize;
import apple.uikit.UIColor;
import apple.uikit.UIFont;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;


public class ViewController extends UIViewController {

    public void viewDidLoad() {
        super.viewDidLoad();
        UILabel label = new UILabel(CGRect.create(CGPoint.Zero(), CGSize.create(300, 100)));
        label.setText("Hello world!");
        label.setFont(UIFont.getSystemFont(20));
        this.getView().setBackgroundColor(UIColor.white());
        this.getView().addSubview(label);
    }

}
