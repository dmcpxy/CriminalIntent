package com.asiainfo.jacob.crime.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jacob on 14-10-4.
 */
public class Crime implements Serializable {
    private final static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    private UUID mId;
    private String mTitle;
    private Date lastModifiedDate;
    private boolean mSolved;


    public Crime() {
        this.mId = UUID.randomUUID();
        lastModifiedDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getLastModifiedDateStr() {
        return dateformat.format(lastModifiedDate);
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
