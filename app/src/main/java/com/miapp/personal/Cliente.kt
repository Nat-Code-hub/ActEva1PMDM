package com.miapp.personal

/**
 * Clase modelo que representa un Cliente en el CRM
 * @property id Identificador único del cliente (auto-generado por SQLite)
 * @property nombre Nombre completo del cliente
 * @property email Correo electrónico del cliente
 * @property telefono Número de teléfono del cliente
 */
data class Cliente(
    val id: Int = 0,
    val nombre: String,
    val email: String,
    val telefono: String
)