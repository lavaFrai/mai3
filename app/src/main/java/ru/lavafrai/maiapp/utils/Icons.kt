package ru.lavafrai.maiapp.utils

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorGroup
import androidx.compose.ui.graphics.vector.VectorNode
import androidx.compose.ui.graphics.vector.VectorPath
import androidx.compose.ui.graphics.vector.group
import compose.icons.SimpleIcons
import compose.icons.simpleicons.Mediafire

object LocalIcons {
    val FireWorkIcon = ImageVector.copyFrom(
        src = SimpleIcons.Mediafire,
        rotation = 90f,
        pivotX = SimpleIcons.Mediafire.defaultWidth.value / 2,
        pivotY = SimpleIcons.Mediafire.defaultHeight.value / 2,
    )
}




fun ImageVector.Companion.copyFrom(
    src: ImageVector,
    rotation: Float = src.root.rotation,
    pivotX: Float = src.root.pivotX,
    pivotY: Float = src.root.pivotY,
) = ImageVector.Builder(
    name = src.name,
    defaultWidth = src.defaultWidth,
    defaultHeight = src.defaultHeight,
    viewportWidth = src.viewportWidth,
    viewportHeight = src.viewportHeight,
    tintColor = src.tintColor,
    tintBlendMode = src.tintBlendMode,
    autoMirror = src.autoMirror,
).addGroup(
    src = src.root,
    rotation = rotation,
    pivotX = pivotX,
    pivotY = pivotY,
).build()

private fun ImageVector.Builder.addNode(node: VectorNode) {
    when (node) {
        is VectorGroup -> addGroup(node)
        is VectorPath -> addPath(node)
    }
}

private fun ImageVector.Builder.addGroup(
    src: VectorGroup,
    rotation: Float = src.rotation,
    pivotX: Float = src.pivotX,
    pivotY: Float = src.pivotY,
) = apply {
    group(
        name = src.name,
        rotate = rotation,
        pivotX = pivotX,
        pivotY = pivotY,
        scaleX = src.scaleX,
        scaleY = src.scaleY,
        translationX = src.translationX,
        translationY = src.translationY,
        clipPathData = src.clipPathData,
    ) {
        src.forEach { addNode(it) }
    }
}

private fun ImageVector.Builder.addPath(src: VectorPath) = apply {
    addPath(
        pathData = src.pathData,
        pathFillType = src.pathFillType,
        name = src.name,
        fill = src.fill,
        fillAlpha = src.fillAlpha,
        stroke = src.stroke,
        strokeAlpha = src.strokeAlpha,
        strokeLineWidth = src.strokeLineWidth,
        strokeLineCap = src.strokeLineCap,
        strokeLineJoin = src.strokeLineJoin,
        strokeLineMiter = src.strokeLineMiter,
    )
}