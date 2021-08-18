package com.example.mybomlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AppsDrawer extends AppCompatActivity {
    RAdapter radapter;
    // ArrayList<AppInfo> appsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_drawer);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.appList);
        radapter = new RAdapter(this);
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new myThread().execute(); // Thread가 앱 불러오는 동작
    }


    public class myThread extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... Params) {
            PackageManager pm = getPackageManager();
            radapter.appsList = new ArrayList<>();

            Intent i = new Intent(Intent.ACTION_MAIN, null);
            i.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
            for(ResolveInfo ri:allApps) { // 필요한 앱만 거르기
                if (ri.loadLabel(pm).equals("갤러리") || ri.loadLabel(pm).equals("카메라") ||
                        ri.loadLabel(pm).equals("Snapdragon 카메라") || ri.loadLabel(pm).equals("설정") ||
                        ri.loadLabel(pm).equals("MyBomSettings") || ri.loadLabel(pm).equals("시계")) {
                    AppInfo app = new AppInfo();
                    app.label = ri.loadLabel(pm);
                    app.packageName = ri.activityInfo.packageName;
                    app.icon = ri.activityInfo.loadIcon(pm);
                    Log.i("MyTag)", "앱 이름: "+app.label+", 패키지 이름: "+app.packageName+", 타겟 액티비티: "+ri.activityInfo.targetActivity+", 부모 액티비티 :"+ri.activityInfo.parentActivityName);

                    radapter.addApp(app);
                } else {
                    continue;
                }
            }
            return "Success";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            updateStuff();
        }
    }

    public void updateStuff() {
        // recycler view에 동적으로 app list 추가 (새로 추가/삭제된 앱 바로 반영)
        radapter.notifyItemChanged(radapter.getItemCount()-1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
