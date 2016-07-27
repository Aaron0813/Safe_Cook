package com.example.administrator.safecook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.activity.LoginActivity;
import com.example.administrator.safecook.domain.MyUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/7/23.
 */
public class PersonalFragment extends Fragment {
//    private Button button;
    private TextView tv_login;
    private TextView tv_logout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_personal_center,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_login= (TextView) getActivity().findViewById(R.id.tv_personal_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
                if (userInfo!=null){
                    Toast.makeText(getContext(),"用户已登录",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent;
                    intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        tv_logout= (TextView) getActivity().findViewById(R.id.tv_personal_log_out);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
                Toast.makeText(getContext(),"退出成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
