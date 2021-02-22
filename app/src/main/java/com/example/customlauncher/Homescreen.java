package com.example.customlauncher;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Homescreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Homescreen extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Homescreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Homescreen.
     */
    // TODO: Rename and change types and number of parameters
    /*public static Homescreen newInstance(String param1, String param2) {
        Homescreen fragment = new Homescreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_homescreen, container, false);
        ImageView Icon = v.findViewById(R.id.icon);
        Icon.setImageDrawable(MainActivity.getActivityIcon(this.getContext(), "com.android.chrome", "com.google.android.apps.chrome.Main"));
        Icon.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.icon:
                Intent launchIntent = MainActivity.baseContext.getPackageManager().getLaunchIntentForPackage("com.android.chrome");
                startActivity(launchIntent);
                Log.d("MyTAG", "chrome버튼 클릭");
                break;
        }
    }
}