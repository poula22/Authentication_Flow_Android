package com.codecraft.autheticationflow.presentation.log_in

import com.codecraft.domain.model.Resource
import com.codecraft.domain.model.user.UserLogInDomainModel

data class LogInState(val result:Resource<UserLogInDomainModel>?)