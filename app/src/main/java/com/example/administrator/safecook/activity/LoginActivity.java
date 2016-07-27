package com.example.administrator.safecook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.domain.MyInstallation;
import com.example.administrator.safecook.domain.MyUser;

import java.util.List;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class LoginActivity extends AppCompatActivity {
    EditText edt_userName;
    EditText edt_password;
    Button btn_login;
    Button btn_register;
    ImageView imv_pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        edt_userName= (EditText) findViewById(R.id.edt_userName);
        edt_password= (EditText) findViewById(R.id.edt_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String username=edt_userName.getText().toString();
                    String password=edt_password.getText().toString();
                    MyUser currentUser=new MyUser();
                    currentUser.setUsername(username);
                    currentUser.setPassword(password);
                    currentUser.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser bmobUser, BmobException e) {
                            if (e == null) {
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

                                /*将用户的ID与设备ID相互绑定*/
                                BmobQuery<MyInstallation> query = new BmobQuery<MyInstallation>();
                                query.addWhereEqualTo("installationId", BmobInstallation.getInstallationId(getApplicationContext()));
                                query.findObjects(new FindListener<MyInstallation>() {
                                    @Override
                                    public void done(List<MyInstallation> list, BmobException e) {
                                        if (e==null){//说明查询到了数据
                                            if(list.size()>0){
                                                MyInstallation myIns=list.get(0);
                                                myIns.setUserID(BmobUser.getCurrentUser().getObjectId());
                                                myIns.update(new UpdateListener() {
                                                    @Override
                                                    public void done(BmobException e) {
                                                        if (e==null)
                                                            Log.i("update","设备信息与用户绑定成功");
                                                        else
                                                            Log.i("update","设备信息与用户绑定失败");
                                                    }
                                                });
                                            }
                                        }else{//说明没有查询到数据，出现了异常
                                            Log.i("update","没有查询到数据");
                                        }
                                    }
                                });


                                Intent i=new Intent(LoginActivity.this,MainFragmentActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }
        });
        btn_register= (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        imv_pic= (ImageView) findViewById(R.id.imv_pic);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent=getIntent();
        String userName=intent.getStringExtra("userName");
        String password=intent.getStringExtra("password");
        edt_userName.setText(userName);
        edt_password.setText(password);
    }
}
