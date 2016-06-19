package com.example.administrator.safecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.safecook.R;

public class MessageDetailActivity extends AppCompatActivity {
    ImageView go_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        go_back= (ImageView) findViewById(R.id.iv_message_detail_go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageDetailActivity.this,MessageListActivity.class);
                startActivity(intent);
            }
        });
    }
}
