package com.example.administrator.safecook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.domain.Message;
import com.example.administrator.safecook.domain.MyMessage;
import com.example.administrator.safecook.domain.MyUser;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/7/23.
 */
public class MessageListFragment extends Fragment {
    private View view;
    Button bt_notRead;
    Button bt_delete;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_message_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bt_notRead= (Button) getActivity().findViewById(R.id.bt_not_read);
        bt_delete= (Button) getActivity().findViewById(R.id.bt_delete_record);
        bt_notRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMessage myMessage =new MyMessage();
                myMessage.setContent("你家一切正常，好好睡觉吧");
                myMessage.setSafe(true);
                MyUser myUser= BmobUser.getCurrentUser(MyUser.class);
                myMessage.setUserID(myUser.getObjectId());
//                Toast.makeText(getContext(),myUser.getObjectId(),Toast.LENGTH_SHORT).show();
                myMessage.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(getContext(),"添加正常信息成功",Toast.LENGTH_SHORT).show();
//                            Log.i("save","添加正常信息成功");
                        }else
                            Toast.makeText(getContext(),"添加正常信息失败",Toast.LENGTH_SHORT).show();
//                             Log.i("save","添加正常信息失败");
                    }
                });
            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMessage myMessage =new MyMessage();
                myMessage.setContent("你家失火啦，赶紧回家救火去");
                myMessage.setSafe(false);
                MyUser myUser= BmobUser.getCurrentUser(MyUser.class);
                myMessage.setUserID(myUser.getObjectId());
                myMessage.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(getContext(),"添加危急信息成功",Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(getContext(),"添加危急信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
//        bt_notRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bmobPushManager.pushMessageAll("这是给所有设备推送的一条消息。");
////                Toast.makeText(getActivity(),"111",Toast.LENGTH_SHORT).show();
//                test();
//            }
//        });
    }
}
