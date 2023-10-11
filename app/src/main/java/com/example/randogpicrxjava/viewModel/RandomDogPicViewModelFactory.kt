package com.example.randogpicrxjava.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randogpicrxjava.data.repository.RandomDogPicRepository

class RandomDogPicViewModelFactory (private val repository: RandomDogPicRepository)

    : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RandomDogPicViewModel(repository) as T
        }

}






