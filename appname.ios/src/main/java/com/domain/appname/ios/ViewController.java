package com.domain.appname.ios;

import apple.uikit.UIFont;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;


public class ViewController extends UIViewController {

    public void viewDidLoad() {
        super.viewDidLoad();
        UILabel label = new UILabel();
        label.setText("Hello world!");
        label.setFont(UIFont.getSystemFont(16));
        this.getView().addSubview(label);
    }

}
