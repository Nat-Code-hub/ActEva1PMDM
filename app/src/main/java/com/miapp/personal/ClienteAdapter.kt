package com.miapp.personal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ClienteAdapter - Adaptador para mostrar la lista de clientes en un RecyclerView
 * Permite gestionar eventos de click y long click en cada item
 */
class ClienteAdapter(
    private var listaClientes: List<Cliente>,
    private val onClienteClick: (Cliente) -> Unit,
    private val onClienteLongClick: (Cliente) -> Boolean
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    /**
     * ViewHolder que contiene las referencias a las vistas de cada item
     */
    class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivClienteIcon)
        val tvNombre: TextView = itemView.findViewById(R.id.tvClienteNombre)
        val tvEmail: TextView = itemView.findViewById(R.id.tvClienteEmail)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvClienteTelefono)
    }

    /**
     * Crea una nueva vista para cada item del RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    /**
     * Vincula los datos del cliente con las vistas del ViewHolder
     */
    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = listaClientes[position]

        // Mostrar datos del cliente
        holder.tvNombre.text = cliente.nombre
        holder.tvEmail.text = cliente.email
        holder.tvTelefono.text = cliente.telefono

        // Click para editar
        holder.itemView.setOnClickListener {
            onClienteClick(cliente)
        }

        // Long click para eliminar
        holder.itemView.setOnLongClickListener {
            onClienteLongClick(cliente)
        }
    }

    /**
     * Retorna el número total de items en la lista
     */
    override fun getItemCount(): Int = listaClientes.size

    /**
     * Actualiza la lista de clientes y refresca el RecyclerView
     * @param nuevaLista Nueva lista de clientes a mostrar
     */
    fun actualizarLista(nuevaLista: List<Cliente>) {
        listaClientes = nuevaLista
        notifyDataSetChanged()
    }
}