package com.nayeem2021.liilab_app_dev_assesment_project.di

import com.nayeem2021.liilab_app_dev_assesment_project.data.repository.AuthRepositoryImpl
import com.nayeem2021.liilab_app_dev_assesment_project.data.source.local.UserLocalDataSource
import com.nayeem2021.liilab_app_dev_assesment_project.domain.repository.AuthRepository
import com.nayeem2021.liilab_app_dev_assesment_project.domain.usecase.RegistrationDataValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(localDataSource: UserLocalDataSource) : AuthRepository {
        return AuthRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideRegistrationValidationUseCase() : RegistrationDataValidationUseCase {
        return RegistrationDataValidationUseCase()
    }

}