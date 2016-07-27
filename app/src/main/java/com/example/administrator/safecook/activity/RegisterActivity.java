package com.example.administrator.safecook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.safecook.R;
import com.example.administrator.safecook.domain.MyUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private Button btn_register;
    private EditText edt_username, edt_password, edt_password_confirm, edt_mail;
    private String str_username, str_password1, str_password2, str_mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        edt_username = (EditText) findViewById(R.id.user_name);
        edt_password = (EditText) findViewById(R.id.user_password1);
        edt_password_confirm = (EditText) findViewById(R.id.userpassword2);
        edt_mail = (EditText) findViewById(R.id.phone_num);
        btn_register = (Button) findViewById(R.id.like_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_username = edt_username.getText().toString();
                str_password1 = edt_password.getText().toString();
                str_password2 = edt_password_confirm.getText().toString();
                str_mail = edt_mail.getText().toString();
                System.out.println(str_username + str_password1 + str_password2 + str_mail);
                MyUser user = new MyUser();
                user.setUsername(str_username);
                if (str_password1.equals(str_password2)) {
                    user.setPassword(str_password1);
                } else {
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                }
                user.setEmail(str_mail);
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser s, BmobException e) {
                        if (e == null) {
                            System.out.println("注册成功");
                            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                            i.putExtra("userName",str_username);
                            i.putExtra("password",str_password1);
                            startActivity(i);
                        } else {
                            System.out.println("失败");
                        }
                    }
                });
            }
        });
    }
}

