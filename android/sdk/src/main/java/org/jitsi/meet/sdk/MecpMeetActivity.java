package org.jitsi.meet.sdk;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Map;

/**
 * @author Create by qinyuanmao
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date Create on 2017/11/20 16:10.
 * @email qinyuanmao.live@gmail.com
 */

public class MecpMeetActivity extends JitsiMeetActivity {
    private String mAvatarUrl = "http://www.cdyachang.com:8080/image/017b6080-a26c-11e7-a17d-a315fa85a39cbase64.png";
    private String mName = "特朗普";
    private String mServerUrl = "https://meet.wisesoft.net.cn/";
    private String mRoomId = "qq";

    public MecpMeetActivity() {
    }

    protected JitsiMeetView initializeView(ShareRoomCallback callback) {
        JitsiMeetView view = super.initializeView(this.getShareCallback());
        Bundle bundle = new Bundle();
        bundle.putString("url", this.mServerUrl + this.mRoomId);
        bundle.putString("headerUrl", this.mAvatarUrl);
        bundle.putString("userName", this.mName);
        bundle.putBoolean("isMeeting", true);
        view.loadURLObject(bundle);
        view.setListener(new JitsiMeetViewListener() {
            public void onConferenceFailed(Map<String, Object> data) {
            }

            public void onConferenceJoined(Map<String, Object> data) {
            }

            public void onConferenceLeft(Map<String, Object> data) {
                MecpMeetActivity.this.finish();
            }

            public void onConferenceWillJoin(Map<String, Object> data) {
            }

            public void onConferenceWillLeave(Map<String, Object> data) {
            }

            public void onLoadConfigError(Map<String, Object> data) {
            }
        });
        return view;
    }

    public ShareRoomCallback getShareCallback() {
        ShareRoomCallback callback = new ShareRoomCallback() {
            public void share(String url) {
                Toast.makeText(MecpMeetActivity.this, "RNCallNative", 0).show();
            }
        };
        return callback;
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.setWelcomePageEnabled(false);
        super.onCreate(savedInstanceState);
    }
}
