package com.buildweek.bbc.clone.repository

abstract class MainRepository {

    abstract suspend fun getNewsFromSpringBoot()
    abstract suspend fun getNewsFromOpenApi()

}