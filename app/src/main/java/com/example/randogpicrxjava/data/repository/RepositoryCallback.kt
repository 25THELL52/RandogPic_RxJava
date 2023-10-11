package com.example.randogpicrxjava.data.repository

import com.example.randogpicrxjava.model.Dog


interface RepositoryCallback  {
     fun onSuccess(dog : Dog) {
// TODO
    }
     fun onError(e: String) {
// TODO
    }
}
