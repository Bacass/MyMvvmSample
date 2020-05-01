package com.lee.mymvvmsample.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoObj {
    var total: Int = 0
    var totalHits: Int = 0
    var hits: MutableList<VideoHits> = mutableListOf()
}

data class VideoHits(
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("pageURL")
    val pageURL: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("tags")
    val tags: String,
    @Expose
    @SerializedName("duration")
    var duration: Int,
    @Expose
    @SerializedName("picture_id")
    val picture_id: String,
    @Expose
    @SerializedName("videos")
    var videos: Videos,
    @Expose
    @SerializedName("views")
    var views: Int,
    @Expose
    @SerializedName("downloads")
    var downloads: Int,
    @Expose
    @SerializedName("favorites")
    var favorites: Int,
    @Expose
    @SerializedName("likes")
    var likes: Int,
    @Expose
    @SerializedName("comments")
    var comments: Int,
    @Expose
    @SerializedName("user_id")
    var user_id: Int,
    @Expose
    @SerializedName("user")
    val user: String,
    @Expose
    @SerializedName("userImageURL")
    val userImageURL: String
)

data class Videos(
    @Expose
    @SerializedName("large")
    var large: Large,
    @Expose
    @SerializedName("medium")
    var medium: Medium,
    @Expose
    @SerializedName("small")
    var small: Small,
    @Expose
    @SerializedName("tiny")
    var tiny: Tiny
)

data class Tiny(
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("width")
    var width: Int,
    @Expose
    @SerializedName("height")
    var height: Int,
    @Expose
    @SerializedName("size")
    var size: Int
)

data class Small(
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("width")
    var width: Int,
    @Expose
    @SerializedName("height")
    var height: Int,
    @Expose
    @SerializedName("size")
    var size: Int
)

data class Medium(
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("width")
    var width: Int,
    @Expose
    @SerializedName("height")
    var height: Int,
    @Expose
    @SerializedName("size")
    var size: Int
)

data class Large(
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("width")
    var width: Int,
    @Expose
    @SerializedName("height")
    var height: Int,
    @Expose
    @SerializedName("size")
    var size: Int
)

