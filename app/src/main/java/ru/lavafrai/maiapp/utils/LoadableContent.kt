package ru.lavafrai.maiapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ru.lavafrai.maiapp.ui.fragments.network.NetworkErrorView
import ru.lavafrai.maiapp.ui.fragments.network.NetworkLoadingView
import kotlin.concurrent.thread

class LoadableContent<T> (
    val loader: () -> T,
    val onLoadingView: @Composable () -> Unit = { NetworkLoadingView() },
    val onFailView: @Composable (cause: Exception) -> Unit = { NetworkErrorView() },
    val onLoadedView: @Composable (result: T) -> Unit,
) {
    private val state = mutableStateOf(State.Loading)
    private var result: T? = null
    private var failCause: Exception? = null

    init {
        thread {
            try {
                result = loader()
                withMainContext(null) {
                    state.value = State.Loaded
                }
            } catch (e: Exception) {
                withMainContext(null) {
                    state.value = State.Failed
                }
                e.printStackTrace()
            }
        }
    }

    @Composable
    fun View() {
        if (result == null && state.value != State.Loading) {
            onFailView(NullPointerException("Result is null"))
        }
        else when (state.value) {
            State.Failed -> onFailView(failCause!!)
            State.Loading -> onLoadingView()
            State.Loaded -> onLoadedView(result!!)
        }
    }

    companion object {
        enum class State {
            Loading,
            Loaded,
            Failed,
        }
    }
}

@Composable
fun <T>LoadableContentView(
    loader: () -> T,
    onLoadingView: @Composable () -> Unit = { NetworkLoadingView() },
    onFailView: @Composable (cause: Exception) -> Unit = { NetworkErrorView() },
    onLoadedView: @Composable (result: T) -> Unit,
) {
    LoadableContent(
        loader,
        onLoadingView,
        onFailView,
        onLoadedView,
    ).View()
}