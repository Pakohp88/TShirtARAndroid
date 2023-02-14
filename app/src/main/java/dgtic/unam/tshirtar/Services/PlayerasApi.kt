package dgtic.unam.tshirtar.Services

import dgtic.unam.tshirtar.Models.PlayeraModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PlayerasApi {
    @GET
    suspend fun  getPlayeras(@Url url: String): Response<List<PlayeraModel>>
}