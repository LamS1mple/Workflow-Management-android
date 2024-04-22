package com.example.workflowmanagementandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.workflowmanagementandroid.Model.User;
import com.google.gson.Gson;
import com.zegocloud.uikit.components.audiovideocontainer.ZegoLayout;
import com.zegocloud.uikit.components.audiovideocontainer.ZegoLayoutGalleryConfig;
import com.zegocloud.uikit.components.audiovideocontainer.ZegoLayoutMode;
import com.zegocloud.uikit.components.common.ZegoShowFullscreenModeToggleButtonRules;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;
import com.zegocloud.uikit.prebuilt.videoconference.config.ZegoMenuBarButtonName;

import java.util.Arrays;

public class ConferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);

        addFragment();
    }

    public void addFragment() {
        long appID = AppConstants.APPID;
        String appSign = AppConstants.APPSIGN;
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String userString = sharedPreferences.getString("userString","");
        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);
        String conferenceID = getIntent().getStringExtra("passZoom");
        String userID = user.getId()+"";
        String userName = user.getName();



        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        config.bottomMenuBarConfig.buttons = Arrays.asList(ZegoMenuBarButtonName.TOGGLE_CAMERA_BUTTON,
                ZegoMenuBarButtonName.TOGGLE_MICROPHONE_BUTTON,ZegoMenuBarButtonName.CHAT_BUTTON, ZegoMenuBarButtonName.SCREEN_SHARING_TOGGLE_BUTTON, ZegoMenuBarButtonName.LEAVE_BUTTON);

        ZegoLayoutGalleryConfig galleryConfig = new ZegoLayoutGalleryConfig();
        galleryConfig.removeViewWhenAudioVideoUnavailable = true;
        galleryConfig.showNewScreenSharingViewInFullscreenMode = true;
        galleryConfig.showScreenSharingFullscreenModeToggleButtonRules  = ZegoShowFullscreenModeToggleButtonRules.SHOW_WHEN_SCREEN_PRESSED;
        config.layout = new ZegoLayout(ZegoLayoutMode.GALLERY, galleryConfig);

        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userID, userName,conferenceID,config);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}