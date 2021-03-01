package de.mannodermaus.purrfect.view.detail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import de.mannodermaus.purrfect.model.Kitten
import de.mannodermaus.purrfect.model.Owner
import de.mannodermaus.purrfect.ui.KittenName
import de.mannodermaus.purrfect.ui.LoadingUI
import de.mannodermaus.purrfect.ui.theme.typography

@Composable
private fun detailViewModel(kittenId: String) =
    viewModel<DetailViewModel>(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(kittenId) as T
        }
    })

@Composable
fun DetailScreen(
    kittenId: String,
    model: DetailViewModel = detailViewModel(kittenId)
) {
    val stateChanges = model.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Crossfade(
            modifier = Modifier.fillMaxSize(),
            targetState = stateChanges.value
        ) { state ->
            when (state) {
                is DetailViewModel.State.Loading -> {
                    LoadingUI()
                }
                is DetailViewModel.State.Loaded -> {
                    KittenDetailUI(state.kitten)
                }
                is DetailViewModel.State.Error -> {

                }
            }
        }
    }
}

@Composable
private fun KittenDetailUI(kitten: Kitten) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Image(
            modifier = Modifier.height(320.dp),
            painter = painterResource(id = kitten.imageRes),
            contentScale = ContentScale.Crop,
            contentDescription = kitten.name
        )

        Column(modifier = Modifier.padding(16.dp)) {
            // Kitten details
            KittenName(name = kitten.name, gender = kitten.gender)
            Text(text = kitten.age)

            // Owner details
            OwnerDetails(kitten.owner)

            // Kitten description
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "About ${kitten.name}",
                style = typography.caption
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = kitten.description
            )
        }

        // Actions
        var isFavorite by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.height(48.dp),
                onClick = {}
            ) {
                Text("Become My Friend")
            }
            Button(
                modifier = Modifier.height(48.dp),
                onClick = { isFavorite = !isFavorite }
            ) {
                Image(
                    imageVector = if (isFavorite) {
                        Icons.Filled.Star
                    } else {
                        Icons.Outlined.StarOutline
                    },
                    contentDescription = "Favorite",
                    colorFilter = tint(MaterialTheme.colors.onPrimary)
                )
            }
        }
    }
}

@Composable
private fun OwnerDetails(owner: Owner) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = owner.imageRes),
                contentDescription = owner.name
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = owner.name,
                    style = typography.caption
                )
                Text(
                    text = "Berlin, Germany"
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.size(60.dp),
                    onClick = { /*TODO*/ },
                    colors = buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    )
                ) {
                    Image(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "Call ${owner.name}",
                        colorFilter = tint(MaterialTheme.colors.onSecondary)
                    )
                }
            }
        }
    }
}