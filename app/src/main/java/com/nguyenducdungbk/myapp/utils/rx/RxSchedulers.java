package com.nguyenducdungbk.myapp.utils.rx;


import dagger.Module;
import io.reactivex.Scheduler;

@Module
public interface RxSchedulers {

    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();


}
