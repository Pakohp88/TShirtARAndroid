package dgtic.unam.tshirtar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dgtic.unam.tshirtar.Models.UsuarioModel
import dgtic.unam.tshirtar.Services.UsuariosApi
import dgtic.unam.tshirtar.databinding.ActivityRegistroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://63d850b85a330a6ae166da80.mockapi.io/TshirtAR/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun registrarUsuario(view: View) {
        var nombre = binding.nombre.text
        var email = binding.correo.text
        var contrasena = binding.contrasena.text
        var contrasenaConfirmacion = binding.confirmarContrasena.text


        if (nombre.isEmpty() || nombre.isBlank()) {
            binding.nombre.error = getString(R.string.valorInvalido)
            return
        }

        if (email.isEmpty() || email.isBlank()) {
            binding.correo.error = getString(R.string.valorInvalido)
            return
        }

        if (contrasena.isEmpty() || contrasena.isBlank()) {
            binding.contrasena.error = getString(R.string.valorInvalido)
            return
        }

        if (contrasenaConfirmacion.isEmpty() || contrasenaConfirmacion.isBlank()) {
            binding.confirmarContrasena.error = getString(R.string.valorInvalido)
            return
        }

        if (contrasena.equals(contrasenaConfirmacion)) {
            binding.contrasena.error = getString(R.string.contrasenaDiferente)
            binding.confirmarContrasena.error = getString(R.string.contrasenaDiferente)
            return
        }

        var usuario = UsuarioModel(
            id = null,
            nombreCompleto = nombre.toString(),
            email = email.toString(),
            contrasena = contrasena.toString()
        )
        addUser(usuario){
            if (it?.nombreCompleto != null) {
                Toast.makeText(this@RegistroActivity, "Creación exitosa", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegistroActivity, InicioActivity::class.java)
                startActivity(intent)
            } else {
                binding.msjErrorRegistro.text = getString(R.string.datosIncorrectos)
                binding.msjErrorRegistro.setVisibility(View.VISIBLE)
            }
        }
    }

    private fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    fun addUser(userData: UsuarioModel, onResult: (UsuarioModel?) -> Unit){
        val retrofit = buildService(UsuariosApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<UsuarioModel> {
                override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UsuarioModel>, response: Response<UsuarioModel>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }




}