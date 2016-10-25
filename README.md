# WheelPickLibrary
一个日期、选项选择Module
##截图：
![截图](https://github.com/yannecer/WheelPickLibrary/blob/master/app/gif/screen.gif)

####DatePicker使用

 ```
        DatePicker datePicker = new DatePicker(this);
        datePicker.setNegativeText("取消");
        datePicker.setPositiveText("确定");
        datePicker.setYearInterval(1945, 2050);//设置年份间隔
        datePicker.setCycle(true);//设置是否循环滑动
        datePicker.setOnDatePickListener(new DatePicker.OnDatePickListener() {
            @Override
            public void onSelect(String year, String month, String day) {
                String s = year + "-" + month + "-" + day;
                tv_.setText(s);
            }
        });
        datePicker.show();
 ```
####OptionPicker使用
```
        OptionPicker optionPicker = new OptionPicker(this, new String[]{"aaa", "bbb", "ccc", "ddd"});
        optionPicker.setCycle(true);
        optionPicker.setOnOptionSelectListener(new OptionPicker.OnOptionSelectListener() {
            @Override
            public void onSelect(String option, int index) {
                tv_.setText(option + "----" + index);
            }
        }).show();

```
###感谢[AndroidPicker](https://github.com/gzu-liyujiang/AndroidPicker) ，修改了这个项目中滑动不能循环的问题
