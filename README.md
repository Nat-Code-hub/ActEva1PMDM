# Mi Perfil Personal - Aplicación Android

Aplicación Android desarrollada en Kotlin que permite gestionar información personal del usuario a través de múltiples pantallas con navegación fluida, formularios con validaciones robustas y una interfaz moderna con Material Design.

## 📱 Características

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

## 🏗️ Arquitectura

### Activities

1. **MainActivity**: Pantalla de bienvenida con navegación a las demás actividades
2. **ProfileActivity**: Visualización de la información personal del usuario
3. **FormActivity**: Formulario para ingresar/editar datos personales con validaciones

### Layouts

- **activity_main.xml**: Pantalla principal con tarjeta centrada y botones de navegación
- **activity_profile.xml**: Diseño de perfil con avatar circular, campos informativos y barra superior colorida
- **activity_form.xml**: Formulario con TextInputLayouts de Material Design y botones de acción

## 🎨 Diseño

### Colores Personalizados
```xml
Primary: #6200EA (Morado vibrante)
Primary Dark: #3700B3
Accent: #03DAC5 (Turquesa)
Background: #F5F5F5 (Gris claro)
Error: #B00020 (Rojo)
```

### Estilos Personalizados

- **ButtonPrimary**: Botones con bordes redondeados y iconos
- **InputField**: Campos de texto con estilo outlined de Material Design
- **TitleText**: Títulos destacados en 24sp
- **SubtitleText**: Texto secundario en 16sp

### Recursos Visuales

- Avatar circular con fondo de color primario
- Iconos de Material Design integrados
- Tarjetas elevadas con sombras (CardView)
- Divisores visuales entre secciones

## 📋 Requisitos Técnicos

- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 35
- **Compile SDK**: API 35
- **Lenguaje**: Kotlin 2.0.21
- **Build System**: Gradle 8.7.3

### Dependencias
```kotlin
androidx.core:core-ktx
androidx.appcompat:appcompat
com.google.android.material:material
androidx.constraintlayout:constraintlayout
androidx.activity:activity-ktx
```

## 🚀 Instalación y Ejecución

### Opción 1: Android Studio

1. Clona o descarga este repositorio
2. Abre el proyecto en Android Studio
3. Espera a que Gradle sincronice las dependencias
4. Conecta un dispositivo físico o inicia un emulador
5. Click en el botón **Run** ▶️ (o Shift+F10)

### Opción 2: Generar APK

1. En Android Studio: **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. El APK se generará en: `app/build/outputs/apk/debug/`
3. Transfiere el APK a tu dispositivo Android
4. Instala la aplicación (habilita "Instalar desde fuentes desconocidas" si es necesario)

### Opción 3: Dispositivo Físico (Recomendado)

1. **Habilita las Opciones de Desarrollador** en tu móvil:
   - Ve a Ajustes → Acerca del teléfono
   - Toca 7 veces en "Número de compilación"
2. **Activa Depuración USB**:
   - Ajustes → Opciones de desarrollador → Depuración USB
3. Conecta tu móvil al PC con cable USB
4. Acepta el mensaje de depuración USB en tu móvil
5. En Android Studio, selecciona tu dispositivo en el desplegable
6. Click en **Run** ▶️

## 🧪 Validaciones Implementadas

### Campo Nombre
- ✅ No puede estar vacío
- ✅ Mínimo 3 caracteres

### Campo Email
- ✅ No puede estar vacío
- ✅ Debe tener formato válido de email (usuario@dominio.extensión)
- ✅ Validación mediante `Patterns.EMAIL_ADDRESS`

### Campo Teléfono
- ✅ No puede estar vacío
- ✅ Mínimo 9 dígitos
- ✅ Solo números (sin letras ni caracteres especiales)
- ✅ Validación mediante expresión regular

### Campo Biografía
- ✅ Opcional (puede dejarse vacío)
- ✅ Si se completa, mínimo 10 caracteres

### Feedback Visual
- ❌ **Errores**: Mensajes rojos debajo de cada campo con descripción específica
- ✅ **Éxito**: Toast verde y mensaje de confirmación al guardar
- 🔄 **Limpieza**: Opción para limpiar el formulario completo

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
## 📸 Capturas de Pantalla

El flujo visual de la aplicación, mostrando las tres pantallas principales y sus interacciones.

| Pantalla Principal | Formulario de Edición |
| :---: | :---: |
| <img width="378" alt="PantallaPrincipal" src="https://github.com/user-attachments/assets/5b8a7c14-7db9-42d1-9dab-9b6b57a54caa" /> | <img width="377" alt="FormularioDeEdicion" src="https://github.com/user-attachments/assets/b86773f2-3384-450d-a7af-b4bce058178c" /> |
| Pantalla Principal | Vista de Perfil |
|       |       |
| <img width="378" alt="PantallaPrincipal" src="https://github.com/user-attachments/assets/5b8a7c14-7db9-42d1-9dab-9b6b57a54caa" /> | <img width="377" alt="Perfil" src="https://github.com/user-attachments/assets/60924f1b-5c13-4b15-beb3-ece9a577d9f6" /> |

## 💾 Paso de Datos

Los datos se pasan entre Activities mediante **Intents** con extras:
```kotlin
intent.putExtra("USER_NAME", nombre)
intent.putExtra("USER_EMAIL", email)
intent.putExtra("USER_PHONE", telefono)
intent.putExtra("USER_BIO", biografia)
```

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

## 👨‍💻 Autor

Desarrollado como proyecto de práctica para aprender desarrollo Android con Kotlin.

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.

---

**Versión**: 1.0 
