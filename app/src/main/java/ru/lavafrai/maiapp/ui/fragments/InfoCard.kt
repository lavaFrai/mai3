package ru.lavafrai.maiapp.ui.fragments

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Globe


@Preview
@Composable
fun InfoCard(
    firstText: String = "Волоколамское шоссе 24Б, 5 этаж, у Нелина в кабинете",
    secondText: String? = "Пн-Пт 9:00-18:00 вход только с чаем",
    thirdText: String? = "24Б",
    contactText: String? = "vk.com/lava_frai",
    contactType: String? = "geo",
    contactLink: String? = null,
    topText: String? = "Вкусный чаек",
) {
    val context = LocalContext.current
    Box (Modifier.padding(top = if (topText == null) 0.dp else 8.dp, bottom = 0.dp)) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .padding(top = if (topText == null) 8.dp else 16.dp, bottom = 0.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .padding(top = if (topText == null) 8.dp else 24.dp),

                ) {
                Text(text = firstText, fontSize = 18.sp)

                if (secondText != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = secondText,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                        fontSize = 16.sp
                    )
                }

                if (thirdText != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = thirdText,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                }

                if (contactText != null && contactType != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse("$contactType:${contactLink ?: contactText}"));
                        context.startActivity(intent)
                    }) {
                        when (contactType) {
                            "tel" -> Icon(Icons.Default.Phone, null)
                            "mailto" -> Icon(Icons.Default.Email, null)
                            "https" -> Icon(FeatherIcons.Globe, null)
                            "geo" -> Icon(Icons.Default.LocationOn, null)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = contactText)
                    }
                }
            }
        }

        if (topText != null) {
            Button(onClick = {}, Modifier.offset(24.dp)) {
                Text(topText)
            }
        }
    }
}
