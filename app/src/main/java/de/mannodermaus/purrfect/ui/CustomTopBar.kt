package de.mannodermaus.purrfect.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import de.mannodermaus.purrfect.R

@Composable
fun CustomTopBar(navController: NavHostController) {
    val currentScreen by navController.currentBackStackEntryAsState()
    val currentRoute = currentScreen?.arguments?.getString(KEY_ROUTE)
    val isHome = currentRoute == "list"

    Surface(
        color = MaterialTheme.colors.primarySurface,
        contentColor = contentColorFor(MaterialTheme.colors.primarySurface),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            Image(
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.purrfect_logo),
                contentDescription = "Logo"
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        if (isHome) {
                            // todo
                        } else {
                            navController.popBackStack()
                        }
                    }
                    .padding(16.dp),
                alignment = Alignment.CenterStart,
                imageVector = if (isHome) {
                    Icons.Default.Menu
                } else {
                    Icons.Default.ArrowBack
                },
                contentDescription = "Menu",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
            )
        }
    }
}
