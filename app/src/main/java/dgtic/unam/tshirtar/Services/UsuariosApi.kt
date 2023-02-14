package dgtic.unam.tshirtar.Services

import dgtic.unam.tshirtar.Models.UsuarioModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UsuariosApi {
    @GET
    suspend fun getUsuarios(@Url url: String): Response<List<UsuarioModel>>

    @Headers("Content-Type: application/json")
    @POST("user")
    fun addUser(@Body userData: UsuarioModel): Call<UsuarioModel>
}