package com.asiainfo.jacob.crime.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.asiainfo.jacob.crime.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jacob on 14-10-9.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.asiainfo.jdcbo.crime.lastModifiedDate";
    private Date lastModifiedDate;


    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        lastModifiedDate = (Date) getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastModifiedDate);

        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = new DatePicker(getActivity());
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                lastModifiedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();

                getArguments().putSerializable(EXTRA_DATE, lastModifiedDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(datePicker)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        returnResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    private void returnResult(int resultCode) {
        if (null == getTargetFragment()) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, lastModifiedDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
