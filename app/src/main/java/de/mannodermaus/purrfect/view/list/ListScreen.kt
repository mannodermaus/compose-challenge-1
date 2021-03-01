package de.mannodermaus.purrfect.view.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import de.mannodermaus.purrfect.model.Kitten
import de.mannodermaus.purrfect.ui.KittenName
import de.mannodermaus.purrfect.ui.LoadingUI
import de.mannodermaus.purrfect.ui.theme.typography
import kotlin.math.roundToInt

@Composable
fun ListScreen(
    navController: NavHostController,
    model: ListViewModel = viewModel()
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
                is ListViewModel.State.Loading -> {
                    LoadingUI()
                }
                is ListViewModel.State.Loaded -> {
                    KittenList(
                        modifier = Modifier.padding(8.dp),
                        kittens = state.kittens,
                        onKittenClicked = { kitten ->
                            navController.navigate("detail/${kitten.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun KittenList(
    modifier: Modifier = Modifier,
    kittens: List<Kitten>,
    onKittenClicked: (Kitten) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.verticalScroll(scrollState)) {
        kittens.forEach { kitten ->
            KittenListItem(kitten, onClick = { onKittenClicked(kitten) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun KittenListItem(kitten: Kitten, onClick: () -> Unit) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = kitten.imageRes),
                contentDescription = kitten.name
            )

            // Kitten facts
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .heightIn(min = 96.dp),
                verticalArrangement = Arrangement.Center
            ) {
                KittenName(name = kitten.name, gender = kitten.gender)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .aspectRatio(1f),
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null
                    )
                    Text(
                        text = "${formatDistance(kitten.distance)} away",
                        style = typography.body1
                    )
                }

                Text(
                    text = kitten.age,
                    style = typography.body1
                )
            }
        }
    }
}

private fun formatDistance(distance: Float) = if (distance < 1000) {
    "${distance.roundToInt()} m"
} else {
    "${String.format("%.1f", distance / 1000f)} km"
}
