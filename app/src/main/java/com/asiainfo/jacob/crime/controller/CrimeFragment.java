package com.asiainfo.jacob.crime.controller;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.asiainfo.jacob.crime.R;
import com.asiainfo.jacob.crime.model.Crime;
import com.asiainfo.jacob.crime.model.CrimeLab;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jacob on 14-10-4.
 */
public class CrimeFragment extends Fragment {
    public static final String CRIME_ID = "crime_id";
    private EditText editText;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    private Crime crime;
    private final static String DIALOG_DATE = "date";
    private final static int REQUEST_DATE = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*获取从Activity传入的参数 crime_id */
        UUID crimeId = (UUID) getArguments().getSerializable(CRIME_ID);

        /*得到相应的对象*/
        crime = CrimeLab.getCrimeLab(getActivity()).getCrime(crimeId);
    }

    /**
     * 创建 CrimeFragment 对象的方法
     * 主要是为了封装参数
     * @param crimeId
     * @return
     */
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CRIME_ID, crimeId);

        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(bundle);

        return crimeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*根据定义的界面，创建视图*/
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        editText = (EditText) view.findViewById(R.id.crime_title);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String newValue = charSequence.toString();
                //data changed.
                if (!crime.getmTitle().equals(newValue)) {
                    crime.setmTitle(charSequence.toString());

                    crime.setLastModifiedDate(new Date());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        editText.setText(crime.getmTitle());


        mDateButton = (Button) view.findViewById(R.id.crime_date);
        mDateButton.setText(crime.getLastModifiedDateStr());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                //DatePickerFragment datePickerFragment = new DatePickerFragment();  //first version
                //second version.
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(crime.getLastModifiedDate());
                datePickerFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);

                datePickerFragment.show(fragmentManager, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(crime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                crime.setmSolved(b);
            }
        });

        return view;
    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            crime.setLastModifiedDate(date);

            mDateButton.setText(crime.getLastModifiedDateStr());
        }
    }

}
