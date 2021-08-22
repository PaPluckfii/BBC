package com.buildweek.bbc.clone.repository

class DefaultMainRepository : MainRepository() {
    override suspend fun getNewsFromSpringBoot() {
        //Not implemented
    }

    override suspend fun getNewsFromOpenApi() {

    }
}