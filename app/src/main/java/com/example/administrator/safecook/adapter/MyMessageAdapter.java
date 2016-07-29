package com.example.administrator.safecook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.safecook.R;
import com.example.administrator.safecook.domain.MessageItem;
import java.util.List;

/**
 * Created by Aaron on 2016/6/18.
 */
public class MyMessageAdapter extends BaseAdapter{
    public LayoutInflater mInflater;
    private List<MessageItem> mList;
    public MyMessageAdapter(List<MessageItem> list,Context context) {
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
            convertView=mInflater.inflate(R.layout.message_list_item, null);
            viewHolder.time=(TextView) convertView.findViewById(R.id.tv_item_time);
            viewHolder.content=(TextView) convertView.findViewById(R.id.tv_item_content);
            convertView.setTag(viewHolder);
        }
        viewHolder=(ViewHolder) convertView.getTag();
        MessageItem bean=mList.get(position);
        viewHolder.time.setText(bean.getTime());
        viewHolder.content.setText(bean.getContent());
        return convertView;
    }
    class ViewHolder{
        public TextView time;
        public TextView content;

    }

}
