package com.d204.rumeet.domain.usecase.auth

import com.d204.rumeet.domain.repository.AuthRepository
import javax.inject.Inject

class DoKakaoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(accessToken : String) = authRepository.doKakaoLogin(accessToken)
}