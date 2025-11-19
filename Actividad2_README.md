# Mi Perfil Personal - Aplicación Android
# 📱 Gestor de Clientes - Aplicación CRM

Aplicación Android desarrollada en Kotlin que permite gestionar información personal del usuario a través de múltiples pantallas con navegación fluida, formularios con validaciones robustas y una interfaz moderna con Material Design.
**Alumno:** Natalia Chuquillanqui 
**Curso:** 2º DAM - Programación Multimedia y Dispositivos Móviles  
**Proyecto:** Actividad Evaluable 2

## 📱 Características
---

- **Navegación entre Activities**: Tres pantallas principales interconectadas con paso de datos mediante Intents
- **Formularios con validaciones robustas**: 
  - Validación de campos vacíos
  - Validación de formato de email
  - Validación de número de teléfono (mínimo 9 dígitos, solo números)
  - Validación de longitud mínima en biografía
  - Feedback visual inmediato con mensajes de error específicos
- **Interfaz moderna**: Diseño con Material Design 3, colores personalizados y componentes modernos
- **Feedback al usuario**: Toast messages y TextView de confirmación
- **Diseño responsive**: Layouts con ConstraintLayout para adaptarse a diferentes tamaños de pantalla
- **Código limpio**: Organizado en clases, bien comentado y siguiendo buenas prácticas de Kotlin
## 📋 Descripción del Proyecto

## 🏗️ Arquitectura
Aplicación móvil Android de gestión de clientes (CRM) desarrollada en **Kotlin** con **Android Studio**.

### Activities
La aplicación permite **registrar, buscar, editar y eliminar clientes** de forma persistente utilizando una base de datos **SQLite local**.

1. **MainActivity**: Pantalla de bienvenida con navegación a las demás actividades
2. **ProfileActivity**: Visualización de la información personal del usuario
3. **FormActivity**: Formulario para ingresar/editar datos personales con validaciones
---

### Layouts
## ✨ Funcionalidades Principales

- **activity_main.xml**: Pantalla principal con tarjeta centrada y botones de navegación
- **activity_profile.xml**: Diseño de perfil con avatar circular, campos informativos y barra superior colorida
- **activity_form.xml**: Formulario con TextInputLayouts de Material Design y botones de acción
### ✅ CRUD Completo
- **Crear:** Agregar nuevos clientes mediante formulario validado
- **Leer:** Visualizar lista completa de clientes en RecyclerView
- **Actualizar:** Editar información de clientes existentes (click)
- **Eliminar:** Borrar clientes con confirmación (long click)

## 🎨 Diseño
### 🔍 Búsqueda Dinámica
- Filtrado en tiempo real por nombre o email
- Actualización instantánea de resultados

### Colores Personalizados
```xml
Primary: #6200EA (Morado vibrante)
Primary Dark: #3700B3
Accent: #03DAC5 (Turquesa)
Background: #F5F5F5 (Gris claro)
Error: #B00020 (Rojo)
```
### ✔️ Validaciones Robustas
- Campos obligatorios (nombre, email, teléfono)
- Formato de email correcto
- Teléfono con mínimo 9 dígitos
- Solo números en teléfono

### Estilos Personalizados
### 🎨 Interfaz Profesional
- Material Design 3
- RecyclerView con diseño de tarjetas (CardView)
- FloatingActionButton para agregar clientes
- AlertDialog para confirmación de eliminación
- Contador total de clientes

- **ButtonPrimary**: Botones con bordes redondeados y iconos
- **InputField**: Campos de texto con estilo outlined de Material Design
- **TitleText**: Títulos destacados en 24sp
- **SubtitleText**: Texto secundario en 16sp

### Recursos Visuales
---

- Avatar circular con fondo de color primario
- Iconos de Material Design integrados
- Tarjetas elevadas con sombras (CardView)
- Divisores visuales entre secciones
## 🗄️ Modelo de Datos

## 📋 Requisitos Técnicos
### Tabla: `clientes`

- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 35
- **Compile SDK**: API 35
- **Lenguaje**: Kotlin 2.0.21
- **Build System**: Gradle 8.7.3
| Campo      | Tipo    | Descripción                          |
|------------|---------|--------------------------------------|
| `id`       | INTEGER | Clave primaria (auto-incremental)    |
| `nombre`   | TEXT    | Nombre completo del cliente          |
| `email`    | TEXT    | Correo electrónico (único)           |
| `telefono` | TEXT    | Número de teléfono                   |

