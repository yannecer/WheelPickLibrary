package com.necer.picklibrary.picker;

import android.app.Activity;
import android.view.View;

import com.necer.picklibrary.R;
import com.necer.picklibrary.widget.WheelView;
import com.necer.picklibrary.widget.adapters.ArrayWheelAdapter;
import com.necer.picklibrary.widget.adapters.OnWheelSelectListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by necer on 2016/10/24.
 */

public class DatePicker extends BasePicker implements OnWheelSelectListener {
    private WheelView wl_year;
    private WheelView wl_month;
    private WheelView wl_day;

    private ArrayList<String> yearsList = new ArrayList<>();
    private ArrayList<String> monthsList = new ArrayList<>();
    private ArrayList<String> daysList = new ArrayList<>();

    private int beganYear = 1970;
    private int endYear = 2050;


    private String selectYear;
    private String selectMonth;
    private String selectDay;


    private OnDatePickListener mDatePickListener;

    private Calendar calendar = Calendar.getInstance();

    private ArrayWheelAdapter<String> dayWheelAdapter;



    public DatePicker(Activity activity) {
        super(activity);
        initData();
    }

    private void initData() {
        yearsList.clear();
        monthsList.clear();
        daysList.clear();

        for (int i = beganYear; i <= endYear; i++) {
            yearsList.add(String.valueOf(i));
        }
        for (int i = 1; i <= 12; i++) {
            monthsList.add(i < 10 ? "0" + i : String.valueOf(i));
        }
        wl_year.setViewAdapter(new ArrayWheelAdapter<>(mContext, yearsList));
        wl_month.setViewAdapter(new ArrayWheelAdapter<>(mContext, monthsList));
        dayWheelAdapter = new ArrayWheelAdapter<>(mContext, daysList);
        wl_day.setViewAdapter(dayWheelAdapter);

        selectYear = String.valueOf(calendar.get(Calendar.YEAR));
        selectMonth = calendar.get(Calendar.MONTH) + 1 < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : String.valueOf(calendar.get(Calendar.MONTH) + 1);
        selectDay = calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + calendar.get(Calendar.DAY_OF_MONTH) : String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        calculateDate();

        wl_year.setCurrentItem(findItemIndex(yearsList, calendar.get(Calendar.YEAR)));
        wl_month.setCurrentItem(findItemIndex(monthsList, calendar.get(Calendar.MONTH) + 1));
        wl_day.setCurrentItem(findItemIndex(daysList, calendar.get(Calendar.DAY_OF_MONTH)));


    }


    //折半查找有序元素的索引
    private int findItemIndex(ArrayList<String> items, int item) {
        int index = Collections.binarySearch(items, item, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String lhsStr = lhs.toString();
                String rhsStr = rhs.toString();
                return Integer.parseInt(lhsStr) - Integer.parseInt(rhsStr);
            }
        });

        return index;
    }

    @Override
    protected void initView() {
        wl_year = (WheelView) pickView.findViewById(R.id.wl_year);
        wl_month = (WheelView) pickView.findViewById(R.id.wl_month);
        wl_day = (WheelView) pickView.findViewById(R.id.wl_day);



        wl_year.setTag(0);
        wl_month.setTag(1);
        wl_day.setTag(2);

        wl_year.setOnWheelSelectListener(this);
        wl_month.setOnWheelSelectListener(this);
        wl_day.setOnWheelSelectListener(this);

    }

    @Override
    protected View initPickView() {
        return View.inflate(mContext, R.layout.pop_picker_date, null);
    }


    @Override
    protected void onSure() {

        if (mDatePickListener != null) {
            selectYear = yearsList.get(wl_year.getCurrentItem());
            selectMonth = monthsList.get(wl_month.getCurrentItem());
            selectDay = daysList.get(wl_day.getCurrentItem());
            mDatePickListener.onSelect(selectYear, selectMonth, selectDay);
        }
    }

    @Override
    public void setCycle(boolean isCycle) {
        wl_year.setCyclic(isCycle);
        wl_month.setCyclic(isCycle);
        wl_day.setCyclic(isCycle);
    }

    @Override
    public void onItemSeclet(WheelView wheel, int index) {
        int tag=(int)wheel.getTag();
        switch (tag) {
            case 0:
                selectYear = yearsList.get(index);
                calculateDate();
                break;

            case 1:
                selectMonth = monthsList.get(index);
                calculateDate();
                break;

            case 2:
                selectDay = daysList.get(index);
                break;
        }

    }


    public interface OnDatePickListener {
        void onSelect(String year, String month, String day);
    }

    public DatePicker setOnDatePickListener(OnDatePickListener onDatePickListener) {
        this.mDatePickListener = onDatePickListener;
        return this;
    }


    private void calculateDate() {
        int month = Integer.parseInt(selectMonth);
        if (month == 1 ||month == 3 ||month == 5 ||month == 7 ||month == 8 || month ==10 ||month == 12) {
            addDays(31);

        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            addDays(30);
            if (selectDay.equals("31")) {
                wl_day.setCurrentItem(29);
            }

        }

        if (month == 2) {
            if (isCommonYear(selectYear)) {
                addDays(29);
                if (selectDay.equals("30" )|| selectDay.equals("31")) {
                    wl_day.setCurrentItem(28);
                }
            } else {
                addDays(28);
                if (selectDay.equals("30" )|| selectDay.equals("31")||selectDay.equals("29")) {
                    wl_day.setCurrentItem(27);
                }
            }

        }
        //刷新日期
        dayWheelAdapter.notifyDataSetChanged();


    }


    /**
     * 设置
     * @param beganYear
     * @param endYear
     */
    public void setYearInterval(int beganYear,int endYear) {
        this.beganYear = beganYear;
        this.endYear = endYear;
        initData();
    }



    private void addDays(int days) {
        daysList.clear();
        for (int i = 1; i <= days; i++) {
            daysList.add(i < 10 ? "0" + i : String.valueOf(i));
        }
    }


    /**
     * 是否为平年
     * @param selectYear
     * @return
     */
    private boolean isCommonYear(String selectYear) {
        if ((Integer.parseInt(selectYear) % 4 == 0 && (Integer.parseInt(selectYear) % 100 != 0) || (Integer.parseInt(selectYear) % 400 == 0))) {
            return true;
        }
        return false;
    }



}
