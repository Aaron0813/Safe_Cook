package com.example.administrator.safecook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.safecook.R;

/**
 * Created by Administrator on 2016/7/23.
 */
public class DeviceDetailFragment extends Fragment {
    private View view;
    Switch timeSwitch;
    Switch s_gear;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_device_details,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timeSwitch= (Switch)getActivity(). findViewById(R.id.s_timeSwitch);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"定时开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"定时开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        s_gear= (Switch)getActivity(). findViewById(R.id.s_gear);
        s_gear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"档位调节已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"档位调节已关闭",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
