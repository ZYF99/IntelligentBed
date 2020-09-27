package com.zhangyf.intelligentbed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String ip = getIntent().getStringExtra("ip");
        String port = getIntent().getStringExtra("port");
        initSocket(ip, port);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(((EditText) findViewById(R.id.et_msg)).getText().toString());
            }
        });
    }

    Socket socket;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    BufferedReader br;

    private void initSocket(final String ip, final String port) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(ip, Integer.parseInt(port));

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
                                while (true){
                                    final String s = br.readLine();
                                    MainActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void sendMsg(final String msg) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}