package com.example.j2objc_demo.ios.Block;

import com.google.j2objc.annotations.Mapping;
import com.google.j2objc.runtime.Selector;
import com.google.j2objc.runtime.block.IntBlock;
import com.google.j2objc.runtime.block.VoidBlock1;

import apple.coregraphics.CGPoint;
import apple.coregraphics.CGRect;
import apple.coregraphics.CGSize;
import apple.foundation.NSNotification;
import apple.foundation.NSNotificationCenter;
import apple.foundation.NSOperationQueue;
import apple.uikit.UIButton;
import apple.uikit.UIButtonType;
import apple.uikit.UIColor;
import apple.uikit.UIControlEvents;
import apple.uikit.UIControlState;
import apple.uikit.UIFont;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;


public class BlockDemoViewController extends UIViewController {

    private UILabel mLabel;
    private int mCount = 0;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        UIButton button = UIButton.create(UIButtonType.Custom);
        button.setFrame(CGRect.create(CGPoint.create(20, 180), CGSize.create(100, 40)));
        button.setBackgroundColor(UIColor.lightGray());
        button.setTitle("Notify +1", UIControlState.Normal);
        button.addTarget(this, Selector.fromString("buttonPressed:"), UIControlEvents.TouchDown);

        mLabel = new UILabel(CGRect.create(CGPoint.create(20, 120), CGSize.create(500, 30)));
        mLabel.setText("NotificationObserver with block: " + String.valueOf(mCount));
        mLabel.setFont(UIFont.getSystemFont(16));

        getView().setBackgroundColor(UIColor.white());
        getView().addSubview(button);
        getView().addSubview(mLabel);


        NSOperationQueue opQ = NSOperationQueue.getCurrentQueue();
        NSNotificationCenter.getDefaultCenter().addNotificationObserver("buttonPressed", null, opQ,
                new VoidBlock1<NSNotification>() {
                    @Override
                    public void run(NSNotification notif) {
                        mLabel.setText("NotificationObserver with block: " + String.valueOf(mCount));
                    }
                });
    }

    @Mapping("buttonPressed:")
    public void buttonPressed(Object sender) {
        mCount += getSomeInteger(new IntBlock() {
            @Override
            public int run() {
                return 1;
            }
        });
        System.out.println("press btn " + String.valueOf(mCount));
        NSNotificationCenter.getDefaultCenter().postNotification("buttonPressed", null);
    }


    public int getSomeInteger(IntBlock someInteger) {
        return someInteger.run();
    }

}
