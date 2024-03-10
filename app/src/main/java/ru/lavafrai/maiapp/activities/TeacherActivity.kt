package ru.lavafrai.maiapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import kotlinx.serialization.json.Json
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.exler.mai.types.TeacherInfo
import ru.lavafrai.exler.mai.types.TeacherReview
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.api.Api
import ru.lavafrai.maiapp.ui.fragments.NetworkErrorView
import ru.lavafrai.maiapp.ui.fragments.text.NetworkLoadingView
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.utils.toHypertext
import kotlin.concurrent.thread
import kotlin.math.absoluteValue

class TeacherActivity : ComponentActivity() {
    private lateinit var teacher: Teacher

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        teacher = Json.decodeFromString(intent.extras?.getString("teacher")!!)

        setContent {
            TeacherView(teacher)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun TeacherView(teacher: Teacher) {
        var teacherInfo = null as TeacherInfo?
        var downloaded by remember { mutableStateOf(null as Boolean?) }

        thread {
            teacherInfo = Api.getInstance().getTeacherInfo(teacher)
            downloaded = true
        }

        MAI30Theme {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 36.dp)
                    .fillMaxSize()
            ) {
                when (downloaded) {
                    null -> NetworkLoadingView()
                    false -> NetworkErrorView()
                    true -> {
                        Column(
                            Modifier
                                .padding(8.dp)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            val pagerState =
                                rememberPagerState(pageCount = { teacherInfo?.photo?.size ?: 0 })

                            Text(
                                text = teacherInfo!!.name,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )

                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                contentPadding = PaddingValues(horizontal = 32.dp)
                            ) { page ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Card(
                                        Modifier
                                            .height(300.dp)
                                            .padding(horizontal = 8.dp)
                                            .graphicsLayer {
                                                // Calculate the absolute offset for the current page from the
                                                // scroll position. We use the absolute value which allows us to mirror
                                                // any effects for both directions
                                                val pageOffset = (
                                                        (pagerState.currentPage - page) + pagerState
                                                            .currentPageOffsetFraction
                                                        ).absoluteValue

                                                // We animate the alpha, between 50% and 100%
                                                alpha = lerp(
                                                    start = 0.5f,
                                                    stop = 1f,
                                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                                )
                                            },
                                    ) {

                                        Image(
                                            painter = rememberImagePainter(
                                                teacherInfo!!.photo?.get(
                                                    page
                                                )
                                            ),
                                            null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxHeight()
                                        )
                                    }
                                }
                            }

                            //Card {
                                Column {
                                    if (teacherInfo?.faculty != null) Text(
                                        text = stringResource(id = R.string.faculty) + ": " + teacherInfo?.faculty,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                    if (teacherInfo?.department != null) Text(
                                        text = stringResource(id = R.string.department) + ": " + teacherInfo?.department,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }
                            //}

                            teacherInfo?.reviews?.sortedBy { it.publishTime }?.reversed()?.forEach {
                                TeacherReviewView(it)
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun TeacherReviewView(review: TeacherReview) {
        val a = "<span>a</span><span>b</span>c<span>d</span>e"

        Card (modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()) {
            Column (modifier = Modifier.padding(8.dp)) {
                if (review.author != null) Text(text = stringResource(id = R.string.author) + ": " + review.author.toHypertext().trim(), color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f))
                if (review.source != null) Text(text = stringResource(id = R.string.source) + ": " + review.source.toHypertext().trim(), color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f))
                if (review.publishTime != null) Text(text = stringResource(id = R.string.publishTime) + ": " + review.publishTime, color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f))
                if (review.hypertext != null) Text(review.hypertext.toHypertext(), modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}