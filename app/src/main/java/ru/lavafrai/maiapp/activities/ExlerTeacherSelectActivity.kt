package ru.lavafrai.maiapp.activities

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.exler.mai.types.teacherNameHash
import ru.lavafrai.maiapp.api.LocalApi
import ru.lavafrai.maiapp.utils.mapThreaded
import kotlin.concurrent.thread

class ExlerTeacherSelectActivity : SearchActivity<Teacher>() {
    class ReturnType {
        companion object {
            const val OpenTeacherInfoActivity = 1
        }
    }


    override fun onFound(selected: Teacher) {
        var target = intent.extras!!.getInt(ExtraKeys.Target, -1)
        if (target == -1) target = intent.extras!!.getInt("resource", ReturnType.OpenTeacherInfoActivity)

        when (target) {
            ReturnType.OpenTeacherInfoActivity -> {
                thread {
                    val intent = Intent(this, TeacherActivity::class.java)
                    intent.putExtra("teacher", Json.encodeToString(selected))
                    startActivity(intent)
                }
            }
        }

        finish()
    }

    override fun getName(selected: Teacher): String {
        return selected.name
    }

    override fun getList(): List<Teacher>? {
        val teachers = LocalApi.getTeachers()
        val knownTeacherHashes = teachers?.mapThreaded { teacherNameHash(it.name) }

        return LocalApi.getExlerTeachers()?.filter { knownTeacherHashes?.contains(it.nameHash) == true }
    }

    override fun search(list: List<Teacher>, query: String): List<Teacher> {
        return list
            .filter { it.name.contains(query, ignoreCase = true) && it.name.isNotBlank() }
            .sortedBy { it.name }
    }

    @Composable
    override fun DrawListItem(data: Teacher) {
        ListItem(
            headlineContent = { Text(text = data.name) },
            modifier = Modifier.clickable { select(data) }
        )
    }
}
