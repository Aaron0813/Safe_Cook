package com.example.administrator.safecook.activity;
/**
 * Aaron 2016/6/18
 */
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.safecook.R;

import com.example.administrator.safecook.adapter.MyListViewAdapter;
import com.example.administrator.safecook.domain.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    ListView messageListView;
    List<ItemBean> itemBeans=new ArrayList<ItemBean>();
    Button unRead;
    Button deleteDetails;
//    ImageView messageList_Menu;
    TextView tvMessage;

    //为后期连接网络做准备
    protected static final int SUCCESS = 0;
    protected static final int ERROR = 1;
    protected static final int NETERROR = 2;
    protected static final int WRONG = 3;
    //因为后期肯定是要连接网络获取状态，所以使用handler
    private Handler handler=new Handler(){

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    //后期用json解析数据并传入对象
//                    List<ItemBean> itemBeanssBeans=(List<ItemBean>) msg.obj;

                    try {
                        for(int i=0;i<2;i++){
                            itemBeans.add(new ItemBean("检测到燃气泄漏",i+""));
                        }
                        messageListView.setAdapter(new MyListViewAdapter(itemBeans, MessageListActivity.this));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case ERROR:
                    Toast.makeText(MessageListActivity.this, "未获取到正确的JSON数据", Toast.LENGTH_SHORT).show();
                    break;
                case NETERROR:
                    Toast.makeText(MessageListActivity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                    break;
                case WRONG:
                    Toast.makeText(MessageListActivity.this, "捕捉到异常", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        messageListView= (ListView) findViewById(R.id.lv_message_list);

        unRead= (Button) findViewById(R.id.bt_not_read);
        unRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MessageListActivity.this,"标为未读被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        deleteDetails= (Button) findViewById(R.id.bt_delete_record);
        deleteDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MessageListActivity.this,"删除记录被点击了",Toast.LENGTH_SHORT).show();
            }
        });
//        messageList_Menu= (ImageView) findViewById(R.id.iv_message_list_menu);
//        messageList_Menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MessageListActivity.this,"菜单键被点击了",Toast.LENGTH_SHORT).show();
//
//                startActivity(intent);
//            }
//        });
        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(itemBeans.get(i).ItemTitle.equals("检测到燃气泄漏"))
//                {
//                    Toast.makeText(MessageListActivity.this,"啊，我被点击了",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MessageListActivity.this,MessageDetailActivity.class);
                    startActivity(intent);
//                }
            }
        });
//        tvMessage= (TextView) findViewById(R.id.tv_item_name);
//        tvMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MessageListActivity.this,"信息详情被点击了",Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(MessageListActivity.this,MessageDetailActivity.class);
//                startActivity(intent);
//            }
//        });
        Message msg=Message.obtain();
        msg.obj=itemBeans;
        msg.what=SUCCESS;
        handler.sendMessage(msg);
    }
}
