package com.codecraft.domain.model.user

import com.codecraft.domain.model.user.utils.Email

data class UserLogInDomainModel(val name:String, val email: Email, val token:String)