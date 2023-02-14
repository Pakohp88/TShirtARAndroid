package dgtic.unam.tshirtar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import dgtic.unam.tshirtar.Models.UsuarioModel
import dgtic.unam.tshirtar.Services.UsuariosApi
import dgtic.unam.tshirtar.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun verRegistro(view: View) {
        val intent = Intent(this@MainActivity, RegistroActivity::class.java)
        startActivity(intent)
    }

    fun iniciarSesion(view: View) {
        binding.msjError.text = getString(R.string.datosIncorrectos)
        binding.msjError.setVisibility(View.INVISIBLE)

        if(!binding.correo.text.isEmpty() && !binding.contrasena.text.isEmpty() ){
            var correo =binding.correo.text.toString()
            var contrasena = binding.contrasena.text.toString()
            login(correo , contrasena)
        }
        else
        {
            if(binding.correo.text.isEmpty()){
                binding.correo.error = getString(R.string.valorInvalido)
            }

            if(binding.contrasena.text.isEmpty()){
                binding.contrasena.error = getString(R.string.valorInvalido)
            }

        }
    }

    private fun getUrl(): Retrofit {
        return Retrofit.Builder()
        .baseUrl("https://63d850b85a330a6ae166da80.mockapi.io/TshirtAR/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    private fun login(email: String, contrasena: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getUrl().create(UsuariosApi::class.java).getUsuarios("user")
            val pl: List<UsuarioModel>? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val UsuarioModelResponse: List<UsuarioModel> = pl ?: emptyList()
                    var usuario = UsuarioModelResponse.filter { it -> it.email.equals(email) && it.contrasena.equals(contrasena)  }

                    if(usuario.size == 1) {
                        val intent = Intent(this@MainActivity, InicioActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        binding.msjError.text = getString(R.string.datosIncorrectos)
                        binding.msjError.setVisibility(View.VISIBLE)
                    }

                } else {
                    binding.msjError.text = getString(R.string.datosIncorrectos)
                    binding.msjError.setVisibility(View.VISIBLE)
                }
            }
        }
    }
}