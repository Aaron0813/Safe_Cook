package com.example.administrator.safecook.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup.*;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.safecook.R;
import com.example.administrator.safecook.domain.Person;
import com.example.administrator.safecook.fragment.DeviceDetailFragment;
import com.example.administrator.safecook.fragment.IndoorIntroductionFragment;
import com.example.administrator.safecook.fragment.MessageListFragment;
import com.example.administrator.safecook.fragment.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2016/7/23.
 */
public class MainFragmentActivity extends FragmentActivity {
    private Button bt_notRead;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDates;
    private TextView tv_indoorIntroduction;
    private TextView tv_deviceDetail;
    private TextView tv_messageList;
    private TextView tv_personal;
    private int mScreenThird;
    private ImageView imageView;
    private int mCurrentPageIndex;


    private static String APPID = "e8df3a4a59521a65d38869db05fb1210";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化BmobSDK
        Bmob.initialize(this, APPID);

        setContentView(R.layout.fragment_main_page);
        initTabLine();
        init();
    }
    void test(){
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(MainFragmentActivity.this,"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_SHORT).show();
                }else{
//                    toast("创建数据失败：" + e.getMessage());
                    Toast.makeText(MainFragmentActivity.this,"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void init(){

        tv_indoorIntroduction = (TextView) findViewById(R.id.id_tv_introduction);
        tv_deviceDetail = (TextView) findViewById(R.id.id_tv_deviceDetails);
        tv_messageList = (TextView) findViewById(R.id.id_tv_messageList);
        tv_personal = (TextView) findViewById(R.id.id_tv_personal);
        mViewPager = (ViewPager) findViewById(R.id.myViewPager);
        mDates = new ArrayList<Fragment>();
        IndoorIntroductionFragment indoorIntroductionFragment = new IndoorIntroductionFragment();
        DeviceDetailFragment deviceFragment = new DeviceDetailFragment();
        MessageListFragment messageFragment = new MessageListFragment();
        PersonalFragment personalFragment = new PersonalFragment();
        mDates.add(indoorIntroductionFragment);
        mDates.add(deviceFragment);
        mDates.add(messageFragment);
        mDates.add(personalFragment);


        /*测试推送*/
//        bt_notRead= (Button) messageFragment.getActivity().findViewById(R.id.bt_not_read);

        // 添加一个监听页面转换的事件，从而完成页面转换时颜色改变的效果
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mDates.size();
            }
            @Override
            public Fragment getItem(int arg0) {
                return mDates.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        // 添加一个页面转换的监听事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        tv_indoorIntroduction.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        tv_deviceDetail.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        tv_messageList.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 3:
                        tv_personal.setTextColor(Color.parseColor("#008000"));
                        break;
                }
                // 获取页码偏移
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx) {
                LinearLayout.LayoutParams lParams = (android.widget.LinearLayout.LayoutParams) imageView
                        .getLayoutParams();
                // 0-》1页时的情况
                if (mCurrentPageIndex == 0 && position == 0) {
                    lParams.leftMargin = (int) ((positionOffset + mCurrentPageIndex) * mScreenThird);
                } else if (mCurrentPageIndex == 1 && position == 0) {// 1->0
                    lParams.leftMargin = (int) ((positionOffset
                            + mCurrentPageIndex - 1) * mScreenThird);
                } else if (mCurrentPageIndex == 1 && position == 1) {// 1->2
                    lParams.leftMargin = (int) ((positionOffset + mCurrentPageIndex) * mScreenThird);
                } else if (mCurrentPageIndex == 2 && position == 1) {// 2->1
                    lParams.leftMargin = (int) ((positionOffset
                            + mCurrentPageIndex - 1) * mScreenThird);
                } else if (mCurrentPageIndex == 2 && position == 2) {// 2->3
                    lParams.leftMargin = (int) ((positionOffset + mCurrentPageIndex) * mScreenThird);
                } else if (mCurrentPageIndex == 3 && position == 2) {// 2->1
                    lParams.leftMargin = (int) ((positionOffset
                            + mCurrentPageIndex - 1) * mScreenThird);
                }

                imageView.setLayoutParams(lParams);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    private void initTabLine(){
        imageView = (ImageView) findViewById(R.id.id_iv_tabline);
        // display对象中包含了屏幕的宽度和高度信息
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreenThird = outMetrics.widthPixels / 4;
        LayoutParams lp = imageView.getLayoutParams();
        lp.width = mScreenThird;
        imageView.setLayoutParams(lp);
    }

    // 每一次都会先重置所有的字体颜色， 然后才改变颜色
    protected void resetTextView() {
        tv_indoorIntroduction.setTextColor(Color.BLACK);
        tv_deviceDetail.setTextColor(Color.BLACK);
        tv_messageList.setTextColor(Color.BLACK);
        tv_personal.setTextColor(Color.BLACK);
    }
}
