package com.example.usersinfoappxml.di

import android.content.Context
import com.example.usersinfoappxml.data.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharedPreferencesHelper(@ApplicationContext context : Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }
}