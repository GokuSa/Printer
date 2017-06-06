package com.shine.printer;

import android.app.Application;

import com.shine.printer_module.PrinterHelper;

/**
 * author:
 * 时间:2017/5/17
 * qq:1220289215
 * 类描述：
 */

public class AppEntrance extends Application {
    private  PrinterHelper mPrinterHelper;
    private static AppEntrance sAppEntrance;
    @Override
    public void onCreate() {
        super.onCreate();
        sAppEntrance=this;
        mPrinterHelper=new PrinterHelper(this);
        mPrinterHelper.init();
    }

    public static AppEntrance getInstance() {
        return sAppEntrance;
    }

    public  PrinterHelper getmPrinterHelper() {
        return mPrinterHelper;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mPrinterHelper != null) {
            mPrinterHelper.exit();
        }
    }
}
