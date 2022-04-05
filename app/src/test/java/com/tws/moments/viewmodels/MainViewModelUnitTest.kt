package com.tws.moments.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tws.moments.api.MomentRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class MainViewModelUnitTest {

    @Rule @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_fetch_tweets() {
        val momentRepository = mockk<MomentRepository>()
        coEvery {
            momentRepository.fetchTweets()
        } returns listOf()

        val mainViewModel = MainViewModel(momentRepository)
        mainViewModel.refreshTweets()

        Assert.assertEquals(0, mainViewModel.tweets.value?.size)
    }
}