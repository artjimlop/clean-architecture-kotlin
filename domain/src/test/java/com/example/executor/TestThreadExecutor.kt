package com.example.executor

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class TestThreadExecutor : ThreadExecutor {

    private val workQueue: BlockingQueue<Runnable>

    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        this.workQueue = LinkedBlockingQueue()
        this.threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME.toLong(), KEEP_ALIVE_TIME_UNIT, this.workQueue)
    }

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    companion object {
        private val INITIAL_POOL_SIZE = 3

        private val MAX_POOL_SIZE = 5

        private val KEEP_ALIVE_TIME = 10

        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    }
}