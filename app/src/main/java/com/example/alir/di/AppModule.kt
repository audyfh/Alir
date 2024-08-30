package com.example.alir.di

import android.content.Context
import android.provider.Settings.Global.getString
import com.example.alir.R
import com.example.alir.data.remote.auth.AccountRepoImpl
import com.example.alir.data.remote.auth.AuthRepoImpl
import com.example.alir.data.remote.donasi.DonasiRepoImpl
import com.example.alir.data.remote.lapor.LaporRepoImpl
import com.example.alir.data.remote.news.NewsRepoImpl
import com.example.alir.model.auth.repository.AuthRepo
import com.example.alir.model.lapor.LaporRepo
import com.example.alir.model.news.NewsRepo
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
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
    fun provideFirebaseAuth () =  Firebase.auth

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    @Singleton
    fun provideAuthRepoImpl(
        firebaseAuth: FirebaseAuth
    ) : AuthRepo = AuthRepoImpl(firebaseAuth = firebaseAuth)

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context
    ) : GoogleSignInClient{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("793444008750-9asadjqeil99vb3orvoveddderodmmlv.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    @Provides
    @Singleton
    fun provideLaporRepoImpl(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ) : LaporRepo = LaporRepoImpl(firestore, storage)

    @Provides
    @Singleton
    fun provideNewsRepoImpl(
        firestore: FirebaseFirestore
    ) : NewsRepo = NewsRepoImpl(firestore)

    @Provides
    @Singleton
    fun provideAccountRepoImpl(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ) : AccountRepoImpl = AccountRepoImpl(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun provideDonasiRepoImpl(
        firestore: FirebaseFirestore
    ) : DonasiRepoImpl = DonasiRepoImpl(firestore)
}