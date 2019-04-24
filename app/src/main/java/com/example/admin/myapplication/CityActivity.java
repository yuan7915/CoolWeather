package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private ListView listView;
    private AdapterView listview;

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
                startActivity(new Intent(CityActivity.this,Main2Activity.class));
            }
        });
        this.listView=(ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                Log.i("点击了哪一个",""+position+":"+cids[position]+":"+data[position]);
                Intent intent=new Intent(CityActivity.this,CountyActivity.class);
                intent.putExtra("cid",cids[position]);
                intent.putExtra("pid",pid);
                startActivity(intent);
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
