package net.hanan.details.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import net.hanan.core.domain.repository.CatRepository
import net.hanan.details.domain.usecases.CatDetailsUseCases
import net.hanan.details.domain.usecases.GetCatDetailsUseCase

@Module
@InstallIn(ViewModelComponent::class)
class CatDetailsFeatureModule {
    @ViewModelScoped
    @Provides
    fun provideUseCases(
        repository: CatRepository
    ): CatDetailsUseCases = CatDetailsUseCases(
        getCatDetailsUseCase = GetCatDetailsUseCase(repository)
    )
}