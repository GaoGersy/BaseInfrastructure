package com.gersion.baseinfrastructure;

import com.gersion.baseinfrastructure.bean.CategoryResultBean;
import com.gersion.library.base.BaseDetailActivity;
import com.gersion.library.http.HttpHandler;
import com.gersion.library.utils.LogUtils;

public class MainActivity extends BaseDetailActivity implements HttpHandler.ResultCallBack<CategoryResultBean> {

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        HttpHandler httpHandler = new HttpHandler.Builder(this,this,CategoryResultBean.class)
                .setUrl("http://120.79.49.134:8080/image/getCategoryListByGirlType")
                .setResultCallBack(this)
                .build();
        httpHandler.getBeanData();
    }

    @Override
    public void handleSucess(CategoryResultBean result) {
        setTitle(result.getData().getList().get(0).getTitle());
        LogUtils.d(result);
    }

    @Override
    public void handleError(Throwable e) {
        LogUtils.d(e);
    }
}
