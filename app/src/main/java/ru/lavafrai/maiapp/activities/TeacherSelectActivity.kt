package ru.lavafrai.maiapp.activities

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.maiapp.api.Api

class TeacherSelectActivity : SearchActivity<TeacherId>() {
    class ReturnType {
        companion object {

        }
    }


    override fun onFound(selected: TeacherId) {
        var target = intent.extras!!.getInt(ExtraKeys.Target, -1)
        if (target == -1) target = intent.extras!!.getInt("resource", 0)

        when (target) {

        }

        finish()
    }

    override fun getName(selected: TeacherId): String {
        return selected.name
    }

    override fun getList(): List<TeacherId>? {
        return Api.getInstance().getTeachers()
    }

    override fun search(list: List<TeacherId>, query: String): List<TeacherId> {
        return list
            .filter { it.name.contains(query, ignoreCase = true) && it.name.isNotBlank() }
            .sortedBy { it.name }
    }

    @Composable
    override fun DrawListItem(data: TeacherId) {
        ListItem(
            headlineContent = { Text(text = data.name) },
            modifier = Modifier.clickable { select(data) }
        )
    }
}
