package dgtic.unam.tshirtar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dgtic.unam.tshirtar.databinding.ActivityInicioBinding
import androidx.recyclerview.widget.LinearLayoutManager
import dgtic.unam.tshirtar.Adpters.PlayeraAdapter
import dgtic.unam.tshirtar.Models.PlayeraModel
import dgtic.unam.tshirtar.Services.PlayerasApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    private lateinit var adapter: PlayeraAdapter
    private val playeras = mutableListOf<PlayeraModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initReclyView()
        load()
    }

    private fun initReclyView() {
        adapter=PlayeraAdapter(playeras)
        binding.data.layoutManager = LinearLayoutManager(this)
        binding.data.adapter = adapter
    }

    private fun getUrl(): Retrofit { return Retrofit.Builder()
        .baseUrl("https://63d850b85a330a6ae166da80.mockapi.io/TshirtAR/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    private fun load() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getUrl().create(PlayerasApi::class.java).getPlayeras("playera")
            val pl: List<PlayeraModel>? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val playerasModelResponse: List<PlayeraModel> = pl ?: emptyList()
                    playeras.clear()
                    playeras.addAll(playerasModelResponse)
                    adapter.notifyDataSetChanged()
                } else {
                    errorMessage()
                }
            }
        }
    }
    private fun errorMessage() {
        Toast.makeText(this, "Error en conexion API", Toast.LENGTH_SHORT).show()
    }
}