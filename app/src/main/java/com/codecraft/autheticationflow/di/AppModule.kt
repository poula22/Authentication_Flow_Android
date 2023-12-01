package com.codecraft.autheticationflow.di

import com.codecraft.data.api.RetrofitManagerImp
import com.codecraft.data.data_source.AuthenticationDataSourceImp
import com.codecraft.data.repo.AuthenticationRepositoryImp
import com.codecraft.domain.data_source.AuthenticationDataSource
import com.codecraft.domain.repo.AuthenticationRepository
import com.codecraft.domain.retrofit.RetrofitManager
import com.codecraft.domain.use_case.LogInUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun provideRetrofitManager(retrofitManager: RetrofitManagerImp):RetrofitManager

    @Singleton
    @Binds
    abstract fun provideAuthenticationDataSource(authenticationDataSource: AuthenticationDataSourceImp):AuthenticationDataSource

    @Singleton
    @Binds
    abstract fun provideAuthenticationRepository(authenticationRepository: AuthenticationRepositoryImp):AuthenticationRepository

    companion object{
        @Singleton
        @Provides
        fun provideLogInUseCase(authenticationRepository:AuthenticationRepository): LogInUseCase {
            return LogInUseCase(authenticationRepository)
        }
    }
}