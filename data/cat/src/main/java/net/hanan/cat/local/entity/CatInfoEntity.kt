package net.hanan.cat.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_info")
data class CatInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val catId: String,
    val url: String
)