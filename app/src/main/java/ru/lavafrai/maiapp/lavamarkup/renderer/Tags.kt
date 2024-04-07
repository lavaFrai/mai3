package ru.lavafrai.maiapp.lavamarkup.renderer

import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Action
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.BaseText
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Br
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Button
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Card
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Header
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Hr
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Icon
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.RichText
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Space
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.layout.Column
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.layout.Row

val Tags = listOf(
    BaseText,
    Button,
    Header,
    RichText,
    Space,
    Br,
    Card,
    Hr,
    Row,
    Column,
    Icon,
    Action,
)