package com.erhuo.erhuoshop.http;

import android.content.Context;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;

public abstract class SpotsCallBack<T> extends BaseCallback<T> {

    SpotsDialog dialog;
    public SpotsCallBack(Context context){
        super(context);
//        super(context);
//        initSpotsDialog();
        dialog = new SpotsDialog(context,"loading...");

    }
//    private void initSpotsDialog() {
//        dialog = new SpotsDialog(,"拼命加载中。。。。");
//    }

    public void showDialog(){
        dialog.show();
    }
    public void dismissDialog(){
        if(dialog != null)
            dialog.dismiss();
    }
    public void setMessage(String message){
        dialog.setMessage(message);
    }
    @Override
    public void onRequestBefore(Request request) {
        showDialog();
    }

    @Override
    public void onFailure(Request request, Exception e) {
        dismissDialog();

    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
