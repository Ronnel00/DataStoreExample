package edu.ucne.datastoreexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.datastoreexample.data.AppStatsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppStatsViewModel @Inject constructor(
    private val repository: AppStatsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AppStatsUiState())
    val state: StateFlow<AppStatsUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.appStatsFlow.collect { stats ->
                _state.update {
                    it.copy(
                        openCount = stats.openCount,
                        lastOpenedTimestamp = stats.lastOpenedTimestamp
                    )
                }
            }
        }
        recordOpen()
    }

    private fun recordOpen() {
        viewModelScope.launch {
            repository.recordAppOpen(System.currentTimeMillis())
        }
    }
}