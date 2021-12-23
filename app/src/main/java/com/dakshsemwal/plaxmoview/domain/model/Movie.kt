package com.dakshsemwal.plaxmoview.domain.model

import com.dakshsemwal.plaxmoview.common.Constants.IMAGE_URL

data class Movie(
    val id: Int,
    val poster_path: String?,
    val title: String,
    val backdrop_path: String?,
) {
    val poster_url = "$IMAGE_URL$poster_path"
    val backdrop_url = "$IMAGE_URL$backdrop_path"
}