### Dependencias
### Clase Kotlin: `Cliente`
```kotlin
androidx.core:core-ktx
androidx.appcompat:appcompat
com.google.android.material:material
androidx.constraintlayout:constraintlayout
androidx.activity:activity-ktx
data class Cliente(
    val id: Int = 0,
    val nombre: String,
    val email: String,
    val telefono: String
)
```

## 🚀 Instalación y Ejecución
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

### Opción 1: Android Studio
---

1. Clona o descarga este repositorio
2. Abre el proyecto en Android Studio
3. Espera a que Gradle sincronice las dependencias
4. Conecta un dispositivo físico o inicia un emulador
5. Click en el botón **Run** ▶️ (o Shift+F10)
## 📸 Capturas de Pantalla

### Opción 2: Generar APK
### 1. Pantalla Principal (Lista vacía)
> Muestra la pantalla inicial con el buscador y el botón flotante para agregar clientes.

1. En Android Studio: **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. El APK se generará en: `app/build/outputs/apk/debug/`
3. Transfiere el APK a tu dispositivo Android
4. Instala la aplicación (habilita "Instalar desde fuentes desconocidas" si es necesario)
Multimedia/screenshots/Primero.png
---

### Opción 3: Dispositivo Físico (Recomendado)
### 2. Formulario de Nuevo Cliente
> Formulario con validaciones para agregar un cliente nuevo.

1. **Habilita las Opciones de Desarrollador** en tu móvil:
   - Ve a Ajustes → Acerca del teléfono
   - Toca 7 veces en "Número de compilación"
2. **Activa Depuración USB**:
   - Ajustes → Opciones de desarrollador → Depuración USB
3. Conecta tu móvil al PC con cable USB
4. Acepta el mensaje de depuración USB en tu móvil
5. En Android Studio, selecciona tu dispositivo en el desplegable
6. Click en **Run** ▶️
Multimedia/screenshots/Segundo.png

## 🧪 Validaciones Implementadas

### Campo Nombre
- ✅ No puede estar vacío
- ✅ Mínimo 3 caracteres
---

### Campo Email
- ✅ No puede estar vacío
- ✅ Debe tener formato válido de email (usuario@dominio.extensión)
- ✅ Validación mediante `Patterns.EMAIL_ADDRESS`
### 3. Cliente Agregado
> Lista mostrando el cliente recién agregado.

### Campo Teléfono
- ✅ No puede estar vacío
- ✅ Mínimo 9 dígitos
- ✅ Solo números (sin letras ni caracteres especiales)
- ✅ Validación mediante expresión regular
Multimedia/screenshots/Tercero.png

### Campo Biografía
- ✅ Opcional (puede dejarse vacío)
- ✅ Si se completa, mínimo 10 caracteres
---

### Feedback Visual
- ❌ **Errores**: Mensajes rojos debajo de cada campo con descripción específica
- ✅ **Éxito**: Toast verde y mensaje de confirmación al guardar
- 🔄 **Limpieza**: Opción para limpiar el formulario completo
### 4. Lista con Múltiples Clientes
> RecyclerView mostrando varios clientes con scroll funcional.

## 🔄 Flujo de Navegación
```
MainActivity
    ├─→ Ver Mi Perfil → ProfileActivity
    │                      ├─→ Editar → FormActivity
    │                      └─→ Volver → MainActivity
    └─→ Actualizar Datos → FormActivity
                              ├─→ Guardar → ProfileActivity
                              ├─→ Limpiar (se mantiene en FormActivity)
                              └─→ Volver → MainActivity
```
Multimedia/screenshots/Cuarto.png

## 💾 Paso de Datos
---

Los datos se pasan entre Activities mediante **Intents** con extras:
```kotlin
intent.putExtra("USER_NAME", nombre)
intent.putExtra("USER_EMAIL", email)
intent.putExtra("USER_PHONE", telefono)
intent.putExtra("USER_BIO", biografia)
```
### 5. Búsqueda Dinámica
> Filtrado en tiempo real al escribir "Isab" en el buscador.

