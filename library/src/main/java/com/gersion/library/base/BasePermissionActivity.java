package com.gersion.library.base;

import android.content.Intent;

import com.gersion.library.utils.PermissionHelper;

/**
 * Created by aa326 on 2017/12/6.
 */

public abstract class BasePermissionActivity extends BaseDetailActivity {

    private boolean mIsCheckPermission;

    protected void checkPermissions() {
        mIsCheckPermission = true;
    }

    private void onCheck(String... permissions) {
        PermissionHelper.getInstance()
                .requestPermission(this, new PermissionHelper.PermissionResultCallback() {
                    @Override
                    public void onSuccess() {
                        onPermissionSuccess();
                    }

                    @Override
                    public void onFailed() {
                        onPermissionFialed();
                    }
                }, permissions);
    }

    //定位权限申请
    protected void checkLocationPermission() {
        onCheck(PermissionHelper.ACCESS_FINE_LOCATION);
    }

    //相机权限申请
    protected void checkCameraPermission(PermissionHelper.PermissionResultCallback callback) {
        PermissionHelper.getInstance().requestCameraPermission(this, callback);
    }

    //录音权限申请
    protected void checkRecordPermission() {
        onCheck(PermissionHelper.RECORD_AUDIO, PermissionHelper.WRITE_EXTERNAL_STORAGE);
    }

    //联系人权限申请
    protected void checkContactPermission() {
        onCheck(PermissionHelper.READ_CONTACTS);
    }

    //权限申请成功的处理
    protected abstract void onPermissionSuccess();

    //权限申请失败的处理
    protected abstract void onPermissionFialed();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mIsCheckPermission && requestCode == PermissionHelper.PERMISSION_CODE) {
            mIsCheckPermission = false;
            checkPermissions();
        }
    }
    
    
}
