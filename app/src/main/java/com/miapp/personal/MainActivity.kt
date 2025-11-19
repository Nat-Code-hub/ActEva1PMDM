package com.miapp.personal

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


/**
 * MainActivity - Pantalla principal del CRM
 * Muestra la lista de clientes en un RecyclerView
 * Permite búsqueda dinámica, agregar, editar y eliminar clientes
 */
class MainActivity : AppCompatActivity() {

    // Referencias a las vistas
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAgregarCliente: FloatingActionButton
    private lateinit var etBuscar: TextInputEditText
    private lateinit var tvContador: TextView

    // Adapter y base de datos
    private lateinit var clienteAdapter: ClienteAdapter
    private lateinit var dbHelper: DatabaseHelper

    // Lista de clientes
    private var listaClientes = mutableListOf<Cliente>()

    // SOLO PARA PRUEBAS - Eliminar antes de entregar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar base de datos
        dbHelper = DatabaseHelper(this)



        // Inicializar vistas
        initViews()

        // Configurar RecyclerView
        setupRecyclerView()

        // Cargar clientes desde la base de datos
        cargarClientes()

        // Configurar búsqueda
        setupBusqueda()

        // Configurar botón de agregar
        setupFAB()
    }

    /**
     * Inicializa todas las referencias a las vistas del layout
     */
    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewClientes)
        fabAgregarCliente = findViewById(R.id.fabAgregarCliente)
        etBuscar = findViewById(R.id.etBuscar)
        tvContador = findViewById(R.id.tvContador)

    }

    /**
     * Configura el RecyclerView con su adapter y layout manager
     */
    private fun setupRecyclerView() {
        clienteAdapter = ClienteAdapter(
            listaClientes = listaClientes,
            onClienteClick = { cliente ->
                // Click normal: editar cliente
                editarCliente(cliente)
            },
            onClienteLongClick = { cliente ->
                // Long click: eliminar cliente
                mostrarDialogoEliminar(cliente)
                true
            }
        )

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = clienteAdapter
        }
    }

    /**
     * Carga todos los clientes desde la base de datos
     */
    private fun cargarClientes() {
        listaClientes.clear()
        listaClientes.addAll(dbHelper.obtenerTodosLosClientes())
        clienteAdapter.actualizarLista(listaClientes)
        actualizarContador()
    }

    /**
     * Configura la búsqueda en tiempo real
     */
    private fun setupBusqueda() {
        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                buscarClientes(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    /**
     * Busca clientes por nombre o email
     */
    private fun buscarClientes(query: String) {
        if (query.isEmpty()) {
            // Si no hay búsqueda, mostrar todos
            cargarClientes()
        } else {
            // Buscar en la base de datos
            val resultados = dbHelper.buscarClientes(query)
            listaClientes.clear()
            listaClientes.addAll(resultados)
            clienteAdapter.actualizarLista(listaClientes)
            actualizarContador()
        }
    }

    /**
     * Configura el FloatingActionButton para agregar nuevos clientes

    private fun setupFAB() {
        fabAgregarCliente.setOnClickListener {
            // Navegar al formulario sin datos (modo crear)
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
     }*/

    /**
     * Configura el FloatingActionButton para agregar nuevos clientes
     */
    private fun setupFAB() {
        fabAgregarCliente.setOnClickListener {
            // Navegar al formulario sin datos (modo crear)
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }



    /**
     * Navega al formulario en modo edición
     */
    private fun editarCliente(cliente: Cliente) {
        val intent = Intent(this, FormActivity::class.java).apply {
            putExtra("CLIENTE_ID", cliente.id)
            putExtra("CLIENTE_NOMBRE", cliente.nombre)
            putExtra("CLIENTE_EMAIL", cliente.email)
            putExtra("CLIENTE_TELEFONO", cliente.telefono)
        }
        startActivity(intent)
    }

    /**
     * Muestra un AlertDialog para confirmar la eliminación
     */
    private fun mostrarDialogoEliminar(cliente: Cliente) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Cliente")
            .setMessage("¿Estás seguro de eliminar a ${cliente.nombre}?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Eliminar") { dialog, _ ->
                eliminarCliente(cliente)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    /**
     * Elimina un cliente de la base de datos
     */
    private fun eliminarCliente(cliente: Cliente) {
        val resultado = dbHelper.eliminarCliente(cliente.id)

        if (resultado > 0) {
            Toast.makeText(this, "Cliente eliminado correctamente", Toast.LENGTH_SHORT).show()
            cargarClientes() // Recargar la lista
        } else {
            Toast.makeText(this, "Error al eliminar cliente", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Actualiza el contador de clientes
     */
    private fun actualizarContador() {
        val total = listaClientes.size
        tvContador.text = "Total de clientes: $total"
    }

    /**
     * Recarga los clientes cuando se vuelve a esta actividad
     */
    override fun onResume() {
        super.onResume()
        cargarClientes()
    }
}