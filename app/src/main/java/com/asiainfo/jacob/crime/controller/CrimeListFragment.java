package com.asiainfo.jacob.crime.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.asiainfo.jacob.crime.R;
import com.asiainfo.jacob.crime.model.Crime;
import com.asiainfo.jacob.crime.model.CrimeLab;

import java.util.List;


/**
 * Created by jacob on 14-10-8.
 */
public class CrimeListFragment extends ListFragment {
    private final static String TAG = "CrimeListFragment";
    private List<Crime> crimeList;
    private final static int REQUEST_CRIME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);

        crimeList = CrimeLab.getCrimeLab(getActivity()).getCrimeList();

        CrimeAdapter crimeAdapter = new CrimeAdapter(crimeList);

        setListAdapter(crimeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = (Crime) getListAdapter().getItem(position);

        /*使用CrimeActivity 创建子视图*/
        //Intent intent = new Intent(getActivity(), CrimeActivity.class);
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.CRIME_ID, crime.getmId());

        //startActivity(intent);
        startActivityForResult(intent, REQUEST_CRIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CRIME) {
            //handle result here...
        }
    }

    /*初始化列表*/
    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(List<Crime> crimeList) {
            super(getActivity(), 0, crimeList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            Crime crime = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);

            titleTextView.setText(crime.getmTitle());
            dateTextView.setText(crime.getLastModifiedDateStr());
            solvedCheckBox.setChecked(crime.ismSolved());

            return convertView;
        }
    }
}
