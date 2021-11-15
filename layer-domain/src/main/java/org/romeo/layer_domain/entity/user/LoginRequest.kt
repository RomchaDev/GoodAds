package org.romeo.layer_domain.entity.user

data class LoginRequest(
    val login: String,
    val password: String
)
