package com.example.j2objc_demo.ios.CallNative;


import com.google.j2objc.annotations.Block;
import com.google.j2objc.annotations.Library;
import com.google.j2objc.annotations.Mapping;
import com.google.j2objc.runtime.block.IntBlock;


@Library("generated_native/NativeDemo.h")
@Mapping("NativeDemo")
public class NativeDemo {

    @Mapping("init")
    public NativeDemo() {
    }

    @Mapping("getInt:")
    public native int getInt(@Block IntBlock intBlock);

    @Mapping("getText:withWho:")
    public native String getTextWithWho(int count, String who);
}