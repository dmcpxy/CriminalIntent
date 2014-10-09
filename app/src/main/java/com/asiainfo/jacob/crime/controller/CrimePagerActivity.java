package com.asiainfo.jacob.crime.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.asiainfo.jacob.crime.R;
import com.asiainfo.jacob.crime.model.Crime;
import com.asiainfo.jacob.crime.model.CrimeLab;

import java.util.List;
import java.util.UUID;

/**
 * Created by jacob on 14-10-9.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private List<Crime> crimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);

        setContentView(viewPager);

        crimeList = CrimeLab.getCrimeLab(this).getCrimeList();

        FragmentManager fragmentManager = getSupportFragmentManager();

        //set viewpager adapter
        //Creating the FragmentStatePagerAdapter requires the FragmentManager
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = crimeList.get(i);
                //if (null != crime.getmTitle()) setTitle(crime.getmTitle());

                return CrimeFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return crimeList.size();
            }
        });

        UUID uuid = (UUID) getIntent().getSerializableExtra(CrimeFragment.CRIME_ID);
        Crime currentCrime = CrimeLab.getCrimeLab(this).getCrime(uuid);
        /* set title for activity */
        this.setTitle(currentCrime.getmTitle());


        /*set current item to the target from list click.*/
        for (int i = 0; i< crimeList.size(); i++) {
            if (crimeList.get(i).getmId().equals(uuid)) {
                viewPager.setCurrentItem(i);
                break;
            }
        } // end of for loop

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                Crime crime = crimeList.get(i);
                if (null != crime) {
                    setTitle(crime.getmTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
