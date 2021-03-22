package com.zhangyf.intelligentbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ip = ((EditText) findViewById(R.id.et_ip)).getText().toString();
                final String port = ((EditText) findViewById(R.id.et_port)).getText().toString();
                if (!ip.isEmpty() && !port.isEmpty()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //尝试连接
                                MyApplication.instance.socket = new Socket(ip, Integer.parseInt(port));
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                finish();
                                startActivity(intent);
                            } catch (final IOException e) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }
}