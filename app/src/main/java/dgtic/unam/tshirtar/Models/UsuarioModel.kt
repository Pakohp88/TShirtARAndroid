package dgtic.unam.tshirtar.Models

import com.google.gson.annotations.SerializedName

data class UsuarioModel(
    @SerializedName("id") var id: Int?,
    @SerializedName("nombreCompleto") var nombreCompleto: String,
    @SerializedName("email") val email: String,
    @SerializedName("contrasena") val contrasena: String
)