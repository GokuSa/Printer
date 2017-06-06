package com.shine.printer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.shine.printer_module.PrintContent2;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_print).setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
       final PrintContent2 printContent2 = new PrintContent2("12", "12", "12", "12", "12", "12");
        Observable.just(v).throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .map(new Function<View, Integer>() {
            @Override
            public Integer apply(@NonNull View view) throws Exception {

                return AppEntrance.getInstance().getmPrinterHelper().print(printContent2);
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "读卡返回结果" + integer);
            }
        });
    }
}