## 🛠️ Estructura del Proyecto
```
MiPrimeraApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/miapp/personal/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── ProfileActivity.kt
│   │   │   │   └── FormActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_profile.xml
│   │   │   │   │   └── activity_form.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── drawable/
│   │   │   │       └── circle_background.xml
│   │   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── README.md
Multimedia/screenshots/Noveno.png



## 📸 Capturas de Pantalla

### 1. Pantalla Principal (Lista vacía)
> Muestra la pantalla inicial con el buscador y el botón flotante para agregar clientes.
<img width="247" height="520" alt="image" src="https://github.com/user-attachments/assets/90a7ecd4-1919-49eb-bb02-85870c0b6313" />


### 2. Formulario de Nuevo Cliente
> Formulario con validaciones para agregar un cliente nuevo.

**PEGA AQUÍ: Segundo.png** (o como se llame)



### 3. Cliente Agregado
> Lista mostrando el cliente recién agregado.

**PEGA AQUÍ: Tercero.png** (o como se llame)


### 4. Lista con Múltiples Clientes
> RecyclerView mostrando varios clientes con scroll funcional.

**PEGA AQUÍ: Cuarto.png**



### 5. Búsqueda Dinámica
> Filtrado en tiempo real al escribir "Isab" en el buscador.

**PEGA AQUÍ: Quinto.png**



### 6. Selección para Editar
> Cliente seleccionado para editar sus datos.

**PEGA AQUÍ: Sexto.png**


### 7. Edición de Cliente
> Formulario en modo edición mostrando "Actualizar Cliente".

**PEGA AQUÍ: Septimo.png**



### 8. Cliente Actualizado
> Lista actualizada mostrando el cambio de nombre.

**PEGA AQUÍ: Octavo.png**



### 9. Confirmación de Eliminación
> AlertDialog solicitando confirmación antes de eliminar.

**PEGA AQUÍ: Noveno.png** (o como se llame)


### 10. Cliente Eliminado
> Lista actualizada tras eliminar un cliente (contador reduce).

**PEGA AQUÍ: Decimo.png**



### 11. Vista Final
> Vista general de la aplicación con múltiples clientes. Se arregló la barra superior.

**PEGA AQUÍ: Undecimo.png** (o como se llame tu última captura)


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

## 📝 Código Limpio y Buenas Prácticas

### Organización
- ✅ Código separado en clases específicas por funcionalidad
- ✅ Funciones pequeñas y con responsabilidad única
- ✅ Nombres descriptivos para variables y funciones

### Comentarios
- ✅ KDoc para documentar clases y funciones principales
- ✅ Comentarios inline para lógica compleja
- ✅ Explicaciones de validaciones y flujos

### Kotlin Best Practices
- ✅ Uso de `lateinit` para vistas
- ✅ Expresiones lambda para listeners
- ✅ Null safety con operadores `?` y `?:`
- ✅ String templates para mensajes
- ✅ Scope functions (`let`, `apply`)

### Material Design
- ✅ Componentes oficiales de Material Design 3
- ✅ TextInputLayout para mejores campos de texto
- ✅ MaterialCardView para tarjetas
- ✅ Colores y tipografías siguiendo guías de Material

## 🎯 Extras Implementados

- ✅ **Icono personalizado**: Avatar circular con icono de ubicación
- ✅ **Estilos personalizados**: 4 estilos reutilizables (ButtonPrimary, InputField, TitleText, SubtitleText)
- ✅ **Paleta de colores personalizada**: 8 colores definidos
- ✅ **Mejoras visuales**: 
  - Barra superior colorida en perfil
  - Tarjetas con elevación y sombras
  - Divisores entre secciones
  - Iconos en todos los botones
  - Feedback visual con colores (verde éxito, rojo error)
- ✅ **Animaciones**: Transiciones suaves entre pantallas
- ✅ **ScrollView**: Contenido desplazable para pantallas pequeñas
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

Desarrollado como proyecto de práctica para aprender desarrollo Android con Kotlin.
---

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.
Este proyecto es de carácter académico para la asignatura de Programación Multimedia y Dispositivos Móviles.

---

## 🙏 Agradecimientos

- Profesor/a de PMDM por la guía y recursos proporcionados
- Documentación oficial de Android Developers
- Material Design Guidelines

---

**Versión**: 1.0  
**Última actualización**: Noviembre 2025
**Fecha de entrega:** [19/11/2025]  
