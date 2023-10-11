package com.example.randogpicrxjava.util

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxJavaFactory()
{


        fun getIo() = Schedulers.io()
        fun getUi() = AndroidSchedulers.mainThread()



}