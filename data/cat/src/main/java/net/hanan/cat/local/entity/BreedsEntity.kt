package net.hanan.cat.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "breed",
    foreignKeys = [
        ForeignKey(
            entity = CatInfoEntity::class,
            parentColumns = ["catId"],
            childColumns = ["catId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class BreedsEntity(
    @PrimaryKey(autoGenerate = false)
    val catId: String,
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    val lifeSpan: String
)