package com.gersion.library.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * ClassName:Utils <br/>
 * Function: 工具类 <br/>
 * Date: 2016年7月10日 下午7:55:04 <br/>
 *
 * @author Ger
 */
public class ToastUtils {
    private static Toast toast;

    public static void show(final Context context, final String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}

