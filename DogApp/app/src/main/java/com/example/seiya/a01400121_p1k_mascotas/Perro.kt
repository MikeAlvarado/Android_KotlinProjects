package com.example.seiya.a01400121_p1k_mascotas

/**
 * Clase: Perro.kt
 * Descripción:
 *      Clase principal de "Perro", objeto con el cual se maneja la información del programa
 * Autor: Miguel Angel Alvarado López, A01400121
 * Fecha de creación: 13/09/2018
 * Fecha de última modificación: 16/09/2018
 */

import android.graphics.Bitmap
import java.io.Serializable

data class Perro(
        var Nombre: String?,
        var Raza: String?,
        var FechaPublicacion: String?,
        var CorreoElectronico : String?,
        var LugarExtravio : String?,
        var Telefono: String?,
        var idImagen: Int = 0,
        var Favorito : Boolean,
        var imagenTemporal : ByteArray? = null
) : Serializable

