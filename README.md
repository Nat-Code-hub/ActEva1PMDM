# 📱 Gestor de Clientes - Aplicación CRM

**Alumno:** Natalia Chuquillanqui 
**Curso:** 2º DAM - Programación Multimedia y Dispositivos Móviles  
**Proyecto:** Actividad Evaluable 2

---

## 📋 Descripción del Proyecto

Aplicación móvil Android de gestión de clientes (CRM) desarrollada en **Kotlin** con **Android Studio**.

La aplicación permite **registrar, buscar, editar y eliminar clientes** de forma persistente utilizando una base de datos **SQLite local**.

---

## ✨ Funcionalidades Principales

### ✅ CRUD Completo
- **Crear:** Agregar nuevos clientes mediante formulario validado
- **Leer:** Visualizar lista completa de clientes en RecyclerView
- **Actualizar:** Editar información de clientes existentes (click)
- **Eliminar:** Borrar clientes con confirmación (long click)

### 🔍 Búsqueda Dinámica
- Filtrado en tiempo real por nombre o email
- Actualización instantánea de resultados

### ✔️ Validaciones Robustas
- Campos obligatorios (nombre, email, teléfono)
- Formato de email correcto
- Teléfono con mínimo 9 dígitos
- Solo números en teléfono

### 🎨 Interfaz Profesional
- Material Design 3
- RecyclerView con diseño de tarjetas (CardView)
- FloatingActionButton para agregar clientes
- AlertDialog para confirmación de eliminación
- Contador total de clientes

---

## 🗄️ Modelo de Datos

### Tabla: `clientes`

| Campo      | Tipo    | Descripción                          |
|------------|---------|--------------------------------------|
| `id`       | INTEGER | Clave primaria (auto-incremental)    |
| `nombre`   | TEXT    | Nombre completo del cliente          |
| `email`    | TEXT    | Correo electrónico (único)           |
| `telefono` | TEXT    | Número de teléfono                   |

### Clase Kotlin: `Cliente`
```kotlin
data class Cliente(
    val id: Int = 0,
    val nombre: String,
    val email: String,
    val telefono: String
)
```

---

## 🏗️ Arquitectura del Proyecto

### **Estructura de Archivos:**
```
com.miapp.personal/
├── Cliente.kt              # Modelo de datos
├── DatabaseHelper.kt       # Gestor de SQLite (CRUD)
├── ClienteAdapter.kt       # Adaptador del RecyclerView
├── MainActivity.kt         # Pantalla principal (lista)
├── FormActivity.kt         # Formulario crear/editar
│
res/layout/
├── activity_main.xml       # Layout principal
├── activity_form.xml       # Layout del formulario
├── item_cliente.xml        # Layout de cada item
│
res/values/
├── strings.xml             # Textos de la app
├── colors.xml              # Paleta de colores
└── themes.xml              # Estilos y temas
```

---

## 📸 Capturas de Pantalla

### 1. Pantalla Principal (Lista vacía)
> Muestra la pantalla inicial con el buscador y el botón flotante para agregar clientes.

Multimedia/screenshots/Primero.png
---

### 2. Formulario de Nuevo Cliente
> Formulario con validaciones para agregar un cliente nuevo.

Multimedia/screenshots/Segundo.png


---

### 3. Cliente Agregado
> Lista mostrando el cliente recién agregado.

Multimedia/screenshots/Tercero.png

---

### 4. Lista con Múltiples Clientes
> RecyclerView mostrando varios clientes con scroll funcional.

Multimedia/screenshots/Cuarto.png

---

### 5. Búsqueda Dinámica
> Filtrado en tiempo real al escribir "Isab" en el buscador.

Multimedia/screenshots/Noveno.png

---

### 6. Selección para Editar
> Cliente seleccionado para editar sus datos.

Multimedia/screenshots/Cuarto.png

---

### 7. Edición de Cliente
> Formulario en modo edición mostrando "Actualizar Cliente".

Multimedia/screenshots/Quinto.png

---

### 8. Cliente Actualizado
> Lista actualizada mostrando el cambio de nombre.

