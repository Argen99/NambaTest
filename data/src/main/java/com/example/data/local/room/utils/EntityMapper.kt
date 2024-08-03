package com.example.data.local.room.utils

interface EntityMapper<T> {
    fun toModel(): T
}