package com.erhuo.erhuoshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SharedPreferences sp = getSharedPreferences("ID", MODE_PRIVATE);
//        if(sp.contains("222")){
//            loverID = sp.getString("loverid",null);
//        }else{
//            Intent intent = new Intent(LunchActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(LunchActivity.this,Login.class);
                        startActivity(intent);
                        LunchActivity.this.finish();
                    }
                });
            }
        }).start();
        setContentView(R.layout.activity_lunch);
    }
}
