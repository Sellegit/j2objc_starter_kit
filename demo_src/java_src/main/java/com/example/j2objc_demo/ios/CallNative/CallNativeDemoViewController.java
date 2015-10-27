package com.example.j2objc_demo.ios.CallNative;

import com.google.j2objc.runtime.block.IntBlock;

import apple.coregraphics.CGPoint;
import apple.coregraphics.CGRect;
import apple.coregraphics.CGSize;
import apple.uikit.UIColor;
import apple.uikit.UIFont;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;


public class CallNativeDemoViewController extends UIViewController {

    private UILabel mLabel;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        NativeDemo nativeDemo = new NativeDemo();

        int count = nativeDemo.getInt(new IntBlock() {
            @Override
            public int run() {
                return 1;
            }
        });

        String text = nativeDemo.getTextWithWho(count, "NativeDemo");

        mLabel = new UILabel(CGRect.create(CGPoint.create(20, 120), CGSize.create(500, 30)));
        mLabel.setText("Get text from NativeDemo.m: " + text);
        mLabel.setFont(UIFont.getSystemFont(16));

        getView().setBackgroundColor(UIColor.white());
        getView().addSubview(mLabel);

    }

}
