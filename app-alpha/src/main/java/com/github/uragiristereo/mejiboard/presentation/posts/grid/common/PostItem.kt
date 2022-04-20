package com.github.uragiristereo.mejiboard.presentation.posts.grid.common

import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.github.uragiristereo.mejiboard.domain.entity.Post
import com.github.uragiristereo.mejiboard.presentation.main.MainViewModel
import com.github.uragiristereo.mejiboard.presentation.posts.PostsViewModel
import java.io.File

@Composable
fun PostItem(
    mainNavigation: NavHostController,
    post: Post,
    index: Int,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier,
    postsViewModel: PostsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val imageType = remember { File(post.image).extension }
    val supportedTypesAnimation = remember { listOf("gif", "webm", "mp4") }
    val url = remember { "https://img3.gelbooru.com/thumbnails/" + post.directory + "/thumbnail_" + post.hash + ".jpg" }
    val borderColor = remember {
        when (imageType) {
            "gif" -> Color.Cyan
            "webm" -> Color.Blue
            "mp4" -> Color.Blue
            else -> Color.Transparent
        }
    }

    Box(
        modifier = modifier
            .padding(
                start = if (index > 0) 4.dp else 0.dp,
                end = if (index == 0) 4.dp else 0.dp,
            )
            .aspectRatio(ratio = 3f / 4f)
            .border(
                BorderStroke(
                    width = if (imageType in supportedTypesAnimation) 4.dp else 0.dp,
                    color = borderColor,
                )
            )
            .clickable {
                if (postsViewModel.allowPostClick) {
                    postsViewModel.allowPostClick = false
                    mainViewModel.saveSelectedPost(post)
                    mainViewModel.backPressedByGesture = false

                    mainNavigation.navigate("image")
                }
            }
    ) {
        CompositionLocalProvider(LocalImageLoader provides mainViewModel.imageLoader) {
            val placeholder = remember { GradientDrawable() }

            placeholder.setSize(post.width, post.height)
            placeholder.setColor(android.graphics.Color.DKGRAY)

            Image(
                painter = rememberImagePainter(
                    ImageRequest.Builder(context)
                        .data(url)
                        .placeholder(placeholder)
                        .crossfade(170)
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}