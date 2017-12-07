package com.gersion.library.callback;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public  abstract class CallBack extends StringCallback {
    boolean isBeanCallback;

    public boolean isBeanCallback() {
        return isBeanCallback;
    }

    public void setBeanCallback(boolean beanCallback) {
        isBeanCallback = beanCallback;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {

    }

}
