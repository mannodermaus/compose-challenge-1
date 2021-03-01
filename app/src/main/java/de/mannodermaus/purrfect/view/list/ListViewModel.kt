package de.mannodermaus.purrfect.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.mannodermaus.purrfect.DI
import de.mannodermaus.purrfect.model.Kitten
import de.mannodermaus.purrfect.KittenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: KittenRepository = DI.kittenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Loading)

    val uiState: StateFlow<State> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val kittens = repository.listKittens()
            _uiState.value = State.Loaded(kittens)
        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val kittens: List<Kitten>) : State()
    }
}
