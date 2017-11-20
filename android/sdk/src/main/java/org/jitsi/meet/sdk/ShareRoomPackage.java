package org.jitsi.meet.sdk;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Create by qinyuanmao
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date Create on 2017/11/20 16:09.
 * @email qinyuanmao.live@gmail.com
 */


public class ShareRoomPackage implements ReactPackage {
    public RNCallBack mRNCallBack;
    private ShareRoomCallback mShareRoomCallback;

    public ShareRoomPackage(ShareRoomCallback callback) {
        this.mShareRoomCallback = callback;
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList();
        this.mRNCallBack = new RNCallBack(reactContext, this.mShareRoomCallback);
        modules.add(this.mRNCallBack);
        return modules;
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}