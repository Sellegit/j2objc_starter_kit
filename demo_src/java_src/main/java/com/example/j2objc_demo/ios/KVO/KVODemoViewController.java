package com.example.j2objc_demo.ios.KVO;

import com.google.j2objc.annotations.Mapping;
import com.google.j2objc.runtime.Selector;
import com.google.j2objc.runtime.VoidPtr;

import apple.coregraphics.CGPoint;
import apple.coregraphics.CGRect;
import apple.coregraphics.CGSize;
import apple.foundation.NSDictionary;
import apple.foundation.NSKeyValueObservingOptions;
import apple.uikit.UIButton;
import apple.uikit.UIButtonType;
import apple.uikit.UIColor;
import apple.uikit.UIControlEvents;
import apple.uikit.UIControlState;
import apple.uikit.UIFont;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;


public class KVODemoViewController extends UIViewController {

    private String mKeyPath = "backgroundColor";
    private UILabel mLabel;
    private UIButton mButton;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        mButton = UIButton.create(UIButtonType.Custom);
        mButton.setFrame(CGRect.create(CGPoint.create(20, 180), CGSize.create(100, 40)));
        mButton.setBackgroundColor(UIColor.lightGray());
        mButton.setTitle("Change", UIControlState.Normal);
        mButton.addTarget(this, Selector.fromString("buttonPressed:"), UIControlEvents.TouchDown);
        mButton.addObserver(this, mKeyPath, NSKeyValueObservingOptions.New, null);

        UILabel label = new UILabel((CGRect.create(CGPoint.create(20, 100), CGSize.create(500, 30))));
        label.setText("The button background color is: ");
        label.setFont(UIFont.getSystemFont(16));
        mLabel = new UILabel(CGRect.create(CGPoint.create(20, 130), CGSize.create(500, 30)));
        mLabel.setText(mButton.getBackgroundColor().toString());
        mLabel.setFont(UIFont.getSystemFont(16));

        getView().setBackgroundColor(UIColor.white());
        getView().addSubview(mButton);
        getView().addSubview(label);
        getView().addSubview(mLabel);
    }

    @Mapping("buttonPressed:")
    public void buttonPressed(Object sender) {
        System.out.println("press btn");
        if (mButton.getBackgroundColor().equals(UIColor.lightGray())) {
            mButton.setBackgroundColor(UIColor.darkGray());
        } else {
            mButton.setBackgroundColor(UIColor.lightGray());
        }

    }

    @Override
    public void observeValueForKeyPath(String keyPath, Object object, NSDictionary<?, ?> change,
                                       VoidPtr context) {
        System.out.println(keyPath + ": " + mButton.getBackgroundColor());
        if (keyPath.equals(mKeyPath)) {
            mLabel.setText(mButton.getBackgroundColor().toString());
        }

    }


}
