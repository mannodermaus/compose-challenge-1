package de.mannodermaus.purrfect.model

import androidx.annotation.DrawableRes

data class Kitten(
    val id: String,
    val name: String,
    val description: String,
    val age: String,
    val distance: Float,
    val gender: KittenGender,
    @DrawableRes val imageRes: Int,
    val owner: Owner
)

data class Owner(
    val name: String,
    @DrawableRes val imageRes: Int
)

enum class KittenGender {
    Male,
    Female
}
