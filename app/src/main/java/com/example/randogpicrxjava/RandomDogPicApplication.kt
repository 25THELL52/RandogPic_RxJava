package com.example.randogpicrxjava

import android.app.Application
import com.example.randogpicrxjava.data.network.RandomDogPicApi
import com.example.randogpicrxjava.data.repository.RandomDogPicRepository
import com.example.randogpicrxjava.data.repository.RandomDogPicRepositoryImpl

class RandomDogPicApplication : Application() {


    val repository : RandomDogPicRepository
        by lazy {RandomDogPicRepositoryImpl(RandomDogPicApi.create())}

}