package ru.lavafrai.maiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import ru.lavafrai.mai.api.models.InfoListItemData
import ru.lavafrai.maiapp.ui.fragments.InfoCard
import ru.lavafrai.maiapp.ui.fragments.InfoHeader
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.ui.theme.MAI30Theme

class InfoListViewActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val extras = intent.extras

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val title = extras?.getString("title") ?: "Unknown category"
        val resourceId = extras?.getInt("resource") ?: R.raw.sport_sections

        setContent {
            MAI30Theme {
                MainView(title, resourceId)
            }
        }
    }

    @Preview
    @Composable
    fun MainView(title: String = "Category name", resourceId: Int = R.raw.sport_sections) {
        val context = LocalContext.current
        val infoItems = Json{ ignoreUnknownKeys = true }.decodeFromStream<List<InfoListItemData>>(context.resources.openRawResource(resourceId))

        Scaffold { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                Header(title)

                LazyColumn () {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    items(infoItems) {
                        if (it.type == "card") {
                            InfoCard(
                                firstText = it.firstText,
                                secondText = it.secondText,
                                thirdText = it.thirdText,
                                contactText = it.contactText,
                                contactType = it.contactType,
                                contactLink = it.contactLink,
                                topText = it.topText,
                            )
                        } else {
                            InfoHeader(
                                firstText = it.firstText,
                                secondText = it.secondText,
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }

        }
    }


    @Composable
    fun Header(title: String) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .fillMaxWidth(),
            ) {
                IconButton(onClick = { this@InfoListViewActivity.finish() }) {
                    Icon(Icons.Default.ArrowBack, null)
                }
                TextH3(text = title)
            }

            Box(
                modifier = Modifier
                    .height(0.5.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}