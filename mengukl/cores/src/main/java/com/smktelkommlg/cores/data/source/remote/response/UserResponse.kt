package com.asthiseta.core.data.soure.remote.response

import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "id")
    val id: Int?,

    @field:Json(name="login")
    val login: String?,

    @field:Json(name="html_url")
    val url: String?,

    @field:Json(name="avatar_url")
    val avatarUrl: String?,

    @field:Json(name="name")
    val name: String?,

    @field:Json(name="location")
    val location: String?,

    @field:Json(name="type")
    val type: String?,

    @field:Json(name="public_repos")
    val publicRepos: Int?,

    @field:Json(name="followers")
    val followers: Int?,

    @field:Json(name="following")
    val following: Int?,
)