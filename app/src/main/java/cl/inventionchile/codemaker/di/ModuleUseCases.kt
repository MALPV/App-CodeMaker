package cl.inventionchile.codemaker.di

import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.remote.AppServices
import cl.inventionchile.codemaker.data.usecases.AuthenticateUseCase
import cl.inventionchile.codemaker.data.usecases.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ModuleUseCases {

    @ViewModelScoped
    @Provides
    fun provideAuthenticateUseCase(
        appServices: AppServices,
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ) = AuthenticateUseCase(
        appServices = appServices,
        appDatabase = appDatabase,
        ioDispatcher = ioDispatcher
    )

    @ViewModelScoped
    @Provides
    fun provideGetUserUseCase(
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ) = GetUserUseCase(
        appDatabase = appDatabase,
        ioDispatcher = ioDispatcher
    )

}