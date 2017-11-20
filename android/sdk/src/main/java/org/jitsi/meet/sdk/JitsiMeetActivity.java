//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jitsi.meet.sdk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import java.net.URL;

public abstract class JitsiMeetActivity extends AppCompatActivity {
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = (int)(Math.random() * 32767.0D);
    private DefaultHardwareBackBtnHandler defaultBackButtonImpl;
    private URL defaultURL;
    private JitsiMeetView view;
    private boolean welcomePageEnabled;

    public JitsiMeetActivity() {
    }

    public abstract ShareRoomCallback getShareCallback();

    private boolean canRequestOverlayPermission() {
        return false;
    }

    public URL getDefaultURL() {
        return this.view == null?this.defaultURL:this.view.getDefaultURL();
    }

    public boolean getWelcomePageEnabled() {
        return this.view == null?this.welcomePageEnabled:this.view.getWelcomePageEnabled();
    }

    private void initializeContentView() {
        JitsiMeetView view = this.initializeView(this.getShareCallback());
        if(view != null) {
            this.view = view;
            this.setContentView(this.view);
        }

    }

    protected JitsiMeetView initializeView(ShareRoomCallback callback) {
        JitsiMeetView view = new JitsiMeetView(this, callback);
        view.setDefaultURL(this.defaultURL);
        view.setWelcomePageEnabled(this.welcomePageEnabled);
        view.loadURL((URL)null);
        return view;
    }

    public void loadURL(@Nullable URL url) {
        this.view.loadURL(url);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == OVERLAY_PERMISSION_REQUEST_CODE && this.canRequestOverlayPermission() && Settings.canDrawOverlays(this)) {
            this.initializeContentView();
        }

    }

    public void onBackPressed() {
        if(!JitsiMeetView.onBackPressed()) {
            if(this.defaultBackButtonImpl == null) {
                super.onBackPressed();
            } else {
                this.defaultBackButtonImpl.invokeDefaultOnBackPressed();
            }
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.canRequestOverlayPermission() && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this.getPackageName()));
            this.startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
        } else {
            this.initializeContentView();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if(this.view != null) {
            this.view.dispose();
            this.view = null;
        }

        JitsiMeetView.onHostDestroy(this);
    }

    public void onNewIntent(Intent intent) {
        JitsiMeetView.onNewIntent(intent);
    }

    protected void onPause() {
        super.onPause();
        JitsiMeetView.onHostPause(this);
        this.defaultBackButtonImpl = null;
    }

    protected void onResume() {
        super.onResume();
        this.defaultBackButtonImpl = new DefaultHardwareBackBtnHandlerImpl(this);
        JitsiMeetView.onHostResume(this, this.defaultBackButtonImpl);
    }

    public void setDefaultURL(URL defaultURL) {
        if(this.view == null) {
            this.defaultURL = defaultURL;
        } else {
            this.view.setDefaultURL(defaultURL);
        }

    }

    public void setWelcomePageEnabled(boolean welcomePageEnabled) {
        if(this.view == null) {
            this.welcomePageEnabled = welcomePageEnabled;
        } else {
            this.view.setWelcomePageEnabled(welcomePageEnabled);
        }

    }
}
