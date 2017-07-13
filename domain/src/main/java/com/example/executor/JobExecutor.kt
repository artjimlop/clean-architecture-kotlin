package com.example.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobExecutor @Inject constructor() : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        val workQueue = LinkedBlockingQueue<Runnable>()
        this.threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME.toLong(), KEEP_ALIVE_TIME_UNIT, workQueue)
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
