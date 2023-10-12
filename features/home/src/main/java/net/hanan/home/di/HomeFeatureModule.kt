package net.hanan.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import net.hanan.core.domain.repository.CatRepository
import net.hanan.home.domin.usecases.GetCatsUseCase
import net.hanan.home.domin.usecases.HomeUseCases

@Module
@InstallIn(ViewModelComponent::class)
object HomeFeatureModule {
    @ViewModelScoped
    @Provides
    fun provideHomeUseCases(
        repository: CatRepository
    ): HomeUseCases = HomeUseCases(
        getCatsUseCase = GetCatsUseCase(repository)
    )
}