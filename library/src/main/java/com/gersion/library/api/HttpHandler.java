/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gersion.library.api;

import android.app.Dialog;
import android.content.Context;

import com.gersion.library.bean.ParamBean;
import com.gersion.library.convert.CallBack;
import com.gersion.library.utils.GsonQuick;
import com.gersion.library.utils.LoadingDialog;
import com.gersion.library.utils.LogUtils;
import com.gersion.library.utils.ToastUtils;
import com.lzy.okgo.model.HttpMethod;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.MediaType;

public class HttpHandler {
    private String header;
    private String paramJson;
    private ParamBean paramBean;
    private Map<String, Object> paramMap;
    private String url;
    private String errorMsg;
    private String loadingMsg;
    private Dialog dialog;
    private Context context;
    private HttpMethod httpMethod;
    private ResultCallBack mResultCallBack;
    private Class clazz;
    private Object tag;

    private HttpHandler(ResultCallBack resultCallBack) {
        mResultCallBack = resultCallBack;
    }

    public void showLoading() {
        if (dialog != null) {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } else {
            dialog = LoadingDialog.getDialog(context, loadingMsg);
        }
    }

    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public <T> void getString() {
        if (httpMethod == HttpMethod.GET) {
            getJson(false);
        } else {
            postJson(false);
        }
    }

    public <T> void getBeanData() {
        if (httpMethod == HttpMethod.GET) {
            getJson(true);
        } else {
            postJson(true);
        }
    }

    private void getJson(boolean isBeanCallback) {
        mCallBack.setBeanCallback(isBeanCallback);
        showLoading();
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .build()
                .execute(mCallBack);
    }

    private void postJson(boolean isBeanCallback) {
        if (paramMap != null) {
            postJson(paramMap,isBeanCallback);
        } else if (this.paramBean != null) {
            postJson(this.paramBean,isBeanCallback);
        } else {
            postJson(paramJson,isBeanCallback);
        }
    }

    private void postJson(ParamBean paramBean, boolean isBeanCallback) {
        String paramJson = GsonQuick.toJsonFromBean(paramBean);
        postJson(paramJson,isBeanCallback);
    }

    private void postJson(Map<String, Object> paramMap,boolean isBeanCallback) {
        String paramJson = GsonQuick.toJsonFromMap(paramMap);
        postJson(paramJson,isBeanCallback);
    }

    private void postJson(String paramJson,boolean isBeanCallback) {
        mCallBack.setBeanCallback(isBeanCallback);
        showLoading();
        OkHttpUtils
                .postString()
                .url(url)
                .tag(tag)
                .content(paramJson)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(mCallBack);
    }

    private CallBack mCallBack = new CallBack() {
        @Override
        public void onError(Call call, Exception e, int id) {
            mResultCallBack.handleError(e);
            dismissLoading();
            showToast(errorMsg);
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtils.e(response);
            if (isBeanCallback()) {
                mResultCallBack.handleSucess(GsonQuick.fromJsontoBean(response, clazz));
            }else {
                mResultCallBack.handleSucess(response);
            }
            dismissLoading();
        }
    };

    private void showToast(String errorMsg) {
        ToastUtils.show(context, errorMsg);
    }

    public interface ResultCallBack<T> {
        void handleSucess(T result);

        void handleError(Throwable e);

        void addDisposable(Disposable d);
    }

    public static class Builder {
        private String header = "header";
        private String paramJson = "";
        private ParamBean paramBean;
        private Map<String, Object> paramMap;
        private String url;
        private String errorMsg = "数据请求出错...";
        private String loadingMsg = "正在奋力的加载中...";
        private Dialog dialog;
        private Context context;
        private HttpMethod httpMethod = HttpMethod.GET;
        private ResultCallBack resultCallBack;
        private Class clazz;
        private Object tag;

        public Builder(Context context, Object tag, Class beanClass) {
            this.context = context;
            this.clazz = beanClass;
            this.tag = tag;
        }

        public Builder setHeader(String header) {
            this.header = header;
            return this;
        }

        public Builder setParamJson(String paramJson) {
            this.paramJson = paramJson;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public Builder setLoadingMsg(String loadingMsg) {
            this.loadingMsg = loadingMsg;
            return this;
        }

        public Builder setDialog(Dialog dialog) {
            this.dialog = dialog;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setHttpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Builder setResultCallBack(ResultCallBack resultCallBack) {
            this.resultCallBack = resultCallBack;
            return this;
        }

        public Builder setClazz(Class clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder setTag(Object tag) {
            this.tag = tag;
            return this;
        }

//        public Builder withBaseUrl(String url){
//            this.url =
//        }

        public HttpHandler build() {
            if (resultCallBack == null) {
                throw new NullPointerException("resultCallBack 不能为空...");
            }
            if (context == null) {
                throw new NullPointerException("context 不能为空...");
            }
            if (url == null) {
                throw new NullPointerException("url 不能为空...");
            }
            if (clazz == null) {
                throw new NullPointerException("clazz 不能为空...");
            }
            if (tag == null) {
                throw new NullPointerException("tag 不能为空...");
            }
            HttpHandler httpHandler = new HttpHandler(resultCallBack);
            httpHandler.context = this.context;
            httpHandler.header = this.header;
            httpHandler.paramJson = this.paramJson;
            httpHandler.paramBean = this.paramBean;
            httpHandler.paramMap = this.paramMap;
            httpHandler.errorMsg = this.errorMsg;
            httpHandler.loadingMsg = this.loadingMsg;
            httpHandler.dialog = this.dialog;
            httpHandler.httpMethod = this.httpMethod;
            httpHandler.url = this.url;
            httpHandler.clazz = this.clazz;
            httpHandler.tag = this.tag;
            return httpHandler;
        }
    }
}