Multimedia/screenshots/Sexto.png

---

### 9. Confirmación de Eliminación
> AlertDialog solicitando confirmación antes de eliminar.

Multimedia/screenshots/Septimo.png

---

### 10. Cliente Eliminado
> Lista actualizada tras eliminar un cliente (contador reduce).

Multimedia/screenshots/Octavo.png

---

### 11. Vista Final
> Vista general de la aplicación con múltiples clientes. Se arreglo la parte de arriba.

Multimedia/screenshots/Decimo.png

---

## 🚀 Instrucciones de Instalación

### **Requisitos:**
- Android Studio Hedgehog | 2023.1.1 o superior
- SDK mínimo: API 24 (Android 7.0)
- SDK objetivo: API 34 (Android 14)
- Kotlin 1.9+

### **Pasos para ejecutar:**

1. **Clonar el repositorio:**
```bash
   git clone [URL_DEL_REPOSITORIO]
```

2. **Abrir en Android Studio:**
    - File → Open
    - Seleccionar la carpeta del proyecto

3. **Sincronizar Gradle:**
    - Esperar a que se descarguen las dependencias

4. **Ejecutar la aplicación:**
    - Conectar dispositivo físico o iniciar emulador
    - Click en Run ▶️ (Shift + F10)

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **IDE:** Android Studio
- **Base de Datos:** SQLite (SQLiteOpenHelper)
- **UI Components:**
    - RecyclerView
    - Material Design 3
    - FloatingActionButton
    - TextInputLayout
    - AlertDialog
- **Arquitectura:** Patrón MVC simplificado
- **Control de Versiones:** Git

---

## 📝 Características Técnicas Destacadas

### **Base de Datos:**
- Implementación de `SQLiteOpenHelper`
- CRUD completo con métodos optimizados
- Búsqueda con consultas SQL (`LIKE`)
- Manejo de transacciones seguras

### **RecyclerView:**
- Adapter personalizado con ViewHolder
- Eventos de click y long click
- Actualización dinámica de datos

### **Validaciones:**
- Uso de `Patterns.EMAIL_ADDRESS` para emails
- Validación de longitud mínima
- Expresiones regulares para teléfonos
- Feedback visual con `TextInputLayout.error`

### **Persistencia:**
- Datos almacenados localmente en SQLite
- Carga automática al iniciar la app
- Sin pérdida de información al cerrar

---

## 🎯 Cumplimiento de Requisitos

| Criterio                          | Puntos | Estado |
|-----------------------------------|--------|--------|
| Diseño y estructura (Layouts XML) | 10     | ✅     |
| RecyclerView funcional            | 15     | ✅     |
| CRUD completo en SQLite           | 20     | ✅     |
| Validación de formularios         | 10     | ✅     |
| Búsqueda dinámica                 | 10     | ✅     |
| Gestión de errores (AlertDialog)  | 5      | ✅     |
| Base de datos optimizada          | 10     | ✅     |
| Código limpio y comentado         | 10     | ✅     |
| Documentación (README)            | 5      | ✅     |
| Extras/Profesionalización         | 5      | ✅     |
| **TOTAL**                         | **100**| **✅** |

---

## 📌 Extras Implementados

- ✅ Búsqueda en tiempo real (sin botón)
- ✅ Contador total de clientes
- ✅ Diseño profesional con Material Design 3
- ✅ Confirmación antes de eliminar (AlertDialog)
- ✅ Feedback visual con Toast y TextViews
- ✅ Código altamente comentado
- ✅ Manejo de estados (modo crear/editar)

---

## 👨‍💻 Autor

**[Tu Nombre]**  
Estudiante de 2º DAM  
[Tu Email] (opcional)  
[Tu GitHub] (opcional)

---

## 📄 Licencia

Este proyecto es de carácter académico para la asignatura de Programación Multimedia y Dispositivos Móviles.

---

## 🙏 Agradecimientos

- Profesor/a de PMDM por la guía y recursos proporcionados
- Documentación oficial de Android Developers
- Material Design Guidelines

---

**Fecha de entrega:** [18/11/2025]  
**Versión:** 1.0