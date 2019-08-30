package com.erhuo.erhuoshop.http;

import android.content.Context;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseCallback<T> {
    Type mType;
    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
    public BaseCallback(Context context)
    {
        mType = getSuperclassTypeParameter(getClass());
    }
    public abstract void onRequestBefore(Request request);
    public abstract  void onFailure(Request request, Exception e) ;
    public abstract void onSuccess(Response response,T t) ;
    public abstract void onError(Response response, int code,Exception e) ;

    public abstract void onResponse(Response response);
}
