package dev.alraj.singlelayoutexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _liveData: MutableLiveData<List<Int>> = MutableLiveData(listOf())
    val liveData: LiveData<List<Int>> = _liveData

    fun getList() {
        viewModelScope.launch {
            delay(15)
            _liveData.value = (listOf(1, 2))
        }
    }
}