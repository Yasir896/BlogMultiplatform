package com.example.blogmultiplatform.util

import com.example.blogmultiplatform.models.User
import com.example.blogmultiplatform.models.UserWithoutPassword
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

suspend fun checkUserExistance(user: User): UserWithoutPassword? {
    return try {
        val result = window.api.tryPost(
            apiPath = "usercheck",
            body = Json.encodeToString(user).encodeToByteArray()
        )
        result?.let { Json.decodeFromString<UserWithoutPassword>(it.decodeToString()) }
    } catch (e: Exception) {
        println(e.message)
        null
    }
}