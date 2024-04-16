package ru.lavafrai.maiapp.activities.pages

import Quadtuple
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.BikingSolid
import compose.icons.lineawesomeicons.BookOpenSolid
import compose.icons.lineawesomeicons.BookSolid
import compose.icons.lineawesomeicons.CoffeeSolid
import compose.icons.lineawesomeicons.Comments
import compose.icons.lineawesomeicons.Map
import compose.icons.lineawesomeicons.PaletteSolid
import compose.icons.lineawesomeicons.UserFriendsSolid
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.activities.ExlerTeacherSelectActivity
import ru.lavafrai.maiapp.activities.InfoListViewActivity
import ru.lavafrai.maiapp.activities.MapViewActivity
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRendererActivity
import ru.lavafrai.maiapp.ui.fragments.layout.PageTitle


@Preview
@Composable
fun InfoPage() {
    val campusCategories = listOf<Quadtuple<String, ImageVector?, Any?, Class<*>?>>(

        Quadtuple(stringResource(id = R.string.campus), null, null, null),
        Quadtuple(
            stringResource(id = R.string.campus_map),
            LineAwesomeIcons.Map,
            R.drawable.img_campus_map_new,
            MapViewActivity::class.java
        ),
        Quadtuple(
            stringResource(id = R.string.canteens_and_buffets),
            LineAwesomeIcons.CoffeeSolid,
            R.raw.cafeteries,
            InfoListViewActivity::class.java
        ),
        Quadtuple(
            stringResource(id = R.string.libraries),
            LineAwesomeIcons.BookOpenSolid,
            R.raw.libraries,
            InfoListViewActivity::class.java
        ),
        Quadtuple(stringResource(id = R.string.live), null, null, null),
        Quadtuple(
            stringResource(id = R.string.about_teachers),
            LineAwesomeIcons.Comments,
            ExlerTeacherSelectActivity.ReturnType.OpenTeacherInfoActivity,
            ExlerTeacherSelectActivity::class.java
        ),
        Quadtuple(
            stringResource(id = R.string.sport_sections),
            LineAwesomeIcons.BikingSolid,
            "sport_sections",
            LavamarkupRendererActivity::class.java
        ),
        Quadtuple(
            stringResource(id = R.string.mai_dictionary),
            LineAwesomeIcons.BookSolid,
            "exlers_dictionary",
            LavamarkupRendererActivity::class.java
        ),
        Quadtuple(
            stringResource(id = R.string.creative_teams),
            LineAwesomeIcons.PaletteSolid,
            R.raw.creative_teams,
            InfoListViewActivity::class.java
        ),
        Quadtuple(
            stringResource(id = R.string.students_organizations),
            LineAwesomeIcons.UserFriendsSolid,
            R.raw.students_organizations,
            InfoListViewActivity::class.java
        ),
    )

    PageTitle (title = stringResource(id = R.string.information), padded = false) {
        LazyColumn {
            items(campusCategories) { item ->
                if (item.second != null && item.third != null) {
                    InfoCategory(item.first, item.second, item.third, item.fourth!!)
                } else {
                    InfoHeader(item.first)
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun InfoHeader(text: String) {
    Text(
        text = text,
        fontSize = 24.sp,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 8.dp)
            .fillMaxWidth(),
        fontWeight = FontWeight.SemiBold
    )
}


@Preview
@Composable
fun InfoCategory(
    name: String = "Category",
    icon: ImageVector = Icons.Default.Settings,
    resource: Any = 0,
    activity: Class<*> = InfoListViewActivity::class.java
) {
    val context = LocalContext.current

    Card(
        Modifier
            .padding(16.dp, 8.dp)
            .fillMaxWidth()
    ) {
        Box(
            Modifier.clickable {
                val intent = Intent(context, activity)
                intent.putExtra("title", name)

                if (resource is Int) intent.putExtra("resource", resource as Int)
                if (resource is String) {
                    val url = Settings.getApiUrl() ?: "https://mai3.lavafrai.ru/"
                    intent.putExtra(LavamarkupRendererActivity.Companion.ExtraKeys.url, "$url/data/${resource as String}")
                }

                context.startActivity(intent)
            }
        ) {
            Row(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    icon,
                    null,
                    Modifier
                        .height(28.dp)
                        .width(28.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}
