package org.jitsi.meet.sdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * @author Create by qinyuanmao
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date Create on 2017/11/20 16:07.
 * @email qinyuanmao.live@gmail.com
 */

public class RNCallBack extends ReactContextBaseJavaModule {
    private ReactApplicationContext mContext;
    private ShareRoomCallback mShareRoomCallback;

    public RNCallBack(ReactApplicationContext reactContext, ShareRoomCallback callback) {
        super(reactContext);
        this.mContext = reactContext;
        this.mShareRoomCallback = callback;
    }

    public String getName() {
        return "RNCallBack";
    }

    @ReactMethod
    public void shareRoom(String url) {
        this.mShareRoomCallback.share(url);
    }
}
