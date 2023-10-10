package fr.iut.alldev.allin.ui.bet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class BetViewModel @Inject constructor(
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    private fun refreshData(){
        Thread.sleep(1000)
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            withContext(Dispatchers.IO) {
                refreshData()
            }
            _isRefreshing.emit(false)
        }
    }

}