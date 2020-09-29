package com.zhangyf.intelligentbed;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

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
                if (b) {
                    String autoModeTime = etAutoMode.getText().toString();
                    sendMsg("automode:true," + autoModeTime);
                }
            }
        });

        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String windTemp = etWind.getText().toString();
                String waterTemp = etWater.getText().toString();
                sendMsg("windtemp:" + windTemp);
                sendMsg("watertemp:" + waterTemp);
            }
        });

    }

    Socket socket;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    BufferedReader br;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initSocket() {

        new Thread(new Runnable() {
            @Override
            public void run() {
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
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvNum.setText(s);
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
            }
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
                            this,"再按一次退出", Toast.LENGTH_SHORT
                    ).show();
                    quiteTime = System.currentTimeMillis();
                } else {
                    finish();
                }
        }
    }
}