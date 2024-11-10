package com.nayeem2021.liilab_app_dev_assesment_project.di

import android.content.Context
import androidx.room.Room
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.AuthRepositoryImpl
import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.AuthRepositoryImplRoom
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.Database
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.EmailValidityCheckUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.PasswordValidityCheckUseCase
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationDataValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideAuthRepository(database: Database): AuthRepository {
        return AuthRepositoryImplRoom(database)
    }

    @Provides
    @Singleton
    fun provideRegistrationValidationUseCase(): RegistrationDataValidationUseCase {
        return RegistrationDataValidationUseCase()
    }

    @Provides
    @Singleton
    fun provideEmailValidityCheckUseCase(): EmailValidityCheckUseCase {
        return EmailValidityCheckUseCase()
    }

    @Provides
    @Singleton
    fun providePasswordValidityCheckUseCase(): PasswordValidityCheckUseCase {
        return PasswordValidityCheckUseCase()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "7ijdb"
        ).build()
    }
}