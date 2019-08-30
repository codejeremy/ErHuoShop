package com.erhuo.erhuoshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.erhuo.erhuoshop.http.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class Regist extends AppCompatActivity implements View.OnClickListener {

    private EditText registuser;
    private EditText registsec;
    private EditText registemail;
    private EditText registsex;
    private EditText registtel;
    private TextView registnow;
    private String registusers,registsecs,registemails,registsexs,registtels;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(Regist.this, "注册成功，即将前往登录！！！", Toast.LENGTH_LONG).show();//调用
                    Intent intent = new Intent(Regist.this,Login.class);
                    startActivity(intent);
                    Regist.this.finish();
                    break;
                case 20:
                    Toast.makeText(Regist.this, "注册失败，请检查是否符合格式！！！", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        registuser = findViewById(R.id.registuser);
        registsec = findViewById(R.id.registsec);
        registemail = findViewById(R.id.registemail);
        registsex = findViewById(R.id.registsex);
        registtel = findViewById(R.id.registtel);
        registnow = findViewById(R.id.registnow);
        registnow.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registnow:
                registusers = registuser.getText().toString().trim();
                registsecs = registsec.getText().toString().trim();
                registemails = registemail.getText().toString().trim();
                registsexs = registsex.getText().toString().trim();
                registtels = registtel.getText().toString().trim();
                AsyncHttpClient client = new AsyncHttpClient();

                RequestParams params = new RequestParams();

                params.put("method", "_POST");
                params.put("username", registusers);
                params.put("userpw",registsecs);
                params.put("useremail", registemails);
                params.put("usersex", registsexs);
                params.put("usertel", registtels);

                client.post("http://172.20.10.3:8000/android_user/", params, new MyTextListener(handler, 2, 20));

                break;
        }

    }
}
