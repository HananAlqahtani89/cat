package net.hanan.cat.remote

import net.hanan.cat.remote.dto.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    companion object {
        const val BASE_URL = "https://api.thecatapi.com"
        const val API_KEY = "live_jVsyuDwgYHmd1dpkQR8gmmRuqVdHAOXLAFvWWDVjdSJ0i3k9A3o3YVyQhqUog34L"
    }

    @GET("v1/images/search")
    suspend fun getCats(
        @Query("limit") limit: Int,
        @Query("breed_ids") breedIds: String,
        @Query("api_key") key: String
    ): List<CatDto>
}