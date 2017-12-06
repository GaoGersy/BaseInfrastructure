package com.gersion.library.base;

import android.content.Intent;

/**
 * Created by aa326 on 2017/12/6.
 */

public abstract class BasePermissionActivity extends BaseRxDetailActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
