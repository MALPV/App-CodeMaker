package cl.inventionchile.codemaker.di

import cl.inventionchile.codemaker.data.local.AppDatabase
import cl.inventionchile.codemaker.data.remote.AppServices
import cl.inventionchile.codemaker.data.usecases.AddServicesUseCase
import cl.inventionchile.codemaker.data.usecases.AuthenticateUseCase
import cl.inventionchile.codemaker.data.usecases.DeleteServicesUseCase
import cl.inventionchile.codemaker.data.usecases.GetUserUseCase
import cl.inventionchile.codemaker.data.usecases.ObserveServicesUseCase
import cl.inventionchile.codemaker.data.usecases.UpdateSelectedServiceUseCase
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

    @ViewModelScoped
    @Provides
    fun provideObserveServicesUseCase(
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ) = ObserveServicesUseCase(
        appDatabase = appDatabase,
        ioDispatcher = ioDispatcher
    )

    @ViewModelScoped
    @Provides
    fun provideAddServicesUseCase(
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ) = AddServicesUseCase(
        appDatabase = appDatabase,
        ioDispatcher = ioDispatcher
    )

    @ViewModelScoped
    @Provides
    fun provideDeleteServicesUseCase(
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ) = DeleteServicesUseCase(
        appDatabase = appDatabase,
        ioDispatcher = ioDispatcher
    )

    @ViewModelScoped
    @Provides
    fun provideUpdateUserUseCase(
        appDatabase: AppDatabase,
        ioDispatcher: CoroutineDispatcher
    ) = UpdateSelectedServiceUseCase(
        appDatabase = appDatabase,
        ioDispatcher = ioDispatcher
    )

}