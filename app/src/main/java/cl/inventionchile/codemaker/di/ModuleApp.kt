package cl.inventionchile.codemaker.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Singleton
    @Provides
    fun providerContext(
        application: Application
    ): Context = application.applicationContext

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}