package com.miapp.personal

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * FormActivity - Formulario para crear o editar clientes
 * Incluye validaciones completas para todos los campos
 * Guarda los datos en SQLite
 */
class FormActivity : AppCompatActivity() {

    // Referencias a las vistas
    private lateinit var tilNombre: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilTelefono: TextInputLayout

    private lateinit var etNombre: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etTelefono: TextInputEditText

    private lateinit var tvFeedback: TextView
    private lateinit var btnGuardar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnVolver: Button

    // Base de datos
    private lateinit var dbHelper: DatabaseHelper

    // Variables para modo edición
    private var clienteId: Int = -1
    private var modoEdicion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        // Inicializar base de datos
        dbHelper = DatabaseHelper(this)

        // Inicializar vistas
        initViews()

        // Verificar si es modo edición
        verificarModoEdicion()

        // Configurar botones
        setupButtons()
    }

    /**
     * Inicializa todas las referencias a las vistas del layout
     */
    private fun initViews() {
        // TextInputLayouts
        tilNombre = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        tilTelefono = findViewById(R.id.tilPhone)

        // EditTexts
        etNombre = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etTelefono = findViewById(R.id.etPhone)

        // Otros elementos
        tvFeedback = findViewById(R.id.tvFeedback)
        btnGuardar = findViewById(R.id.btnSave)
        btnLimpiar = findViewById(R.id.btnClear)
        btnVolver = findViewById(R.id.btnBack)
    }

    /**
     * Verifica si se está editando un cliente existente
     */
    private fun verificarModoEdicion() {
        clienteId = intent.getIntExtra("CLIENTE_ID", -1)

        if (clienteId != -1) {
            // Modo edición
            modoEdicion = true
            btnGuardar.text = "Actualizar Cliente"

            // Cargar datos del cliente
            val nombre = intent.getStringExtra("CLIENTE_NOMBRE") ?: ""
            val email = intent.getStringExtra("CLIENTE_EMAIL") ?: ""
            val telefono = intent.getStringExtra("CLIENTE_TELEFONO") ?: ""

            etNombre.setText(nombre)
            etEmail.setText(email)
            etTelefono.setText(telefono)
        } else {
            // Modo creación
            modoEdicion = false
            btnGuardar.text = "Guardar Cliente"
        }
    }

    /**
     * Configura los listeners de todos los botones
     */
    private fun setupButtons() {
        // Botón Guardar/Actualizar
        btnGuardar.setOnClickListener {
            if (validarFormulario()) {
                guardarCliente()
            }
        }

        // Botón Limpiar
        btnLimpiar.setOnClickListener {
            limpiarFormulario()
            Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_SHORT).show()
        }

        // Botón Volver
        btnVolver.setOnClickListener {
            finish()
        }
    }

    /**
     * Valida todos los campos del formulario
     * @return true si todos los campos son válidos
     */
    private fun validarFormulario(): Boolean {
        // Limpiar errores previos
        limpiarErrores()

        var isValid = true

        // Validar Nombre
        val nombre = etNombre.text.toString().trim()
        if (nombre.isEmpty()) {
            tilNombre.error = "El nombre no puede estar vacío"
            isValid = false
        } else if (nombre.length < 3) {
            tilNombre.error = "El nombre debe tener al menos 3 caracteres"
            isValid = false
        }

        // Validar Email
        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = "El email no puede estar vacío"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Email inválido"
            isValid = false
        }

        // Validar Teléfono
        val telefono = etTelefono.text.toString().trim()
        if (telefono.isEmpty()) {
            tilTelefono.error = "El teléfono no puede estar vacío"
            isValid = false
        } else if (telefono.length < 9) {
            tilTelefono.error = "El teléfono debe tener al menos 9 dígitos"
            isValid = false
        } else if (!telefono.matches(Regex("^[0-9]+$"))) {
            tilTelefono.error = "El teléfono solo debe contener números"
            isValid = false
        }

        // Mostrar feedback visual si hay errores
        if (!isValid) {
            mostrarFeedback("Por favor corrige los errores", false)
        }

        return isValid
    }

    /**
     * Limpia todos los mensajes de error de los campos
     */
    private fun limpiarErrores() {
        tilNombre.error = null
        tilEmail.error = null
        tilTelefono.error = null
    }

    /**
     * Guarda o actualiza el cliente en la base de datos
     */
    private fun guardarCliente() {
        // Obtener valores de los campos
        val nombre = etNombre.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val telefono = etTelefono.text.toString().trim()

        if (modoEdicion) {
            // Actualizar cliente existente
            val cliente = Cliente(
                id = clienteId,
                nombre = nombre,
                email = email,
                telefono = telefono
            )

            val resultado = dbHelper.actualizarCliente(cliente)

            if (resultado > 0) {
                Toast.makeText(this, "✓ Cliente actualizado correctamente", Toast.LENGTH_LONG).show()
                mostrarFeedback("Cliente actualizado correctamente", true)

                // Volver a MainActivity después de 1 segundo
                tvFeedback.postDelayed({
                    finish()
                }, 1000)
            } else {
                Toast.makeText(this, "Error al actualizar cliente", Toast.LENGTH_SHORT).show()
                mostrarFeedback("Error al actualizar cliente", false)
            }

        } else {
            // Crear nuevo cliente
            val cliente = Cliente(
                nombre = nombre,
                email = email,
                telefono = telefono
            )

            val resultado = dbHelper.insertarCliente(cliente)

            if (resultado != -1L) {
                Toast.makeText(this, "✓ Cliente guardado correctamente", Toast.LENGTH_LONG).show()
                mostrarFeedback("Cliente guardado correctamente", true)

                // Limpiar formulario y preparar para agregar otro
                limpiarFormulario()

                // Opcional: cerrar después de 2 segundos
                tvFeedback.postDelayed({
                    finish()
                }, 2000)
            } else {
                Toast.makeText(this, "Error al guardar cliente", Toast.LENGTH_SHORT).show()
                mostrarFeedback("Error: Es posible que el email ya exista", false)
            }
        }
    }

    /**
     * Limpia todos los campos del formulario
     */
    private fun limpiarFormulario() {
        etNombre.text?.clear()
        etEmail.text?.clear()
        etTelefono.text?.clear()
        limpiarErrores()
        tvFeedback.visibility = View.GONE
    }

    /**
     * Muestra un mensaje de feedback al usuario
     * @param message Mensaje a mostrar
     * @param isSuccess true para éxito (verde), false para error (rojo)
     */
    private fun mostrarFeedback(message: String, isSuccess: Boolean) {
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