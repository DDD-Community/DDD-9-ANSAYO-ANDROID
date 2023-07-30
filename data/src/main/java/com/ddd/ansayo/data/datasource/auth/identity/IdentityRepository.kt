package com.ddd.ansayo.data.datasource.auth.identity

import com.example.domain.custmoer.model.SocialProvider
import com.example.domain.identity.model.SocialSignUp
import com.example.domain.identity.model.TokenInfoResponse

interface IdentityRepository {

    suspend fun signInWithSocial(provider: SocialProvider, token: String): TokenInfoResponse

    suspend fun signUpWithSocial(signUp: SocialSignUp): TokenInfoResponse

    suspend fun signOut()
}