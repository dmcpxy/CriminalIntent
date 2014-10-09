package com.asiainfo.jacob.crime.controller;

import android.support.v4.app.Fragment;

/**
 * 主要是为了创建CrimeListFragment对象而存在
 * Created by jacob on 14-10-8.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
