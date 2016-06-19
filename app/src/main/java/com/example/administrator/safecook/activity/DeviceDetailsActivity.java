package com.example.administrator.safecook.activity;
/**
 * Aaron 2016/6/17
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.safecook.R;

public class DeviceDetailsActivity extends AppCompatActivity {
    ImageView go_back;
    ImageView details_menu;
    Switch timeSwitch;
    Switch s_gear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_device_details);
        go_back=(ImageView) findViewById(R.id.iv_go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DeviceDetailsActivity.this,MainPageActivity.class);
                startActivity(intent);
            }
        });
        details_menu= (ImageView) findViewById(R.id.iv_details_menu);
        details_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DeviceDetailsActivity.this,"菜单键被点击",Toast.LENGTH_SHORT).show();
            }
        });
        timeSwitch= (Switch) findViewById(R.id.s_timeSwitch);
        timeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(DeviceDetailsActivity.this,"定时开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DeviceDetailsActivity.this,"定时开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        s_gear= (Switch) findViewById(R.id.s_gear);
        s_gear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(DeviceDetailsActivity.this,"档位调节已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DeviceDetailsActivity.this,"档位调节已关闭",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
