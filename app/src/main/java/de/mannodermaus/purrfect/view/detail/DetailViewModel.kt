package de.mannodermaus.purrfect.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.mannodermaus.purrfect.DI
import de.mannodermaus.purrfect.model.Kitten
import de.mannodermaus.purrfect.KittenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    kittenId: String,
    private val repository: KittenRepository = DI.kittenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Loading)

    val uiState: StateFlow<State> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val kitten = repository.getKitten(kittenId)
            _uiState.value = if (kitten != null) {
                State.Loaded(kitten)
            } else {
                State.Error("cannot find kitten, my god")
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val kitten: Kitten) : State()
        data class Error(val message: String) : State()
    }
}
