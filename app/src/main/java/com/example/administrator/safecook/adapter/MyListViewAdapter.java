package com.example.administrator.safecook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.activity.MessageDetailActivity;
import com.example.administrator.safecook.activity.MessageListActivity;
import com.example.administrator.safecook.domain.ItemBean;

import java.util.List;

/**
 * Created by Aaron on 2016/6/18.
 */
public class MyListViewAdapter extends BaseAdapter{
    public LayoutInflater mInflater;
    private List<ItemBean> mList;
    public MyListViewAdapter(List<ItemBean> list, Context context) {
        mList=list;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /////////////////////文艺式/////////////////////////////
        ViewHolder viewHolder;
        if (convertView==null) {
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.activity_message_list_item, null);
            viewHolder.name=(TextView) convertView.findViewById(R.id.tv_item_name);
            viewHolder.content=(TextView) convertView.findViewById(R.id.tv_item_state);
            convertView.setTag(viewHolder);
        }
        viewHolder=(ViewHolder) convertView.getTag();
        ItemBean bean=mList.get(position);
//        TextView tv= (TextView) convertView.findViewById(R.id.tv_item_name);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MessageListActivity.class,MessageDetailActivity.class);
////                    startActivity(intent);
//            }
//        });
        viewHolder.name.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);

        return convertView;
    }
    class ViewHolder{
        public TextView name;
        public TextView content;

    }
}
