package com.example.disastermanagmentapp.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.disastermanagmentapp.Interface.Disaster
import com.example.disastermanagmentapp.Rest.Event
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DisasterViewModel(
    private val repo: Repo
) : ViewModel() {

    private val _droughtEvents = MutableLiveData<List<Event>>()
    val droughtEvents: LiveData<List<Event>> = _droughtEvents

    private val _landslideEvents = MutableLiveData<List<Event>>()
    val landslideEvents: LiveData<List<Event>> = _landslideEvents

    private val _snowEvents = MutableLiveData<List<Event>>()
    val snowEvents: LiveData<List<Event>> = _snowEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        fetchDisasterData()
    }

    private fun fetchDisasterData() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val droughtData = repo.getDroughtEvents()
                val landslideData = repo.getLandslideEvents()
                val snowData = repo.getSnowEvents()

                _droughtEvents.value = droughtData
                _landslideEvents.value = landslideData
                _snowEvents.value = snowData
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}