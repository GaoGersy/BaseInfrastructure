package com.gersion.library.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gersion.library.R;

/**
 * Created by aa326 on 2017/12/7.
 */

public class LoadingDialog {
    public static Dialog getDialog(Context context,String loadingMsg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        TextView tvMsg = view.findViewById(R.id.tv_msg);
        tvMsg.setText(loadingMsg);
        Dialog dialog = new Dialog(context, R.style.loading_dialog);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        dialog.show();
        return dialog;
    }

}
