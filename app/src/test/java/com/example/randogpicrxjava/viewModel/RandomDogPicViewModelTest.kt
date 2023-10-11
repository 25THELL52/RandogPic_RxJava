package com.example.randogpicrxjava.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.randogpicrxjava.data.repository.RandomDogPicRepository
import com.example.randogpicrxjava.model.Dog
import com.example.randogpicrxjava.util.RxJavaFactory
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

import org.mockito.kotlin.times
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)


class RandomDogPicViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var rxJavaFactory: RxJavaFactory


    private lateinit var testScheduler: TestScheduler

    @Mock
    lateinit var randomDogPicRepository: RandomDogPicRepository
    lateinit var randomDogPicViewModel: RandomDogPicViewModel

    @Mock
    lateinit var dog: Dog

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<Boolean>

    @Mock
    lateinit var dogObserver: Observer<Dog>


    @Before
    fun setUp() {

        randomDogPicViewModel = RandomDogPicViewModel(randomDogPicRepository)
        randomDogPicViewModel.rxJavaFactory = rxJavaFactory
        randomDogPicViewModel.let {
            it.getDog().observeForever(dogObserver)
            it.getLoading().observeForever(loadingObserver)
            it.getError().observeForever(errorObserver)

            testScheduler = TestScheduler()
            whenever(rxJavaFactory.getIo()).thenReturn(testScheduler)
            whenever(rxJavaFactory.getUi()).thenReturn(testScheduler)


        }


    }


    private fun setUpRepositoryWithSuccess() {

        val singleObservable = Single.just(dog)
        whenever(randomDogPicRepository.getDog()).thenReturn(singleObservable)

    }

    private fun setUpRepositoryWithFailure() {

        val singleObservable: Single<Dog> = Single.error(Exception())
        whenever(randomDogPicRepository.getDog()).thenReturn(singleObservable)


    }


    @Test
    fun whenInitialize_fetchIsCalled() {

        setUpRepositoryWithSuccess()
        val spyViewModel = spy(randomDogPicViewModel)
        spyViewModel.initialize()
        verify(spyViewModel).fetch()

    }


    @Test
    fun whenFetch_initializeDataIsCalled() {

        setUpRepositoryWithSuccess()
        val spyViewModel = spy(randomDogPicViewModel)
        spyViewModel.fetch()
        verify(spyViewModel).initializeLiveData()
    }


    @Test
    fun whenInitializeLiveData_loadingIsTrue() {

        randomDogPicViewModel.initializeLiveData()
        verify(loadingObserver).onChanged(true)
    }

    @Test
    fun whenInitializeLiveData_ErrorIsFalse() {

        randomDogPicViewModel.initializeLiveData()
        verify(errorObserver).onChanged(false)
    }



    @Test
    fun whenFetch_getDogIsCalled() {
        setUpRepositoryWithSuccess()
        randomDogPicViewModel.fetch()
        verify(randomDogPicRepository).getDog()
    }


    @Test
    fun whenRepositoryReturnsSuccess_loadingIsFalse() {

        setUpRepositoryWithSuccess()
        randomDogPicViewModel.fetch()
        testScheduler.triggerActions()
        //Log.d("loading is: ", "${randomDogPicViewModel.getLoading().value}")
        verify(loadingObserver).onChanged(false)

    }

    @Test
    fun whenRepositoryReturnsSuccess_errorIsFalse() {
        setUpRepositoryWithSuccess()
        randomDogPicViewModel.fetch()
        testScheduler.triggerActions()

        verify(errorObserver, times(2)).onChanged(false)
    }

    @Test
    fun whenRepositoryReturnsSuccess_DogValueIsChangedTodog() {
        setUpRepositoryWithSuccess()
        randomDogPicViewModel.fetch()
        testScheduler.triggerActions()

        verify(dogObserver).onChanged(dog)
    }

    @Test
    fun whenRepositoryReturnsError_loadingIsFalse() {
        setUpRepositoryWithFailure()
        randomDogPicViewModel.fetch()
        testScheduler.triggerActions()

        verify(loadingObserver).onChanged(false)
    }

    @Test
    fun whenRepositoryReturnsError_errorIsTrue() {
        setUpRepositoryWithFailure()
        randomDogPicViewModel.fetch()
        testScheduler.triggerActions()

        verify(errorObserver).onChanged(true)
    }


}