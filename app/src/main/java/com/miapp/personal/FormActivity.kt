package com.miapp.personal

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.content.Context

/**
 * FormActivity - Formulario para ingresar/editar información personal
 * Incluye validaciones robustas para todos los campos
 * Muestra feedback visual al usuario (Toast y TextView)
 */
class FormActivity : AppCompatActivity() {

    // Referencias a las vistas
    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var tilBio: TextInputLayout

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etBio: TextInputEditText

    private lateinit var tvFeedback: TextView
    private lateinit var btnSave: Button
    private lateinit var btnClear: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        // Inicializar todas las vistas
        initViews()

        // Cargar datos si vienen del perfil (modo edición)
        loadExistingData()

        // Configurar listeners de botones
        setupButtons()
    }

    /**
     * Inicializa todas las referencias a las vistas del layout
     */
    private fun initViews() {
        // TextInputLayouts
        tilName = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPhone = findViewById(R.id.tilPhone)
        tilBio = findViewById(R.id.tilBio)

        // EditTexts
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etBio = findViewById(R.id.etBio)

        // Otros elementos
        tvFeedback = findViewById(R.id.tvFeedback)
        btnSave = findViewById(R.id.btnSave)
        btnClear = findViewById(R.id.btnClear)
        btnBack = findViewById(R.id.btnBack)
    }

    /**
     * Carga datos existentes si vienen del ProfileActivity (modo edición)
     */
    private fun loadExistingData() {
        intent.getStringExtra("USER_NAME")?.let {
            if (it != "Sin información") etName.setText(it)
        }
        intent.getStringExtra("USER_EMAIL")?.let {
            if (it != "Sin información") etEmail.setText(it)
        }
        intent.getStringExtra("USER_PHONE")?.let {
            if (it != "Sin información") etPhone.setText(it)
        }
        intent.getStringExtra("USER_BIO")?.let {
            if (it != "Sin información") etBio.setText(it)
        }
    }

    /**
     * Configura los listeners de todos los botones
     */
    private fun setupButtons() {
        // Botón Guardar
        btnSave.setOnClickListener {
            if (validateForm()) {
                saveAndNavigate()
            }
        }

        // Botón Limpiar
        btnClear.setOnClickListener {
            clearForm()
            Toast.makeText(this, getString(R.string.form_cleared), Toast.LENGTH_SHORT).show()
        }

        // Botón Volver
        btnBack.setOnClickListener {
            finish()
        }
    }

    /**
     * Valida todos los campos del formulario
     * Muestra mensajes de error específicos para cada campo
     * @return true si todos los campos son válidos
     */
    private fun validateForm(): Boolean {
        // Limpiar errores previos
        clearErrors()

        var isValid = true

        // Validar Nombre
        val name = etName.text.toString().trim()
        if (name.isEmpty()) {
            tilName.error = getString(R.string.error_empty_name)
            isValid = false
        } else if (name.length < 3) {
            tilName.error = "El nombre debe tener al menos 3 caracteres"
            isValid = false
        }

        // Validar Email
        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = getString(R.string.error_empty_email)
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = getString(R.string.error_invalid_email)
            isValid = false
        }

        // Validar Teléfono
        val phone = etPhone.text.toString().trim()
        if (phone.isEmpty()) {
            tilPhone.error = getString(R.string.error_empty_phone)
            isValid = false
        } else if (phone.length < 9) {
            tilPhone.error = getString(R.string.error_invalid_phone)
            isValid = false
        } else if (!phone.matches(Regex("^[0-9]+$"))) {
            tilPhone.error = "El teléfono solo debe contener números"
            isValid = false
        }

        // Validar Biografía (opcional pero con mínimo si se llena)
        val bio = etBio.text.toString().trim()
        if (bio.isNotEmpty() && bio.length < 10) {
            tilBio.error = "La biografía debe tener al menos 10 caracteres"
            isValid = false
        }

        // Mostrar feedback visual
        if (!isValid) {
            showFeedback("Por favor corrige los errores", false)
        }

        return isValid
    }

    /**
     * Limpia todos los mensajes de error de los campos
     */
    private fun clearErrors() {
        tilName.error = null
        tilEmail.error = null
        tilPhone.error = null
        tilBio.error = null
    }

    /**
     * Guarda los datos y navega a ProfileActivity
     */
    private fun saveAndNavigate() {
        // Obtener valores de los campos
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val bio = etBio.text.toString().trim().ifEmpty { "Sin información" }

        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        sharedPref.edit().putString("USER_NAME", name).putString("USER_EMAIL", email).putString("USER_PHONE", phone).putString("USER_BIO", bio).apply()
    //cosa añadida PARA PRUEBA
        Toast.makeText(this, "Guardado: $name", Toast.LENGTH_LONG).show()

        // Mostrar Toast de éxito
        Toast.makeText(this, getString(R.string.success_saved), Toast.LENGTH_LONG).show()

        // Mostrar feedback visual
        showFeedback(getString(R.string.success_saved), true)

        // Navegar al perfil con los datos
        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra("USER_NAME", name)
            putExtra("USER_EMAIL", email)
            putExtra("USER_PHONE", phone)
            putExtra("USER_BIO", bio)
        }

        startActivity(intent)
        finish() // Cerrar el formulario
    }

    /**
     * Limpia todos los campos del formulario
     */
    private fun clearForm() {
        etName.text?.clear()
        etEmail.text?.clear()
        etPhone.text?.clear()
        etBio.text?.clear()
        clearErrors()
        tvFeedback.visibility = View.GONE
    }

    /**
     * Muestra un mensaje de feedback al usuario
     * @param message Mensaje a mostrar
     * @param isSuccess true para éxito (verde), false para error (rojo)
     */
    private fun showFeedback(message: String, isSuccess: Boolean) {
        tvFeedback.text = message
        tvFeedback.visibility = View.VISIBLE

        // Cambiar color según el tipo de mensaje
        if (isSuccess) {
            tvFeedback.setBackgroundColor(getColor(R.color.accent))
        } else {
            tvFeedback.setBackgroundColor(getColor(R.color.error))
        }
    }
}