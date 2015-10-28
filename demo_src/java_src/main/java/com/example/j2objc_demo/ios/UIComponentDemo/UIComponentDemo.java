package com.example.j2objc_demo.ios.UIComponentDemo;

import com.google.j2objc.annotations.Mapping;
import com.google.j2objc.runtime.Selector;

import apple.coregraphics.CGPoint;
import apple.coregraphics.CGRect;
import apple.coregraphics.CGSize;
import apple.foundation.NSRange;
import apple.uikit.UIAlertControllerStyle;
import apple.uikit.UIAlertView;
import apple.uikit.UIAlertViewDelegateAdapter;
import apple.uikit.UIAlertViewStyle;
import apple.uikit.UIButton;
import apple.uikit.UIButtonType;
import apple.uikit.UIColor;
import apple.uikit.UIControlContentVerticalAlignment;
import apple.uikit.UIControlEvents;
import apple.uikit.UIControlState;
import apple.uikit.UIFont;
import apple.uikit.UILabel;
import apple.uikit.UIScrollView;
import apple.uikit.UITextBorderStyle;
import apple.uikit.UITextField;
import apple.uikit.UITextFieldDelegateAdapter;
import apple.uikit.UIViewController;


public class UIComponentDemo extends UIViewController {

    private UIAlertView mAlert;
    private AlertViewDelegate mAlertViewDelegate;
    private UITextField mTextField;
    private UILabel mLabelForTextField;
    private TextFieldDelegate mTextFieldDelegate;

    @Override
    public void loadView() {
        super.loadView();
        UIScrollView scrollView = new UIScrollView();
        setView(scrollView);
    }

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        getView().setBackgroundColor(UIColor.white());

        UILabel label = new UILabel(CGRect.create(CGPoint.create(20, 30), CGSize.create(300, 30)));
        label.setText("This is a UILabel.");
        label.setFont(UIFont.getSystemFont(18));
        getView().addSubview(label);

        UIButton button = UIButton.create(UIButtonType.Custom);
        button.setFrame(CGRect.create(CGPoint.create(20, 90), CGSize.create(120, 30)));
        button.setBackgroundColor(UIColor.lightGray());
        button.setTitle("Alert Button", UIControlState.Normal);
        button.addTarget(this, Selector.fromString("buttonPressed:"), UIControlEvents.TouchDown);
        getView().addSubview(button);

        mAlertViewDelegate = new AlertViewDelegate();
        mAlert = new UIAlertView();
        mAlert.setTitle("Alert");
        mAlert.setMessage("Did you miss me ?");
        mAlert.setAlertViewStyle(UIAlertControllerStyle.Alert);
        mAlert.setDelegate(mAlertViewDelegate);
        mAlert.setAlertViewStyle(UIAlertViewStyle.Default);
        mAlert.addButton("Yes");
        mAlert.addButton("No");

        mTextField = new UITextField(CGRect.create(CGPoint.create(20, 150),
                CGSize.create(300, 30)));
        mTextField.setPlaceholder("Input something");
        mTextField.setBorderStyle(UITextBorderStyle.Line);
        mTextField.setContentVerticalAlignment(UIControlContentVerticalAlignment.Center);
        mTextFieldDelegate = new TextFieldDelegate();
        mTextField.setDelegate(mTextFieldDelegate);
        getView().addSubview(mTextField);

        mLabelForTextField = new UILabel(CGRect.create(CGPoint.create(20, 180), CGSize.create(300, 30)));
        mLabelForTextField.setText("Input:");
        mLabelForTextField.setFont(UIFont.getSystemFont(18));
        getView().addSubview(mLabelForTextField);

    }

    @Mapping("buttonPressed:")
    public void buttonPressed(Object sender) {
        System.out.println("press btn");
        mAlert.show();
    }

    public class AlertViewDelegate extends UIAlertViewDelegateAdapter {
        @Override
        public void didDismiss(UIAlertView alertView, long index) {
            System.out.println("didDismiss index " + String.valueOf(index));
        }
    }

    public class TextFieldDelegate extends UITextFieldDelegateAdapter {
        @Override
        public boolean shouldChangeCharacters(UITextField textField, NSRange range, String acc) {
            if (textField.equals(mTextField)) {
                String str = textField.getText().substring(0, (int) range.getLocation()) + acc;
                mLabelForTextField.setText("Input: " + str);
            }
            return true;
        }
    }
}
