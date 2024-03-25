package ru.lavafrai.maiapp.ui.fragments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomDialog(state: MutableState<Boolean>, content :@Composable () -> Unit) {
    if (state.value) ModalBottomSheet(onDismissRequest = { state.value = false }) {
        content()
        Spacer(modifier = Modifier.height(32.dp))
    }
}