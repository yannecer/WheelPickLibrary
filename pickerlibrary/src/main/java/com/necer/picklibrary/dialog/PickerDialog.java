package com.necer.picklibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.necer.picklibrary.R;

/**
 * Created by zhuodao on 2016/10/10.
 */

public class PickerDialog extends Dialog {
    private Context mContext;

    public PickerDialog(Context context) {
        super(context, R.style.PickerDialog);
        this.mContext = context;
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

}