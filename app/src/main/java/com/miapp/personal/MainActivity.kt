package com.miapp.personal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity - Pantalla principal de la aplicación
 * Muestra dos botones para navegar a otras actividades:
 * - Ver perfil
 * - Ir al formulario de actualización
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencia a los botones del layout
        val btnGoToProfile = findViewById<Button>(R.id.btnGoToProfile)
        val btnGoToForm = findViewById<Button>(R.id.btnGoToForm)

        // Configurar navegación al perfil
        btnGoToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Configurar navegación al formulario
        btnGoToForm.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}