package net.hanan.cat.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.hanan.cat.local.entity.BreedsEntity
import net.hanan.cat.local.entity.CatInfoEntity
import net.hanan.cat.local.entity.relations.CatBreedCrossRef

@Database(
    entities = [
        CatInfoEntity::class,
        BreedsEntity::class,
        CatBreedCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CatDatabase : RoomDatabase() {
    abstract val dao: CatDao
}