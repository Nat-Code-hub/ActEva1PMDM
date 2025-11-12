package com.miapp.personal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Context

/**
 * ProfileActivity - Muestra la información del perfil del usuario
 * Recibe los datos desde FormActivity mediante Intent
 * Permite editar la información o volver al inicio
 */
class ProfileActivity : AppCompatActivity() {

    // Referencias a las vistas
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvBio: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var btnBackToMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inicializar vistas
        initViews()

        // Cargar datos recibidos del formulario
        loadProfileData()

        // Configurar listeners de botones
        setupButtons()
    }

    /**
     * Inicializa todas las referencias a las vistas del layout
     */
    private fun initViews() {
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvPhone = findViewById(R.id.tvPhone)
        tvBio = findViewById(R.id.tvBio)
        btnEditProfile = findViewById(R.id.btnEditProfile)
        btnBackToMain = findViewById(R.id.btnBackToMain)
    }

    /**
     * Carga los datos enviados desde FormActivity mediante Intent
     * Si no hay datos en Intent, busca en SharedPreferences
     * Si no hay datos, muestra "Sin información"
     */
    private fun loadProfileData() {
        // Obtener SharedPreferences
        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        // Obtener datos del Intent, si no hay, buscar en SharedPreferences
        val name = intent.getStringExtra("USER_NAME")
            ?: sharedPref.getString("USER_NAME", "Sin información")
            ?: "Sin información"

        val email = intent.getStringExtra("USER_EMAIL")
            ?: sharedPref.getString("USER_EMAIL", "Sin información")
            ?: "Sin información"

        val phone = intent.getStringExtra("USER_PHONE")
            ?: sharedPref.getString("USER_PHONE", "Sin información")
            ?: "Sin información"

        val bio = intent.getStringExtra("USER_BIO")
            ?: sharedPref.getString("USER_BIO", "Sin información")
            ?: "Sin información"

        // Mostrar datos en las vistas
        tvName.text = name
        tvEmail.text = email
        tvPhone.text = phone
        tvBio.text = bio
    }

    /**
     * Configura los listeners de los botones
     */
    private fun setupButtons() {
        // Botón Editar - navega al formulario
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            // Pasar datos actuales al formulario para editarlos
            intent.putExtra("USER_NAME", tvName.text.toString())
            intent.putExtra("USER_EMAIL", tvEmail.text.toString())
            intent.putExtra("USER_PHONE", tvPhone.text.toString())
            intent.putExtra("USER_BIO", tvBio.text.toString())
            startActivity(intent)
            finish() // Cerrar esta actividad
        }

        // Botón Volver - regresa a MainActivity
        btnBackToMain.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior
        }
    }
}