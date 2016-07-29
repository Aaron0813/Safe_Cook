package com.example.administrator.safecook.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.example.administrator.safecook.R;
import com.example.administrator.safecook.base.MyApplication;
import com.example.administrator.safecook.domain.WeatherState;
import com.example.administrator.safecook.utils.StreamUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class IndoorIntroductionFragment extends Fragment implements BDLocationListener{
    public LocationClient mLocationClient = null;
    private static final String PATH="http://wthrcdn.etouch.cn/weather_mini?city=";
    private View view;
    protected static final int SUCCESS = 0;
    protected static final int ERROR = 1;
    protected static final int INVALID_CITY = 2;
    TextView tv_wind,tv_temperature,tv_weather;
    Switch s1,s2,s3,s4;
    private String ul,city="";
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    JSONArray array=(JSONArray) msg.obj;
                    try {
//					String jsonString=array.getString(0);
//					showTextView2.setText(jsonString);
					JSONObject jsonObject=array.getJSONObject(0);

//					WeatherState weatherBean=new WeatherState();
//                        weatherBean.setTemperature(jsonObject.getString("low")+"-"+jsonObject.getString("high"));
//                        weatherBean.setWind(jsonObject.getString("fengli"));
//                        weatherBean.setWeather(jsonObject.getString("type"));
                        tv_wind.setText(jsonObject.getString("fengli").substring(0,2));
                        Log.i("BaiduLocationApiDem", jsonObject.getString("type"));
                        tv_temperature.setText(jsonObject.getString("low").substring(2)+"-"+jsonObject.getString("high").substring(2));
                        tv_weather.setText((jsonObject.getString("type")).trim());
//			    	weatherBean.setHeavy(jsonObject.getString("fengli"));
//			    	weatherBean.setWind(jsonObject.getString("fengxiang"));
//			    	weatherBean.setHighTem(jsonObject.getString("high"));
//			    	weatherBean.setLowTemp(jsonObject.getString("low"));
//			    	weatherBean.setType(jsonObject.getString("type"));
//
//			    	String date=null;
//			    	date=weatherBean.toString();
//			    	System.out.println(date);
//					showTextView1.setText(date);
//                        showTextView1.setText(setView(0, array));
//                        showTextView2.setText(setView(1, array));
//                        showTextView3.setText(setView(2, array));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case ERROR:
                    Toast.makeText(MyApplication.getContext(), "网络有问题", Toast.LENGTH_SHORT).show();
                    break;
                case INVALID_CITY:
                    Toast.makeText(MyApplication.getContext(), "城市错误", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        };
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.activity_main_page,container,false);
        }
         ViewGroup parent=(ViewGroup)view.getParent();
        if (parent!=null){
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(MyApplication.getContext());
        mLocationClient = new LocationClient(MyApplication.getContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(this);    //注册监听函数
        initLocation();
        mLocationClient.start();
        init();

    }

    private void init(){
        //油烟机开关-s1
        s1= (Switch)getActivity(). findViewById(R.id.swt_cook_hood);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"油烟机开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"油烟机开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //燃气灶开关-s2
        s2= (Switch) getActivity().findViewById(R.id.swt_gas_cooker);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"燃气灶开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"燃气灶开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //空气净化器-s3
        s3= (Switch)getActivity(). findViewById(R.id.swt_air_cleaner);
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"空气净化器开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"空气净化器开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        //红外检测器-s4
        s4= (Switch) getActivity().findViewById(R.id.swt_infrared_detector);
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getActivity(),"红外检测器开关已打开",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"红外检测器开关已关闭",Toast.LENGTH_SHORT).show();
            }
        });
        tv_wind= (TextView) getActivity().findViewById(R.id.tv_wind);
        tv_temperature= (TextView) getActivity().findViewById(R.id.tv_temperature);
        tv_weather= (TextView) getActivity().findViewById(R.id.tv_weather);
    }

    /**
     * 初始化定位设置
     */
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// 单位度
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\n describe : ");
            sb.append("gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
            sb.append("\n addr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        List<Poi> list = location.getPoiList();// POI数据
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        sb.append("\ncity=" + location.getCity());
        Log.i("BaiduLocationApiDem", sb.toString());
        city=location.getCity().substring(0,location.getCity().length()-1);
        mLocationClient.stop();
        Thread t=new Thread(new GetWeather());
        t.start();
    }
    private class GetWeather implements Runnable{

        @Override
        public void run() {
            try {
                Log.i("BaiduLocationApiDem", PATH+city);
//                Log.i("BaiduLocationApiDem", city);
                ul=PATH+URLEncoder.encode(city, "UTF-8");
//                ul=PATH+city;
                Log.i("BaiduLocationApiDem", ul);
                URL url=new URL(ul);
                Log.i("BaiduLocationApiDem", "线程启动");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                int code=connection.getResponseCode();
                if (code==200) {
                    InputStream inputStream=connection.getInputStream();
                    String date= StreamUtils.decodeStream(inputStream);
                    //System.out.println(date);
                    JSONObject jsonObject=new JSONObject(date);
                    String state=jsonObject.getString("desc");
                    if ("OK".equals(state)) {
                        //城市信息获取正确
                        JSONObject dateObject=jsonObject.getJSONObject("data");
                        JSONArray dateArray=dateObject.getJSONArray("forecast");
                        Message msg=Message.obtain();
                        msg.obj=dateArray;
                        msg.what=SUCCESS;
                        handler.sendMessage(msg);
                    }
                    else {
                        //城市不存在
                        Message msg=Message.obtain();
                        msg.what=INVALID_CITY;
                        handler.sendMessage(msg);
                    }

                    //showTextView.setText(date);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Message msg=Message.obtain();
                msg.what=ERROR;
                handler.sendMessage(msg);
                e.printStackTrace();
            }
        }
    }


}
