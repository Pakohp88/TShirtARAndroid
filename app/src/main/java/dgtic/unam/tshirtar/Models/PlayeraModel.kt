package dgtic.unam.tshirtar.Models
import com.google.gson.annotations.SerializedName
data class PlayeraModel(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description:String,
    @SerializedName("price") val price: String,
    @SerializedName("image") val image: String
    )
