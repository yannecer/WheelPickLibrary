package com.necer.wheelpicklibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.necer.picklibrary.picker.DatePicker;
import com.necer.picklibrary.picker.OptionPicker;

public class MainActivity extends AppCompatActivity {


    private TextView tv_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_ = (TextView) findViewById(R.id.tv_);

    }

    public void bbb(View view) {
        DatePicker datePicker = new DatePicker(this);
        datePicker.setNegativeText("取消");
        datePicker.setPositiveText("确定");
        datePicker.setYearInterval(1945, 2050);
        datePicker.setCycle(true);
        datePicker.setOnDatePickListener(new DatePicker.OnDatePickListener() {
            @Override
            public void onSelect(String year, String month, String day) {
                String s = year + "-" + month + "-" + day;
                tv_.setText(s);

            }
        });
        datePicker.show();
    }

    public void ccc(View view) {
        OptionPicker optionPicker = new OptionPicker(this, new String[]{"aaa", "bbb", "ccc", "ddd"});
        optionPicker.setCycle(true);
        optionPicker.setOnOptionSelectListener(new OptionPicker.OnOptionSelectListener() {
            @Override
            public void onSelect(String option, int index) {
                tv_.setText(option + "----" + index);
            }
        }).show();
    }
}
