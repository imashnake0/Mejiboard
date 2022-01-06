package com.github.uragiristereo.mejiboard.data.dto.api.post

import com.github.uragiristereo.mejiboard.data.dto.api.common.Attributes
import com.squareup.moshi.Json

data class PostResultDto(
    @field:Json(name = "@attributes")
    val attributes: Attributes,
    val post: List<PostDto>?,
)