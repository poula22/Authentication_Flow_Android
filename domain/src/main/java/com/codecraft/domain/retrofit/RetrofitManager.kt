package com.codecraft.domain.retrofit

interface RetrofitManager {
    fun <T> buildWebService(webService:Class<T>): T
}