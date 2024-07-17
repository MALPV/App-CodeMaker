package cl.inventionchile.codemaker.di

import android.content.Context
import androidx.room.Room
import cl.inventionchile.codemaker.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleLocal {

    @Singleton
    @Provides
    fun providerAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}