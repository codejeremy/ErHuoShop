package com.erhuo.erhuoshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.erhuo.erhuoshop.http.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText loginuser;
    private EditText loginsec;
    private Button login;
    private ImageView regist;
    private String loginuserw;
    private String loginsecw;
    private static String User;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(Login.this, "登陆成功，请开始选购商品！", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    Login.this.finish();
                    break;
                case 20:
                    Toast.makeText(Login.this, "登陆失败，请检查用户名或密码是否正确！！！", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginuser = findViewById(R.id.loginuser);
        loginsec = findViewById(R.id.loginsec);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                loginuserw = loginuser.getText().toString().trim();
                loginsecw = loginsec.getText().toString().trim();
                AsyncHttpClient client = new AsyncHttpClient();

                RequestParams params = new RequestParams();

                params.put("method", "_GET");
                params.put("username",loginuserw);

                params.put("userpw",loginsecw);

                client.post("http://172.20.10.3:8000/android_user/", params, new MyTextListener(handler, 2, 20));
                break;
            case R.id.regist:
                Intent intent = new Intent(Login.this,Regist.class);
                startActivity(intent);
                break;
        }
    }
}
