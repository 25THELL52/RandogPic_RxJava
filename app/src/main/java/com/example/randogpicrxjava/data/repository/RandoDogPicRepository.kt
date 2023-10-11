package com.example.randogpicrxjava.data.repository

import com.example.randogpicrxjava.model.Dog
import io.reactivex.Single

interface RandomDogPicRepository {

    fun getDog(): Single<Dog>

}