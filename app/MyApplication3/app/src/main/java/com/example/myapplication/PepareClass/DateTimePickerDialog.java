package com.example.myapplication.PepareClass;

import java.util.Calendar;

import com.example.myapplication.PepareClass.DateTimePicker.OnDateTimeChangedListener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.format.DateFormat;
import android.text.format.DateUtils;


//时间选择对话框
public class DateTimePickerDialog extends AlertDialog implements OnClickListener {

    private Calendar mDate = Calendar.getInstance();
    private boolean mIs24HourView;
    private OnDateTimeSetListener mOnDateTimeSetListener;
    private DateTimePicker mDateTimePicker;

    public interface OnDateTimeSetListener {
        void OnDateTimeSet(AlertDialog dialog, long date);
    }

    public DateTimePickerDialog(Context context, long date) {
        super(context);
        mDateTimePicker = new DateTimePicker(context);
        setView(mDateTimePicker);
        mDateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener() {
            public void onDateTimeChanged(DateTimePicker view, int year, int month,
                                          int dayOfMonth, int hourOfDay, int minute) {
                mDate.set(Calendar.YEAR, year);//设置年
                mDate.set(Calendar.MONTH, month);//设置月
                mDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);//设置日
                mDate.set(Calendar.HOUR_OF_DAY, hourOfDay);//设置时
                mDate.set(Calendar.MINUTE, minute);//设置分
                updateTitle(mDate.getTimeInMillis());//更新标题时间
            }
        });
        mDate.setTimeInMillis(date);
        mDate.set(Calendar.SECOND, 0);
        mDateTimePicker.setCurrentDate(mDate.getTimeInMillis());
        //确定与取消按钮
        setButton("确定",this);
        setButton2("取消",(OnClickListener)null);
        set24HourView(DateFormat.is24HourFormat(this.getContext()));//设置为24小时制
        updateTitle(mDate.getTimeInMillis());
    }

    //24小时设置
    public void set24HourView(boolean is24HourView) {
        mIs24HourView = is24HourView;
    }

    public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
        mOnDateTimeSetListener = callBack;
    }

    //更新标题时间
    private void updateTitle(long date) {
        int flag =
                DateUtils.FORMAT_SHOW_YEAR |
                        DateUtils.FORMAT_SHOW_DATE |
                        DateUtils.FORMAT_SHOW_TIME;
        flag |= mIs24HourView ? DateUtils.FORMAT_24HOUR : DateUtils.FORMAT_24HOUR;
        setTitle(DateUtils.formatDateTime(this.getContext(), date, flag));
    }

    //点击事件
    public void onClick(DialogInterface arg0, int arg1) {
        if (mOnDateTimeSetListener != null) {
            mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis());
        }
    }

}
