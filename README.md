# WheelPickLibrary
一个日期、选项选择Module
##截图：
![截图](https://github.com/yannecer/WheelPickLibrary/blob/master/app/gif/screen.gif)

##用法

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


