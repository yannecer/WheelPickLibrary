package com.necer.picklibrary.picker;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.necer.picklibrary.R;
import com.necer.picklibrary.dialog.PickerDialog;

/**
 * Created by necer on 2016/9/12.
 */
public abstract class BasePicker implements View.OnClickListener {

    protected View pickView;
    private PickerDialog dialog;
    protected Context mContext;

    protected TextView mNegative;
    protected TextView mPositive;

    public BasePicker(Context context) {
        this.mContext = context;
        pickView = initPickView();
        initPop();

        mPositive = (TextView) pickView.findViewById(R.id.tv_positive);
        mNegative = (TextView) pickView.findViewById(R.id.tv_negative);
        mNegative.setTag(0);
        mPositive.setTag(1);
        mPositive.setOnClickListener(this);
        mNegative.setOnClickListener(this);

        initView();
        setCycle(true);
    }


    /**
     * 初始化控件
     */
    protected abstract void initView();

    protected void initPop() {
        dialog = new PickerDialog(mContext);
        dialog.setCanceledOnTouchOutside(true);//触摸屏幕取消窗体
        dialog.setCancelable(true);
        dialog.setContentView(pickView);
    }

    public void show() {
        dialog.show();
    }

    protected abstract View initPickView();

    protected abstract void onSure();

    @Override
    public void onClick(View v) {
        int tag=(int)v.getTag();
        switch (tag) {
            case 0:
                dialog.dismiss();
                break;
            case 1:
                onSure();
                dialog.dismiss();
                break;
        }
    }


    public void setNegativeText(String negativeText) {
        mNegative.setText(negativeText);
    }

    public void setPositiveText(String positiveText) {
        mPositive.setText(positiveText);
    }


    public abstract void setCycle(boolean isCycle);

}
