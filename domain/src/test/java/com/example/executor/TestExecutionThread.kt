package com.example.executor

class TestExecutionThread : PostExecutionThread {

    override fun post(runnable: Runnable) {
        runnable.run()
    }
}