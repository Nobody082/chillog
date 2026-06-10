package com.chillog.core.entity
import java.time.Instant;

abstract class Entity(
    open val id: Long,
    open val name: String,
    open val createTime: Instant,
    open val filePath: String,
    open val note: String,
)
