package com.zhangyf.intelligentbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("ip", ((EditText) findViewById(R.id.et_ip)).getText().toString());
                intent.putExtra("port", (((EditText) findViewById(R.id.et_port)).getText().toString()));
                startActivity(intent);
            }
        });
    }
}