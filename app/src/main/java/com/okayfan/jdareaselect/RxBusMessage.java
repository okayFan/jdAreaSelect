package com.okayfan.jdareaselect;

import android.os.Bundle;

/**
 * Created by fyx on 2018/10/15.
 */

public class RxBusMessage {
    public static final int GET_CITY = 1023;
    public static final int GET_AREA = 1024;
    public static final int CLEAR_CITY_SELECT = 1025;

    public int what;
    public String msg;
    public Object obj;
    public Bundle bundle;

    public RxBusMessage(int what) {
        this.what = what;
    }

    public RxBusMessage(int what, String msg) {
        this.what = what;
        this.msg = msg;
    }

    public RxBusMessage(int what, String msg, Object obj) {
        this.what = what;
        this.msg = msg;
        this.obj = obj;
    }

    public RxBusMessage(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public RxBusMessage(int what, Bundle bundle) {
        this.what = what;
        this.bundle = bundle;
    }
}
