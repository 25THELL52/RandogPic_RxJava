package com.example.randogpicrxjava.data.repository

import com.example.randogpicrxjava.data.network.RandomDogPicApi
import com.example.randogpicrxjava.model.Dog
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import retrofit2.Call

@RunWith(MockitoJUnitRunner::class)
class RandomDogPicRepositoryImplTest {



    private lateinit var api: RandomDogPicApi
    private lateinit var randomDogPicRepositoryImpl: RandomDogPicRepositoryImpl
    private lateinit var dog: Dog

    @Before
    fun setUp() {
        api = mock()
        randomDogPicRepositoryImpl = RandomDogPicRepositoryImpl(api)
        dog = Dog("url", "success")
    }

    @Test
    fun getDogCallsApiGetDog() {

        randomDogPicRepositoryImpl.getDog()
        verify(api).getDog()
    }

    /*
    @Test
    fun whenResponseIsNotNullAndSuccessfulAndBodyNotNull_OnSuccessIsCalledOnCallback() {

        whenever(api.getDog()).thenReturn(call)

        doAnswer {

            val callback: Callback<Dog> = it.getArgument(0)
            callback.onResponse(call, Response.success(dog))

        }.whenever(call).enqueue(any())

        randomDogPicRepositoryImpl.getDog(repositoryCallback)
        verify(repositoryCallback).onSuccess(dog)


    }

    @Test
    fun whenResponseIsNotNullAndSuccessfulAndBodyNull_OnErrorIsCalledOnCallback() {

        whenever(api.getDog()).thenReturn(call)

        doAnswer {

            val callback: Callback<Dog> = it.getArgument(0)
            callback.onResponse(call, Response.success(null))

        }.whenever(call).enqueue(any())

        randomDogPicRepositoryImpl.getDog(repositoryCallback)
        verify(repositoryCallback).onError("Couldn't get dog object")


    }

    @Test
    fun whenResponseHasFailed_OnErrorIsCalledOnCallback() {

        whenever(api.getDog()).thenReturn(call)
        doAnswer {

            val callback: Callback<Dog> = it.getArgument(0)
            callback.onFailure(call, Throwable())

        }.whenever(call).enqueue(any())

        randomDogPicRepositoryImpl.getDog(repositoryCallback)
        verify(repositoryCallback).onError("Couldn't get dog object")


    }

     */
}