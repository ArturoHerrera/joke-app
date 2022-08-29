package com.arthur.joke_app.data.remote.dto

import com.google.gson.annotations.SerializedName

class JokeResponse(
    @SerializedName("error") var error: Boolean? = true,
    @SerializedName("category") var category: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("setup") var setup: String? = null,
    @SerializedName("delivery") var delivery: String? = null,
    @SerializedName("joke") var joke: String? = null,
    @SerializedName("flags") var flags: JokeFlags? = JokeFlags(),
    @SerializedName("safe") var safe: Boolean? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("lang") var lang: String? = null
)

data class JokeFlags(
    @SerializedName("nsfw") var nsfw: Boolean? = null,
    @SerializedName("religious") var religious: Boolean? = null,
    @SerializedName("political") var political: Boolean? = null,
    @SerializedName("racist") var racist: Boolean? = null,
    @SerializedName("sexist") var sexist: Boolean? = null,
    @SerializedName("explicit") var explicit: Boolean? = null
)