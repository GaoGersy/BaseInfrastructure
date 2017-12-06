package com.gersion.baseinfrastructure;

import com.gersion.baseinfrastructure.bean.CategoryResultBean;
import com.gersion.library.api.HttpHandler;
import com.gersion.library.base.BaseRxDetailActivity;
import com.gersion.library.utils.LogUtils;

public class MainActivity extends BaseRxDetailActivity implements HttpHandler.ResultCallBack<CategoryResultBean> {

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        HttpHandler httpHandler = new HttpHandler.Builder()
                .setContext(this)
                .setUrl("http://120.79.49.134:8080/image/getCategoryListByGirlType")
                .setResultCallBack(this)
                .setClazz(CategoryResultBean.class)
                .setTag(this)
                .build();
        httpHandler.getBeanData();
    }

    @Override
    public void handleSucess(CategoryResultBean response) {
        setTitle(response.getData().getList().get(0).getTitle());
    }

    @Override
    public void handleError(Throwable e) {
        LogUtils.d(e);
    }
}
