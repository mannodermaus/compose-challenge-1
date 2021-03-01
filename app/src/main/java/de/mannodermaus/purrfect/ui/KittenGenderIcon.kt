package de.mannodermaus.purrfect.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.mannodermaus.purrfect.R
import de.mannodermaus.purrfect.model.KittenGender
import de.mannodermaus.purrfect.ui.theme.typography

@Composable
fun KittenName(name: String, gender: KittenGender) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            style = typography.caption
        )
        KittenGenderIcon(
            modifier = Modifier
                .size(24.dp)
                .padding(start = 4.dp),
            gender = gender
        )
    }
}

@Composable
private fun KittenGenderIcon(
    modifier: Modifier = Modifier,
    gender: KittenGender
) {
    Image(
        modifier = modifier,
        contentDescription = gender.name,
        colorFilter = ColorFilter.tint(
            when (gender) {
                KittenGender.Male -> Color(0xff408dc8)
                KittenGender.Female -> Color(0xffcc687d)
            }
        ),
        painter = painterResource(
            when (gender) {
                KittenGender.Male -> R.drawable.gender_male
                KittenGender.Female -> R.drawable.gender_female
            }
        )
    )
}
