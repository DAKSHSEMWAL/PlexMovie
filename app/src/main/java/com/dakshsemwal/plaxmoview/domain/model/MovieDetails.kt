package com.dakshsemwal.plaxmoview.domain.model

import android.util.Log
import com.dakshsemwal.plaxmoview.common.Constants
import com.dakshsemwal.plaxmoview.common.Constants.IMAGE_URL
import com.dakshsemwal.plaxmoview.common.getFormattedDate
import com.dakshsemwal.plaxmoview.common.getTimeDuration
import com.dakshsemwal.plaxmoview.data.remote.dto.Genre
import com.dakshsemwal.plaxmoview.data.remote.dto.ProductionCompany
import java.util.*
import kotlin.collections.ArrayList

data class MovieDetails (
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<Genre>,
){
    init {
        Log.e("TimeDuration", runtime.toString())
        Log.e("Date", release_date.toString())
    }
    val poster_url = "$IMAGE_URL$poster_path"
    val tagsList : List<String> = genres.map { it.name } as ArrayList<String>
    val timeDuration = Date(runtime.toLong()).getTimeDuration()
    val releaseDate : String = getFormattedDate(Constants.DATE_YYYY_MM_dd,Constants.DATE_dd_MMM_yyyy,release_date).toString()
}