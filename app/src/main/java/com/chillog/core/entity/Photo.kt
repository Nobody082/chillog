package com.chillog.core.entity

import com.chillog.core.entity.Metadata;

import java.time.Instant;

data class Photo(
    override val id: Long,
    override val name: String,
    override val createTime: Instant,
    override val filePath: String,
    override val note: String,

    val weight: Int,
    val height: Int,
    val metadata: Metadata
): Entity(
    id,
    name,
    createTime,
    filePath,
    note
)
