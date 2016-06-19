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

public class MainPageActivity extends AppCompatActivity {
    ImageView iv_mainPage_menu;
    Switch s1;
    Switch s2;
    Switch s3;
    Switch s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_page);
        iv_mainPage_menu= (ImageView) findViewById(R.id.iv_mainPage_menu);
        iv_mainPage_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainPageActivity.this,"菜单键被点击",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainPageActivity.this,MessageListActivity.class);
                startActivity(intent);

            }
        });
        //油烟机开关-s1
        s1= (Switch) findViewById(R.id.switch1);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(MainPageActivity.this,"油烟机开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPageActivity.this,"油烟机开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //燃气灶开关-s2
        s2= (Switch) findViewById(R.id.switch2);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(MainPageActivity.this,"燃气灶开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPageActivity.this,"燃气灶开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //空气净化器-s3
        s3= (Switch) findViewById(R.id.switch3);
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(MainPageActivity.this,"空气净化器开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPageActivity.this,"空气净化器开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //红外检测器-s4
        s4= (Switch) findViewById(R.id.switch4);
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(MainPageActivity.this,"红外检测器开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPageActivity.this,"红外检测器开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
