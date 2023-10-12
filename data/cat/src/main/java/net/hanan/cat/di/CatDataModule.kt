package net.hanan.cat.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hanan.cat.local.CatDatabase
import net.hanan.cat.remote.CatsApi
import net.hanan.cat.repository.CatRepositoryImpl
import net.hanan.core.domain.repository.CatRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatDataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    @Provides
    @Singleton
    fun provideCatApi(client: OkHttpClient): CatsApi = Retrofit.Builder()
        .baseUrl(CatsApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create()

    @Provides
    @Singleton
    fun provideCatDatabase(
        app: Application
    ): CatDatabase = Room.databaseBuilder(
        app,
        CatDatabase::class.java,
        "cat_db"
    ).build()

    @Provides
    @Singleton
    fun provideCatRepository(
        api: CatsApi,
        db: CatDatabase
    ): CatRepository = CatRepositoryImpl(
        api = api,
        db = db
    )
}