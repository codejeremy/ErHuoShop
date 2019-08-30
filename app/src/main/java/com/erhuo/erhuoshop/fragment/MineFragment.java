package com.erhuo.erhuoshop.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erhuo.erhuoshop.FabuActivity;
import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.http.JSONTOOL;
import com.erhuo.erhuoshop.http.MyTextListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends Fragment implements View.OnClickListener {
    private TextView fabugoods = null;
    private TextView showname = null;
    private TextView showtel = null;
    private TextView showemail = null;
    private TextView shownicheng = null;
    private ImageView touxiang = null;
    private Uri imageuri;
    public static final int TAKE_PHOTO =1;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 3:
                    HashMap<String,String> map = JSONTOOL.analyze_once_json(msg.obj.toString());
                    String tel =  map.get("usertel");
                    String email =  map.get("useremail");
                    String nicheng =  map.get("usersex");
                    String name =  map.get("username");
                    showname.setText(name);
                    showemail.setText(email);
                    shownicheng.setText(nicheng);
                    showtel.setText(tel);
                    Log.e("2222222222222222222222", "handleMessage: "+map );

                    Toast.makeText(getActivity(), "信息更新成功", Toast.LENGTH_LONG).show();//信息框

                    break;
                case 30:
                    Toast.makeText(getActivity(), "信息更新失败", Toast.LENGTH_LONG).show();//信息框
//                    t.show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_mine,container,false);
        fabugoods = view.findViewById(R.id.fabugoods);
        fabugoods.setOnClickListener(this);
        showname = view.findViewById(R.id.name);
        showtel = view.findViewById(R.id.tel);
        showemail = view.findViewById(R.id.email);
        shownicheng = view.findViewById(R.id.nicheng);
        touxiang = view.findViewById(R.id.touxiang);
        touxiang.setOnClickListener(this);
        showInfo();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabugoods:
                Intent intent = new Intent(getActivity(),FabuActivity.class);
                startActivity(intent);
                break;
            case R.id.touxiang:
//                touxiang.getBackground().setAlpha(100);
                File outputImage = new File(getActivity().getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageuri = FileProvider.getUriForFile(getActivity(),"com.example.cameraalbumtestthree.fileprovider",outputImage);
                }else {
                    imageuri = Uri.fromFile(outputImage);
                }
                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                intent1.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
                startActivityForResult(intent1,TAKE_PHOTO);
        }

    }
    private void showInfo() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("method", "_GET");
        params.put("username","jeremy");
        params.put("userpw","1");
        client.post("http://172.20.10.3:8000/android_user/", params, new MyTextListener(this.handler, 3, 30));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageuri));
                        touxiang.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
