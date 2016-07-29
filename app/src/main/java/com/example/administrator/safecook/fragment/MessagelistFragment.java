package com.example.administrator.safecook.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.adapter.MyMessageAdapter;
import com.example.administrator.safecook.base.MyApplication;
import com.example.administrator.safecook.domain.MessageItem;
import com.example.administrator.safecook.domain.MyMessage;
import com.example.administrator.safecook.domain.MyUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/7/23.
 */
public class MessageListFragment extends Fragment {
    List<MessageItem> itemBeans=new ArrayList<>();
    private String userID;
    private Integer messageNumber=20;
    private View view;
    Button bt_notRead,bt_delete;
    private ListView listView;
    protected static final int SUCCESS = 0;
    protected static final int ERROR = 1;
    protected static final int WRONG = 3;
    private Handler handler=new Handler(){

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SUCCESS:

                    List<MessageItem> messageItems=(List<MessageItem>) msg.obj;
                    for(MessageItem messageItem:messageItems){
                        Log.i("BaiduLocationApiDem",messageItem.toString());
                    }
                    try {
                        Log.d("aaa",listView + "----------------------------------------------");
                        listView.setAdapter(new MyMessageAdapter(messageItems, getActivity()));
//testTextView.setText(textString);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case ERROR:
                    Toast.makeText(MyApplication.getContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_message_list,container,false);
//        getMessageList();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bt_notRead= (Button) getActivity().findViewById(R.id.bt_not_read);
        listView= (ListView) getActivity().findViewById(R.id.lv_message_list);
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
        if(BmobUser.getCurrentUser()!=null) {
            userID = BmobUser.getCurrentUser().getObjectId();
            getMessageList();
        }
    }

    private void getMessageList(){
        BmobQuery<MyMessage> query=new BmobQuery<>();
        query.addWhereEqualTo("userID", userID);
        query.setLimit(messageNumber);
        query.findObjects(new FindListener<MyMessage>() {
            @Override
            public void done(List<MyMessage> list, BmobException e) {
                //说明查询成功
                if(e==null){
                    Message msg= Message.obtain();
                    List<MessageItem> tempList=new ArrayList<>();
                    for (MyMessage myMessage:list){
                        tempList.add(new MessageItem(myMessage.getContent(),myMessage.getCreatedAt()));
                    }
                    msg.obj=tempList;
                    msg.what=SUCCESS;
                    handler.sendMessage(msg);
                    Log.i("BaiduLocationApiDem","消息发送成功");
                }else {
                    Message msg=Message.obtain();
                    msg.what=ERROR;
                    handler.sendMessage(msg);
                }
            }
        });
    }
}
