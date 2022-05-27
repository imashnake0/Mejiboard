package com.github.uragiristereo.mejiboard.common

import com.github.uragiristereo.mejiboard.domain.entity.provider.post.Rating

object RatingFilter {
    // this lists ratings that to be FILTERED
    val GENERAL_ONLY = listOf(Rating.SENSITIVE, Rating.QUESTIONABLE, Rating.EXPLICIT)
    val SAFE = listOf(Rating.QUESTIONABLE, Rating.EXPLICIT)
    val NO_EXPLICIT = listOf(Rating.EXPLICIT)
    val UNFILTERED = listOf<Rating>()

    val list = listOf(
        GENERAL_ONLY,
        SAFE,
        NO_EXPLICIT,
        UNFILTERED,
    )

    val map = mapOf(
        "general_only" to GENERAL_ONLY,
        "safe" to SAFE,
        "no_explicit" to NO_EXPLICIT,
        "unfiltered" to UNFILTERED,
    )

    fun getAvailableRatings(safeListingOnly: Boolean): Map<String, List<Rating>> {
        return mapOf(
            "general_only" to GENERAL_ONLY,
            "safe" to SAFE,
        ).let {
            when {
                !safeListingOnly -> it + mapOf(
                    "no_explicit" to NO_EXPLICIT,
                    "unfiltered" to UNFILTERED,
                )
                else -> it
            }
        }
    }

    fun getPair(filter: List<Rating>): Pair<String, List<Rating>> {
        return when (filter) {
            GENERAL_ONLY -> "general_only" to GENERAL_ONLY
            SAFE -> "safe" to SAFE
            NO_EXPLICIT -> "no_explicit" to NO_EXPLICIT
            UNFILTERED -> "unfiltered" to UNFILTERED
            else -> "general_only" to GENERAL_ONLY
        }
    }
}