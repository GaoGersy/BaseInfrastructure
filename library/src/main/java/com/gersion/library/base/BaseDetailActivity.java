package com.gersion.library.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.zhy.http.okhttp.OkHttpUtils;


public abstract class BaseDetailActivity extends BaseActivity {

    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        setContentView(getContentId());
        initData();
    }

    protected abstract int getContentId();

    protected abstract void initData();

    @Override
    public void setTitle(CharSequence title) {
        if (actionBar != null) actionBar.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        if (actionBar != null) actionBar.setTitle(titleId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
