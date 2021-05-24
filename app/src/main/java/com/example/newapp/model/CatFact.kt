package com.example.newapp.model

import com.google.gson.annotations.SerializedName

data class CatFact(
    val status: FactStatus,
    val type: String,
    val deleted: Boolean,
    @SerializedName("_id") val id: String,
    val user: String,
    val text: String,
    @SerializedName("__v") val versionNumber: Int,
    val source: String,
    val updatedAt: String,
    val createdAt: String,
    val used: Boolean
)

data class FactStatus(
    val verified: Boolean,
    val sentCount: Int
)
