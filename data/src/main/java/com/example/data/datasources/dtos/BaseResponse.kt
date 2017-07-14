package com.example.data.dtos

open class BaseResponse<out T>(val code: Int,
                               val status: String,
                               val copyright: String,
                               val attributionText: String,
                               val attributionHTML: String,
                               val etag: String,
                               val data: T)