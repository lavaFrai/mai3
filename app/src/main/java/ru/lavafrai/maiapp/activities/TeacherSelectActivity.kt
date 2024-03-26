package ru.lavafrai.maiapp.activities

import android.content.Intent
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.maiapp.api.Api

class TeacherSelectActivity : SearchActivity<TeacherId>() {
    class ReturnType {
        companion object {
            const val OpenTeacherInfoActivity = 1
        }
    }


    override fun onFound(selected: TeacherId) {
        val target = intent.extras!!.getInt(ExtraKeys.Target, ReturnType.OpenTeacherInfoActivity)

        when (target) {
            ReturnType.OpenTeacherInfoActivity -> {
                val intent = Intent(this, TeacherActivity::class.java)
                startActivity(intent)
            }
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
        ListItem(headlineContent = { Text(text = data.name) })
    }
}
