package com.example.disastermanagmentapp.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DisasterViewModelFactory(
    val repo: Repo
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DisasterViewModel(repo) as T
    }

}