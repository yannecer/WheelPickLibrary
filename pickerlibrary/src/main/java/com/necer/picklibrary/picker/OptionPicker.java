package com.necer.picklibrary.picker;

import android.app.Activity;
import android.view.View;

import com.necer.picklibrary.R;
import com.necer.picklibrary.widget.WheelView;
import com.necer.picklibrary.widget.adapters.ArrayWheelAdapter;
import com.necer.picklibrary.widget.adapters.OnWheelSelectListener;


/**
 * Created by zhuodao on 2016/9/12.
 */
public class OptionPicker extends BasePicker {

    private WheelView option;
    private String[] items;
    private int index;
    protected OnOptionSelectListener mOptionSelectListener;
    public OptionPicker(Activity activity) {
        super(activity);
    }

    public OptionPicker(Activity activity, String[] items) {
        super(activity);
        setOptionItems(items);

    }

    @Override
    protected void initView() {
        option = (WheelView) pickView.findViewById(R.id.option);
        option.setOnWheelSelectListener(new OnWheelSelectListener() {
            @Override
            public void onItemSeclet(WheelView wheel, int index1) {
                index = index1;
            }
        });
    }




    @Override
    protected View initPickView() {
        View view = View.inflate(mContext, R.layout.pop_picker_option, null);
        return view;
    }

    @Override
    protected void onSure() {

        if (mOptionSelectListener != null) {
            mOptionSelectListener.onSelect(items[index],index);
        }
    }

    @Override
    public void setCycle(boolean isCycle) {
        option.setCyclic(isCycle);
    }

    public void setOptionItems(String[] items) {

        this.items = items;
        option.setViewAdapter(new ArrayWheelAdapter<>(mContext,items));
    }



    public interface OnOptionSelectListener {
        /**
         * 选择的内容和下标
         * @param option
         * @param index
         */
        void onSelect(String option, int index);
    }

    public BasePicker setOnOptionSelectListener(OnOptionSelectListener onSelectListener) {
        this.mOptionSelectListener = onSelectListener;
        return this;
    }

}
