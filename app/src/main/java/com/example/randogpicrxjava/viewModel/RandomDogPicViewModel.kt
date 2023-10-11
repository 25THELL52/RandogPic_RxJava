package com.example.randogpicrxjava.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randogpicrxjava.data.repository.RandomDogPicRepository
import com.example.randogpicrxjava.model.Dog
import com.example.randogpicrxjava.util.EspressoIdlingResource
import com.example.randogpicrxjava.util.RxJavaFactory
import io.reactivex.disposables.CompositeDisposable


open class RandomDogPicViewModel(private val repository: RandomDogPicRepository) : ViewModel() {


    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val dog = MutableLiveData<Dog>()
    private val disposable = CompositeDisposable()
    var rxJavaFactory = RxJavaFactory()

    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getError(): LiveData<Boolean> = errorLiveData
    fun getDog(): LiveData<Dog> = dog


    fun initialize() {

        fetch()

    }



    fun fetch() {
        EspressoIdlingResource.increment()
        Log.d(
            "MainActivity", "isIdleNow() ${EspressoIdlingResource.getIdlingResource()?.isIdleNow}"
        )


       initializeLiveData()
        Log.d(
            "MainActivity", "error changed"
        )
        disposable.add(
            repository.getDog()
                .subscribeOn(rxJavaFactory.getIo())
                .observeOn(rxJavaFactory.getUi())
                .subscribe({ onSuccess(it) },
                    {
                        onError()
                    })
        )


    }

    private fun onError() {
        loadingLiveData.postValue(false)
        errorLiveData.postValue(true)
        EspressoIdlingResource.decrement()
        Log.d(
            "MainActivity", "isIdleNow() ${EspressoIdlingResource.getIdlingResource()?.isIdleNow}"
        )
    }

    private fun onSuccess(dog: Dog) {

        loadingLiveData.postValue(false)
        errorLiveData.postValue(false)
        this@RandomDogPicViewModel.dog.postValue(dog)
        Log.d(
            "MainActivity", "error changed"
        )
    }

    fun initializeLiveData() {

        loadingLiveData.postValue(true)
        errorLiveData.postValue(false)



    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}