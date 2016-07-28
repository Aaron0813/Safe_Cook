package com.example.administrator.safecook.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.safecook.R;

/**
 * Created by Administrator on 2016/7/23.
 */
public class IndoorIntroductionFragment extends Fragment {
    private View view;
    ImageView iv_mainPage_menu;
    Switch s1;
    Switch s2;
    Switch s3;
    Switch s4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.activity_main_page,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init(){
        //油烟机开关-s1
        s1= (Switch)getActivity(). findViewById(R.id.swt_cook_hood);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"油烟机开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"油烟机开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //燃气灶开关-s2
        s2= (Switch) getActivity().findViewById(R.id.swt_gas_cooker);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"燃气灶开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"燃气灶开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //空气净化器-s3
        s3= (Switch)getActivity(). findViewById(R.id.swt_air_cleaner);
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"空气净化器开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"空气净化器开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //红外检测器-s4
        s4= (Switch) getActivity().findViewById(R.id.swt_infrared_detector);
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"红外检测器开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"红外检测器开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
