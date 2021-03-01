package de.mannodermaus.purrfect

import de.mannodermaus.purrfect.model.Kitten
import de.mannodermaus.purrfect.model.KittenGender
import de.mannodermaus.purrfect.model.Owner
import kotlinx.coroutines.delay

class KittenRepository {

    private val description = """
        Admiration we surrounded possession frequently he. Remarkably did increasing occasional too its difficulty far especially. Known tiled but sorry joy balls. Bed sudden manner indeed fat now feebly. Face do with in need of wife paid that be. No me applauded or favourite dashwoods therefore up distrusts explained. 

        Mr oh winding it enjoyed by between. The servants securing material goodness her. Saw principles themselves ten are possession. So endeavor to continue cheerful doubtful we to. Turned advice the set vanity why mutual. Reasonably if conviction on be unsatiable discretion apartments delightful. Are melancholy appearance stimulated occasional entreaties end. Shy ham had esteem happen active county. Winding morning am shyness evident to. Garrets because elderly new manners however one village she. 

        Delightful unreserved impossible few estimating men favourable see entreaties. She propriety immediate was improving. He or entrance humoured likewise moderate. Much nor game son say feel. Fat make met can must form into gate. Me we offending prevailed discovery. 

        Now for manners use has company believe parlors. Least nor party who wrote while did. Excuse formed as is agreed admire so on result parish. Put use set uncommonly announcing and travelling. Allowance sweetness direction to as necessary. Principle oh explained excellent do my suspected conveying in. Excellent you did therefore perfectly supposing described. 
    """.trimIndent()

    private val hardCodedKittens = listOf(
        Kitten(
            id = "1",
            name = "Tabby",
            description = description,
            age = "3 months",
            distance = 500f,
            gender = KittenGender.Female,
            imageRes = R.drawable.kitten1,
            owner = Owner(
                name = "Marlene",
                imageRes = R.drawable.owner1
            )
        ),
        Kitten(
            id = "2",
            name = "Cesar",
            description = description,
            age = "5 months",
            distance = 800f,
            gender = KittenGender.Male,
            imageRes = R.drawable.kitten2,
            owner = Owner(
                name = "Pablo",
                imageRes = R.drawable.owner2
            )
        ),
        Kitten(
            id = "3",
            name = "Abel & Cain",
            description = description,
            age = "6 months",
            distance = 1400f,
            gender = KittenGender.Male,
            imageRes = R.drawable.kitten3,
            owner = Owner(
                name = "John",
                imageRes = R.drawable.owner3
            )
        ),
        Kitten(
            id = "4",
            name = "Stubbles",
            description = description,
            age = "2 months",
            distance = 2000f,
            gender = KittenGender.Female,
            imageRes = R.drawable.kitten4,
            owner = Owner(
                name = "Michael",
                imageRes = R.drawable.owner4
            )
        ),
        Kitten(
            id = "5",
            name = "Minka",
            description = description,
            age = "12 months",
            distance = 3200f,
            gender = KittenGender.Female,
            imageRes = R.drawable.kitten5,
            owner = Owner(
                name = "Alice",
                imageRes = R.drawable.owner5
            )
        ),
    )

    suspend fun listKittens(): List<Kitten> {
        delay(1000) // Simulate network delay
        return hardCodedKittens
    }

    suspend fun getKitten(id: String): Kitten? {
        delay(300) // Simulate network delay
        return hardCodedKittens.firstOrNull { it.id == id }
    }
}
