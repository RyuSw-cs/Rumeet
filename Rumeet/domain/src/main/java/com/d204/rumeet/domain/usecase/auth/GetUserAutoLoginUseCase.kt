package com.d204.rumeet.domain.usecase.auth

import com.d204.rumeet.domain.repository.UserRepository
import javax.inject.Inject

class GetUserAutoLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke() = userRepository.getUserFirstRunCheck()
}