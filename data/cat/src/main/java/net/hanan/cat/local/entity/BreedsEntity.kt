package net.hanan.cat.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class BreedsEntity(
    @PrimaryKey(autoGenerate = false)
    val breedId: String,
    val name: String,
    val origin: String,
    val description: String,
    val lifeSpan: String
)