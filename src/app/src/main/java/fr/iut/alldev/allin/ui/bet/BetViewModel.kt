package fr.iut.alldev.allin.ui.bet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class BetViewModel @Inject constructor(
    private val keystoreManager: AllInKeystoreManager,
    private val betRepository: BetRepository
) : ViewModel() {

    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(State.Loading) }
    val state: StateFlow<State> by lazy { _state.asStateFlow() }

    private val _filters: MutableStateFlow<List<BetFilter>> by lazy { MutableStateFlow(emptyList()) }
    val filters get() = _filters.asStateFlow()

    private val _refreshing by lazy { MutableStateFlow(false) }
    val refreshing by lazy { _refreshing.asStateFlow() }

    init {
        viewModelScope.launch {
            refreshBets()
            filters
                .debounce(1.seconds)
                .collect {
                    refreshBets()
                }
        }
    }

    fun toggleFilter(filter: BetFilter) {
        viewModelScope.launch {
            _filters.emit(
                if (_filters.value.contains(filter)) {
                    _filters.value - filter
                } else {
                    _filters.value + filter
                }
            )
        }
    }

    private suspend fun refreshBets() {
        try {
            val token = keystoreManager.getTokenOrEmpty()
            _state.emit(
                State.Loaded(
                    bets = betRepository.getAllBets(
                        token = token,
                        filters = filters.value
                    ),
                    popularBet = try {
                        betRepository.getPopularBet(token)
                    } catch (e: Exception) {
                        null
                    }
                )
            )
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _refreshing.emit(true)
            refreshBets()
            _refreshing.emit(false)
        }
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(val bets: List<Bet>, val popularBet: Bet?) : State
    }

}