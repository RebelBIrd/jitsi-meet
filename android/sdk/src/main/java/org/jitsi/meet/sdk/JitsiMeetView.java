//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jitsi.meet.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.RNFetchBlob.RNFetchBlobPackage;
import com.corbt.keepawake.KCKeepAwakePackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.oney.WebRTCModule.WebRTCModulePackage;
import com.rnimmersive.RNImmersivePackage;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

public class JitsiMeetView extends FrameLayout {
    private static final int BACKGROUND_COLOR = -15658735;
    private static ReactInstanceManager reactInstanceManager;
    private static ShareRoomCallback mShareRoomCallback;
    private static final Set<JitsiMeetView> views = Collections.newSetFromMap(new WeakHashMap());
    private URL defaultURL;
    private final String externalAPIScope;
    private JitsiMeetViewListener listener;
    private ReactRootView reactRootView;
    private boolean welcomePageEnabled;

    private static List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.asList(new NativeModule[]{new AndroidSettingsModule(reactContext), new AppInfoModule(reactContext), new AudioModeModule(reactContext), new ExternalAPIModule(reactContext), new ProximityModule(reactContext), new RNCallBack(reactContext, mShareRoomCallback)});
    }

    public static JitsiMeetView findViewByExternalAPIScope(String externalAPIScope) {
        Set var1 = views;
        synchronized(views) {
            Iterator var2 = views.iterator();

            JitsiMeetView view;
            do {
                if(!var2.hasNext()) {
                    return null;
                }

                view = (JitsiMeetView)var2.next();
            } while(!view.externalAPIScope.equals(externalAPIScope));

            return view;
        }
    }

    private static void initReactInstanceManager(Application application) {
        reactInstanceManager = ReactInstanceManager.builder().setApplication(application).setBundleAssetName("index.android.bundle").setJSMainModulePath("index.android").addPackage(new KCKeepAwakePackage()).addPackage(new MainReactPackage()).addPackage(new VectorIconsPackage()).addPackage(new BackgroundTimerPackage()).addPackage(new WebRTCModulePackage()).addPackage(new RNFetchBlobPackage()).addPackage(new RNImmersivePackage()).addPackage(new ReactPackageAdapter() {
            public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
                return JitsiMeetView.createNativeModules(reactContext);
            }
        }).setUseDeveloperSupport(false).setInitialLifecycleState(LifecycleState.RESUMED).build();
    }

    private static boolean loadURLStringInViews(String urlString) {
        Set var1 = views;
        synchronized(views) {
            if(views.isEmpty()) {
                return false;
            } else {
                Iterator var2 = views.iterator();

                while(var2.hasNext()) {
                    JitsiMeetView view = (JitsiMeetView)var2.next();
                    view.loadURLString(urlString);
                }

                return true;
            }
        }
    }

    public static boolean onBackPressed() {
        if(reactInstanceManager == null) {
            return false;
        } else {
            reactInstanceManager.onBackPressed();
            return true;
        }
    }

    public static void onHostDestroy(Activity activity) {
        if(reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy(activity);
        }

    }

    public static void onHostPause(Activity activity) {
        if(reactInstanceManager != null) {
            reactInstanceManager.onHostPause(activity);
        }

    }

    public static void onHostResume(Activity activity) {
        onHostResume(activity, new DefaultHardwareBackBtnHandlerImpl(activity));
    }

    public static void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultBackButtonImpl) {
        if(reactInstanceManager != null) {
            reactInstanceManager.onHostResume(activity, defaultBackButtonImpl);
        }

    }

    public static void onNewIntent(Intent intent) {
        Uri uri;
        if(!"android.intent.action.VIEW".equals(intent.getAction()) || (uri = intent.getData()) == null || !loadURLStringInViews(uri.toString())) {
            if(reactInstanceManager != null) {
                reactInstanceManager.onNewIntent(intent);
            }

        }
    }

    public JitsiMeetView(@NonNull Context context, ShareRoomCallback callback) {
        super(context);
        mShareRoomCallback = callback;
        this.setBackgroundColor(-15658735);
        if(reactInstanceManager == null) {
            initReactInstanceManager(((Activity)context).getApplication());
        }

        this.externalAPIScope = UUID.randomUUID().toString();
        Set var3 = views;
        synchronized(views) {
            views.add(this);
        }
    }

    public void dispose() {
        if(this.reactRootView != null) {
            this.removeView(this.reactRootView);
            this.reactRootView.unmountReactApplication();
            this.reactRootView = null;
        }

    }

    public URL getDefaultURL() {
        return this.defaultURL;
    }

    public JitsiMeetViewListener getListener() {
        return this.listener;
    }

    public boolean getWelcomePageEnabled() {
        return this.welcomePageEnabled;
    }

    public void loadURL(@Nullable URL url) {
        this.loadURLString(url == null?null:url.toString());
    }

    public void loadURLObject(@Nullable Bundle urlObject) {
        Bundle props = new Bundle();
        if(this.defaultURL != null) {
            props.putString("defaultURL", this.defaultURL.toString());
        }

        props.putString("externalAPIScope", this.externalAPIScope);
        if(urlObject != null) {
            props.putBundle("url", urlObject);
        }

        props.putBoolean("welcomePageEnabled", this.welcomePageEnabled);
        props.putLong("timestamp", System.currentTimeMillis());
        if(this.reactRootView == null) {
            this.reactRootView = new ReactRootView(this.getContext());
            this.reactRootView.startReactApplication(reactInstanceManager, "App", props);
            this.reactRootView.setBackgroundColor(-15658735);
            this.addView(this.reactRootView);
        } else {
            this.reactRootView.setAppProperties(props);
        }

    }

    public void loadURLString(@Nullable String urlString) {
        Bundle urlObject;
        if(urlString == null) {
            urlObject = null;
        } else {
            urlObject = new Bundle();
            urlObject.putString("url", urlString);
        }

        this.loadURLObject(urlObject);
    }

    public void setDefaultURL(URL defaultURL) {
        this.defaultURL = defaultURL;
    }

    public void setListener(JitsiMeetViewListener listener) {
        this.listener = listener;
    }

    public void setWelcomePageEnabled(boolean welcomePageEnabled) {
        this.welcomePageEnabled = welcomePageEnabled;
    }
}
