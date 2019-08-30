package com.erhuo.erhuoshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.erhuo.erhuoshop.http.GifSizeFilter;
import com.erhuo.erhuoshop.http.Glide4Engine;
import com.erhuo.erhuoshop.http.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class FabuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button shangchaun = null;
    private ImageView tupian1 = null;
    private ImageView tupian2 = null;
    private ImageView tupian3 = null;
    private EditText fabucontent = null;
    private EditText fabuprice = null;
    private String fabuc,fabup;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(FabuActivity.this, "上传成功！！！", Toast.LENGTH_LONG).show();//调用
                    break;
                case 20:
                    Toast.makeText(FabuActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu);
        shangchaun  = findViewById(R.id.shangchaun);
        shangchaun.setOnClickListener(this);

        tupian1  = findViewById(R.id.tupian1);
        tupian2  = findViewById(R.id.tupian2);
        tupian3  = findViewById(R.id.tupian3);
        fabucontent = findViewById(R.id.fabu_content);
        fabuprice = findViewById(R.id.fabu_price);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.shangchaun:
                Matisse.from(FabuActivity.this)
                        .choose(MimeType.ofAll(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                        .maxSelectable(3)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(
                                getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                        .imageEngine(new Glide4Engine())    // for glide-V4
                        .setOnSelectedListener(new OnSelectedListener() {
                            @Override
                            public void onSelected(
                                    @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("onSelected", "onSelected: pathList=" + pathList);

                            }
                        })
                        .originalEnable(true)
                        .maxOriginalSize(10)
//                                    .autoHideToolbarOnSingleTap(true)
                        .setOnCheckedListener(new OnCheckedListener() {
                            @Override
                            public void onCheck(boolean isChecked) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                            }
                        })
                        .forResult(23);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 23 && resultCode == RESULT_OK) {
            String str = String.valueOf(Matisse.obtainPathResult(data));
            String[] str1 = str.split(",");

            str1[0] = str1[0].substring(1);

            str1[str1.length-1] = str1[str1.length-1].substring(0,str1[str1.length-1].length()-1);
            List<String> list = new ArrayList<String>();
            for(String string : str1){

                String[] name = string.split("/");
                String name1 = name[name.length-1];
                String urll = "http://172.20.10.3:8000/media/"+name1;
                list.add(urll);
            }

            Glide.with(FabuActivity.this).load(str1[0]).into(tupian1);
            Glide.with(FabuActivity.this).load(str1[1].substring(1)).into(tupian2);
            Glide.with(FabuActivity.this).load(str1[2].substring(1)).into(tupian3);
            AsyncHttpClient client = new AsyncHttpClient();

            RequestParams params = new RequestParams();
            fabuc = fabucontent.getText().toString().trim();
            fabup = fabuprice.getText().toString().trim();
            params.put("method", "_POST");
            params.put("content", fabuc);
            params.put("price", fabup);
            params.put("imgurl",str1[0]);
            params.put("imgurl2",str1[1].substring(1));
            params.put("imgurl3", str1[2].substring(1));
            client.post("http://172.20.10.3:8000/android_fabu/", params, new MyTextListener(handler, 2, 20));

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    UpLoadPhotos.init(str1);
//                    Log.e("jianming","liat: ========"+list.toString());
//                    for(int i = 0; i  < 3; i++){
//                        Log.e("jianming","liat: ========"+str1[i]);
//                    }
//                }
//            }).start();
        }
    }
}
