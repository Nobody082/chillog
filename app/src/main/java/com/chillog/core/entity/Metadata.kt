package com.chillog.core.entity

data class Metadata(
    val deviceName: String,
    val f: Double,
    val s: Int,
    val focalLength: Double, //mm
    val iso: Int,
    val capacity: Double, //MP
    val location: String
)
