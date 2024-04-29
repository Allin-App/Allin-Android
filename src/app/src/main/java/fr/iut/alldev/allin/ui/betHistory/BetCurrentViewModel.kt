package fr.iut.alldev.allin.ui.betHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BetCurrentViewModel @Inject constructor(
    private val betRepository: BetRepository,
    private val keystoreManager: AllInKeystoreManager
) : ViewModel() {
    private val _bets: MutableStateFlow<List<BetDetail>> by lazy {
        MutableStateFlow(emptyList())
    }

    val bets: StateFlow<List<BetDetail>> by lazy {
        _bets.asStateFlow()
            .filterNotNull()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000L),
                emptyList()
            )
    }

    init {
        viewModelScope.launch {
            _bets.emit(
                betRepository.getCurrentBets(keystoreManager.getTokenOrEmpty())
            )
        }
    }
}