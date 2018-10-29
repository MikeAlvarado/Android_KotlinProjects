package com.example.seiya.dogappv2

import android.graphics.Bitmap
import java.io.Serializable

data class Mascota(
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

