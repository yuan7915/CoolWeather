package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        int pid=intent.getIntExtra("pid",0);
        Log.i("我们接收到了id",""+pid);
        this.textView=(TextView) findViewById(R.id.abc);
        this.button=(Button)findViewById(R.id.btn);
        this.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
        String weatherId="CN101010200";
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=284c0b03c7a247dd8fb171e45f2e0a1e";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(responseText);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }
}
