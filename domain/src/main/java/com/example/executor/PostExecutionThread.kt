package com.example.executor

interface PostExecutionThread {

    fun post(runnable: Runnable)
}

