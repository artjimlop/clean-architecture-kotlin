package com.example.data.dtos

data class ListResponse<out T>(val offset: Int,
                               val limit: Int,
                               val total: Int,
                               val count: Int,
                               val results: List<T>)