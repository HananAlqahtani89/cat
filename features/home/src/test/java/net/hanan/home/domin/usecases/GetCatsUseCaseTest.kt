package net.hanan.home.domin.usecases

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import net.hanan.core.domain.model.CatInfo
import net.hanan.core.domain.repository.CatRepository
import net.hanan.core.util.Resource
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCatsUseCaseTest {

    private lateinit var repository: CatRepository
    private lateinit var getCatsUseCase: GetCatsUseCase

    private val catList = listOf(
        CatInfo("Cat 1", "url 1"),
        CatInfo("Cat 2", "url 2"),
        CatInfo("Cat 3", "url 3")
    )

    @Before
    fun setUp() {
        repository = mockk()
        getCatsUseCase = GetCatsUseCase(repository)
    }

    @Test
    fun `getCats should return a flow of resource with success when repo returns a non-empty list`() =
        runBlocking {
            val expectedResource = Resource.Success(catList)
            coEvery { repository.getCats() } returns flowOf(expectedResource)

            val resultFlow = getCatsUseCase.invoke()

            resultFlow.collect { result ->
                assertEquals(expectedResource, result)
            }
        }

    @Test
    fun `getCats should return a flow of Resource with  success when repos returns an empty list`() =
        runBlocking {
            val expectedResource = Resource.Success(emptyList<CatInfo>())
            coEvery { repository.getCats() } returns flowOf(expectedResource)

            val resultFlow = getCatsUseCase.invoke()

            resultFlow.collect { result ->
                assertEquals(expectedResource, result)
            }
        }

    @Test
    fun `getCats should return an empty list repos returns an empty list`() = runBlocking {
        val expectedResource = Resource.Success(emptyList<CatInfo>())
        coEvery { repository.getCats() } returns flowOf(expectedResource)

        val resultFlow = getCatsUseCase.invoke()

        resultFlow.collect { result ->
            assertEquals(0, result.data?.size)
        }
    }

    @Test
    fun `getCat should return a flow of Resource Success with 3 items when repo return 3 items`() =
        runBlocking {
            val expectedResource = Resource.Success(catList)
            coEvery { repository.getCats() } returns flowOf(expectedResource)

            val resultFlow = getCatsUseCase.invoke()

            resultFlow.collect { result ->
                assertEquals(3, result.data?.size)
            }
        }


    @Test
    fun `getCats should return the same list as the repos return `() =
        runBlocking {
            val expectedResource = Resource.Success(catList)
            coEvery { repository.getCats() } returns flowOf(expectedResource)

            val resultFlow = getCatsUseCase.invoke()

            resultFlow.collect { result ->
                assertEquals(catList, result.data)
            }
        }
}