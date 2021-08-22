package com.example.mybomlauncher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    /**
     * 메인 액티비티 입니다.
     * 주요 앱 4개와 메뉴 버튼이 있습니다.
     */
    public static Context baseContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseContext = getBaseContext();


        // 바탕화면에 보여질 4개의 앱 아이콘 등록
        ImageView mybomcameraIcon = (ImageView)findViewById(R.id.cameraButton); // myBom에 설치된 Snapdragon 카메라
        mybomcameraIcon.setImageDrawable(getActivityIcon(this,"org.codeaurora.snapcam", null));
        ImageView galleryIcon = (ImageView)findViewById(R.id.galleryButton);
        galleryIcon.setImageDrawable(getActivityIcon(this,"com.android.gallery3d", null ));
        ImageView settingIcon = (ImageView)findViewById(R.id.settingButton);
        //settingIcon.setImageDrawable(getActivityIcon(this,"com.android.settings", "com.android.settings.Settings")); // MyBom에 설치된 기본 설정앱
        settingIcon.setImageDrawable(getActivityIcon(this,"com.example.mybomsettings", "com.example.mybomsettings.MainActivity")); // 직접 구현한 MyBom 설정앱
        ImageView mybomIcon = (ImageView)findViewById(R.id.mybomButton); // 아직 클로이앱 없으니 테스트용으로 RobotPlatform 앱
        mybomIcon.setImageDrawable(getActivityIcon(this,"com.lge.robot.rptestapp", null));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static Drawable getActivityIcon(Context context, String packageName, String activityName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent();

        if (activityName == null) {
            try {
                return context.getPackageManager().getApplicationIcon(packageName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
        return resolveInfo.loadIcon(pm);
    }


    public void onAppsButtonClick(View v) { // 메뉴(앱 목록) 버튼 클릭
        Intent launchIntent = new Intent(getApplicationContext(), AppsDrawerActivity.class);
        startActivity(launchIntent);
    }

    // 앱 아이콘 클릭시 앱 실행되도록
    public void onGalleryButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.gallery3d");
        startActivity(launchIntent);
    }
    public void onCameraButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("org.codeaurora.snapcam");
        startActivity(launchIntent);
    }
    public void onSettingButtonClick(View v) {
        // Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.settings"); // LG CLOi 기존 설정앱
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.mybomsettings"); // MyBom 전용 설정앱
        startActivity(launchIntent);
    }
    public void onMyBomButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.lge.robot.rptestapp");
        startActivity(launchIntent);
    }
}