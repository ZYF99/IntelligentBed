package com.zhangyf.intelligentbed;

import android.view.LayoutInflater;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Long quiteTime = System.currentTimeMillis();

    View view_light;
    ConstraintLayout clSetting;
    Button btnBack;
    Button btnTemp;
    SwitchCompat switchAutoMode;
    EditText etAutoMode;
    EditText etWind;
    EditText etWater;
    TextView tvNum;
    TextView tvNumNew;
    ViewPager vpVideo;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;
    Button btn15;
    Button btn16;
    //StandardGSYVideoPlayer videoPlayer;
    Switch switch_video;
    EditText et_rtmp;
    EditText et_rtmp_2;

    VideoPagerAdapter videoPagerAdapter;

    List<View> videoViewList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSocket();
        view_light = findViewById(R.id.view_light);
        clSetting = findViewById(R.id.cl_setting);
        btnBack = findViewById(R.id.btn_back);
        btnTemp = findViewById(R.id.btn_set_temp);
        switchAutoMode = findViewById(R.id.switch_auto_mode);
        etAutoMode = findViewById(R.id.et_auto_mode);
        etWind = findViewById(R.id.et_wind);
        etWater = findViewById(R.id.et_water);
        tvNum = findViewById(R.id.tv_num);
        tvNumNew = findViewById(R.id.tv_num_new);

        vpVideo = findViewById(R.id.vp_video);
        //videoPlayer = findViewById(R.id.video_player);
        et_rtmp = findViewById(R.id.et_rtmp);
        et_rtmp_2 = findViewById(R.id.et_rtmp_2);
        switch_video = findViewById(R.id.switch_video);

        vpVideo = findViewById(R.id.video_player);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn10 = findViewById(R.id.btn_10);
        btn11 = findViewById(R.id.btn_11);
        btn12 = findViewById(R.id.btn_12);
        btn13 = findViewById(R.id.btn_13);
        btn14 = findViewById(R.id.btn_14);
        btn15 = findViewById(R.id.btn_15);
        btn16 = findViewById(R.id.btn_16);

        vpVideo = findViewById(R.id.vp_video);


        View videoViewItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_video,null,false);
        View videoViewItem2 = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_video,null,false);

        videoViewList.add(videoViewItem);
        videoViewList.add(videoViewItem2);

        switch_video.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if(et_rtmp.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"请先在设置中设置视频流地址",Toast.LENGTH_SHORT).show();
                }else{
                    vpVideo.setVisibility(View.VISIBLE);
                    videoPagerAdapter = new VideoPagerAdapter(MainActivity.this, videoViewList);
                    vpVideo.setAdapter(videoPagerAdapter);
                }
            } else {
                vpVideo.setVisibility(View.GONE);
                videoPagerAdapter.releaseVideo();
            }
        });

        ((StandardGSYVideoPlayer)videoViewItem.findViewById(R.id.video_player)).startPlayLogic();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.SHENGBEI);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.JIANGBEI);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.BEI_FUWEI);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.SHENGTUI);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.JIANGTUI);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.TUI_FUWEI);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.ZUOFAN);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.YOUFAN);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.FAN_FUWEI);
            }
        });


        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.QIAN);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.HOU);
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.ZUO);
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.YOU);
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.RUCE);
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.CHONGXI);
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(Constants.DIRRST);
            }
        });

        ((androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar)).setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_setting) {
                    clSetting.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clSetting.setVisibility(View.GONE);

            }
        });

        switchAutoMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sendMsg(String.valueOf(b));
            }
        });




        vpVideo.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    ((StandardGSYVideoPlayer)videoViewItem2.findViewById(R.id.video_player)).onVideoPause();
                    ((StandardGSYVideoPlayer)videoViewItem.findViewById(R.id.video_player)).startPlayLogic();
                }else {
                    ((StandardGSYVideoPlayer)videoViewItem.findViewById(R.id.video_player)).onVideoPause();
                    ((StandardGSYVideoPlayer)videoViewItem2.findViewById(R.id.video_player)).startPlayLogic();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnTemp.setOnClickListener(view -> {
            String windTemp = etWind.getText().toString();
            String waterTemp = etWater.getText().toString();
            String autoTime = etAutoMode.getText().toString();
            String msg = "autoTime:" + autoTime + " windTemp:" + windTemp + " waterTemp:" + waterTemp;
            Constants.rtmpAddress_1 = et_rtmp.getText().toString();
            Constants.rtmpAddress_2 = et_rtmp_2.getText().toString();
            sendMsg(msg);
            //sendMsg("autoTime:" + autoTime);
            //sendMsg("windtemp:" + windTemp);
            //sendMsg("watertemp:" + waterTemp);
        });

        //初始化视频播放器
        //initVideoPlayer();

    }

    Socket socket;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    BufferedReader br;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initSocket() {

        new Thread(() -> {
            socket = MyApplication.instance.socket;
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    //连接成功 指示灯绿色
                    view_light.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
                    try {
                        // 步骤1：创建输入流对象InputStream
                        is = socket.getInputStream();
                        // 步骤2：创建输入流读取器对象 并传入输入流对象
                        // 该对象作用：获取服务器返回的数据
                        isr = new InputStreamReader(is);
                        br = new BufferedReader(isr);
                        while (true) {
                            final String s = br.readLine();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvNumNew.setText(s);
                                    //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (IOException e) {
                        //连接断开 指示灯红色
                        view_light.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        e.printStackTrace();
                    }
                }
            }).start();
        }).start();

    }

    public void sendMsg(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 步骤2：写入需要发送的数据到输出流对象中
                try {
                    // 步骤1：从Socket 获得输出流对象OutputStream
                    // 该对象作用：发送数据
                    os = socket.getOutputStream();
                    os.write((msg + "\n").getBytes("utf-8"));
                    // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

                    // 步骤3：发送数据到服务端
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 步骤3：断开客户端 & 服务器 连接
        try {
            // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
            os.close();
            // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader
            br.close();

            socket.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (clSetting.getVisibility() == View.VISIBLE) {
            clSetting.setVisibility(View.GONE);
        } else {
            if (System.currentTimeMillis() - quiteTime > 3000) {
                Toast.makeText(
                        this, "再按一次退出", Toast.LENGTH_SHORT
                ).show();
                quiteTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }
}