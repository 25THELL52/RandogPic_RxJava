package com.example.randogpicrxjava.data.repository

import com.example.randogpicrxjava.data.network.RandomDogPicApi
import com.example.randogpicrxjava.model.Dog
import io.reactivex.Single

open class RandomDogPicRepositoryImpl(private val api: RandomDogPicApi) : RandomDogPicRepository {


    override fun getDog(): Single<Dog> {


//        Log.d("RandomDogPicRepoImpl", "sending a request to the url Thread :${Thread.currentThread().name}")
        return api.getDog()


    }


        }



