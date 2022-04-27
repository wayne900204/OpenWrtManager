package com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository

import tw.andyang.kotlinandroidworkshop.network.AuthenticateClient
import tw.andyang.kotlinandroidworkshop.network.entity.User

class UserRepository(private val authenticateClient: AuthenticateClient) {



    suspend fun listUser(): List<User> {
        return authenticateClient.listUser()
    }

    suspend fun getUser(userId: Int): User {
        return authenticateClient.getUser(userId)
    }
}