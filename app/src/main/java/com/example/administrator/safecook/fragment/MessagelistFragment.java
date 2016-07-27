package com.example.administrator.safecook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.domain.Message;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/7/23.
 */
public class MessageListFragment extends Fragment {
    private View view;
    Button bt_notRead;
    Button bt_delete;
//    BmobPushManager bmobPushManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_message_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation().save();

        // 启动推送服务
        BmobPush.startWork(getContext());

        // 创建推送消息的对象
//        bmobPushManager = new BmobPushManager();

        bt_notRead= (Button) getActivity().findViewById(R.id.bt_not_read);
        bt_delete= (Button) getActivity().findViewById(R.id.bt_delete_record);
        bt_notRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bmobPushManager.pushMessageAll("这是给所有设备推送的一条消息。");
                Toast.makeText(getActivity(),"111",Toast.LENGTH_SHORT).show();
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

    void test(){
        Message p2 = new Message();
        p2.setContent("这是给所有设备推送的一条消息");
        p2.setData("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(getActivity(),"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_SHORT).show();
                }else{
//                    toast("创建数据失败：" + e.getMessage());
                    Toast.makeText(getActivity(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
