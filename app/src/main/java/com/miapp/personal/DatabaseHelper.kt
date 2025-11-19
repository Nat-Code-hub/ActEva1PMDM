package com.miapp.personal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * DatabaseHelper - Gestiona la base de datos SQLite del CRM
 * Implementa CRUD completo: Create, Read, Update, Delete
 */
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ClientesCRM.db"
        private const val DATABASE_VERSION = 1

        // Nombre de la tabla
        private const val TABLE_CLIENTES = "clientes"

        // Columnas de la tabla
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_TELEFONO = "telefono"
    }

    /**
     * Se ejecuta la primera vez que se crea la base de datos
     * Crea la tabla de clientes con sus columnas
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_CLIENTES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_TELEFONO TEXT NOT NULL
            )
        """.trimIndent()

        db?.execSQL(createTable)
    }

    /**
     * Se ejecuta cuando se actualiza la versión de la base de datos
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CLIENTES")
        onCreate(db)
    }

    /**
     * CREATE - Insertar un nuevo cliente
     * @param cliente Cliente a insertar
     * @return ID del cliente insertado, o -1 si hay error
     */
    fun insertarCliente(cliente: Cliente): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, cliente.nombre)
            put(COLUMN_EMAIL, cliente.email)
            put(COLUMN_TELEFONO, cliente.telefono)
        }

        val id = db.insert(TABLE_CLIENTES, null, values)
        db.close()
        return id
    }

    /**
     * READ - Obtener todos los clientes
     * @return Lista de todos los clientes en la base de datos
     */
    fun obtenerTodosLosClientes(): List<Cliente> {
        val clientes = mutableListOf<Cliente>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_CLIENTES ORDER BY $COLUMN_NOMBRE", null)

        if (cursor.moveToFirst()) {
            do {
                val cliente = Cliente(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    telefono = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO))
                )
                clientes.add(cliente)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return clientes
    }

    /**
     * UPDATE - Actualizar un cliente existente
     * @param cliente Cliente con los datos actualizados (debe tener ID válido)
     * @return Número de filas afectadas
     */
    fun actualizarCliente(cliente: Cliente): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, cliente.nombre)
            put(COLUMN_EMAIL, cliente.email)
            put(COLUMN_TELEFONO, cliente.telefono)
        }

        val rowsAffected = db.update(
            TABLE_CLIENTES,
            values,
            "$COLUMN_ID = ?",
            arrayOf(cliente.id.toString())
        )

        db.close()
        return rowsAffected
    }

    /**
     * DELETE - Eliminar un cliente
     * @param id ID del cliente a eliminar
     * @return Número de filas eliminadas
     */
    fun eliminarCliente(id: Int): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete(
            TABLE_CLIENTES,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )

        db.close()
        return rowsDeleted
    }

    /**
     * BÚSQUEDA - Buscar clientes por nombre o email
     * @param query Texto a buscar
     * @return Lista de clientes que coinciden con la búsqueda
     */
    fun buscarClientes(query: String): List<Cliente> {
        val clientes = mutableListOf<Cliente>()
        val db = readableDatabase

        val cursor = db.rawQuery(
            """
            SELECT * FROM $TABLE_CLIENTES 
            WHERE $COLUMN_NOMBRE LIKE ? OR $COLUMN_EMAIL LIKE ?
            ORDER BY $COLUMN_NOMBRE
            """.trimIndent(),
            arrayOf("%$query%", "%$query%")
        )

        if (cursor.moveToFirst()) {
            do {
                val cliente = Cliente(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    telefono = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONO))
                )
                clientes.add(cliente)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return clientes
    }

    /**
     * Obtener el número total de clientes
     * @return Contador de clientes en la base de datos
     */
    fun contarClientes(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_CLIENTES", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }
}