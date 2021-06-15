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

    public static Context baseContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseContext = getBaseContext();


        // CLOi 용
        ImageView mybomcameraIcon = (ImageView)findViewById(R.id.cameraButton); // myBom에 설치된 Snapdragon 카메라
        mybomcameraIcon.setImageDrawable(getActivityIcon(this,"org.codeaurora.snapcam", null));
        ImageView galleryIcon = (ImageView)findViewById(R.id.galleryButton);
        galleryIcon.setImageDrawable(getActivityIcon(this,"com.android.gallery3d", null ));
        ImageView settingIcon = (ImageView)findViewById(R.id.settingButton);
        settingIcon.setImageDrawable(getActivityIcon(this,"com.android.settings", "com.android.settings.Settings"));
        ImageView mybomIcon = (ImageView)findViewById(R.id.mybomButton); // 아직 클로이앱 없으니 테스트용으로 RobotPlatform 앱
        mybomIcon.setImageDrawable(getActivityIcon(this,"com.lge.robot.rptestapp", null));

        // android 휴대폰 테스트용
        /*ImageView cameraIcon = (ImageView)findViewById(R.id.cameraButton);
        cameraIcon.setImageDrawable(getActivityIcon(this,"com.sec.android.app.camera", null));
        ImageView galleryIcon = (ImageView)findViewById(R.id.galleryButton);
        galleryIcon.setImageDrawable(getActivityIcon(this,"com.sec.android.gallery3d", null ));
        ImageView settingIcon = (ImageView)findViewById(R.id.settingButton);
        settingIcon.setImageDrawable(getActivityIcon(this,"com.android.settings", "com.android.settings.Settings"));
        ImageView chromeIcon = (ImageView)findViewById(R.id.mybomButton); // 테스트 용으로 mybom 앱 자리에 크롬
        chromeIcon.setImageDrawable(getActivityIcon(this,"com.android.chrome", "com.google.android.apps.chrome.Main"));*/
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

//        setContentView(R.layout.activity_main);
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

    public void openDrawerClick(View v) {
        openDrawer();
    }

    private void openDrawer() {
        Intent intent = new Intent(this, AppsDrawer.class);
        startActivity(intent);
    }


    public void onChromeButtonClick(View v) { // 바탕화면의 크롬 아이콘 클릭
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchIntent);
    }

    public void onAppsButtonClick(View v) { // 메뉴(앱 목록) 버튼 클릭
        Intent launchIntent = new Intent(getApplicationContext(), AppsDrawer.class);
        startActivity(launchIntent);
    }

    public void getMybomApps(View v) {

    }


}