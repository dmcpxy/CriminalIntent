package com.asiainfo.jacob.crime.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * model object
 * data source holder.
 * Created by jacob on 14-10-8.
 */
public class CrimeLab {
    private List<Crime> crimeList;
    private static CrimeLab crimeLab;

    private Context appContext;

    public CrimeLab(Context appContext) {
        this.appContext = appContext;

        crimeList = new ArrayList<Crime>();

        initTestData();
    }

    private void initTestData() {
        for (int i = 0; i < 50; i++) {
            Crime crime = new Crime();
            crime.setmTitle("陋习 # " + i);
            crime.setmSolved(i % 2 == 0);
            crimeList.add(crime);
        }
    }

    public static CrimeLab getCrimeLab(Context context) {
        if (null == crimeLab) {
            crimeLab = new CrimeLab(context.getApplicationContext());
        }

        return crimeLab;
    }

    public Crime getCrime(UUID uid) {
        for (Crime crime : crimeList) {
            if (crime.getmId().equals(uid)) return crime;
        }

        return null;
    }

    public List<Crime> getCrimeList() {
        return crimeList;
    }
}
