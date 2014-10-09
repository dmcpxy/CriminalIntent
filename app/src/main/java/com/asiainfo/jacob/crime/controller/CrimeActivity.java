package com.asiainfo.jacob.crime.controller;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by jacob on 14-10-8.
 */
public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID crime_id = (UUID) getIntent().getSerializableExtra(CrimeFragment.CRIME_ID);

        return CrimeFragment.newInstance(crime_id);
    }
}
