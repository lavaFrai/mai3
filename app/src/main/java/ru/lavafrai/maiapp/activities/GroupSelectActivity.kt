package ru.lavafrai.maiapp.activities

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.api.Api
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.utils.analyzeName

class GroupSelectActivity : SearchActivity<Group>() {
    class ReturnType {
        companion object {
            const val AddNewGroupAndOpenMainActivity = 0
        }
    }

    override fun onFound(selected: Group) {
        val target = intent.extras!!.getInt(ExtraKeys.Target, ReturnType.AddNewGroupAndOpenMainActivity)

        when (target) {
            ReturnType.AddNewGroupAndOpenMainActivity -> {
                Settings.setCurrentGroup(selected)

                startActivity(
                    Intent(
                        this@GroupSelectActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
        finish()
    }

    override fun getName(selected: Group): String {
        return selected.name
    }

    override fun getList(): List<Group>? {
        return Api.getInstance().getGroupsListOrNull()
    }

    @Composable
    override fun DrawListItem(data: Group) {
        ListItem(
            headlineContent = { Text(text = data.name) },
            supportingContent = {
                Text(
                    text = stringResource(id = R.string.faculty_and_course)
                        .replace("%faculty%", data.analyzeName().faculty.toString())
                        .replace("%course%", data.analyzeName().course.toString())
                )
            },
            modifier = Modifier.clickable {
                select(data)
            }
        )
    }

    override fun search(list: List<Group>, query: String): List<Group> {
        return list
            .filter { it.name.contains(query, ignoreCase = true) && it.name.isNotBlank() }
            .sortedBy { it.name }
    }
}
