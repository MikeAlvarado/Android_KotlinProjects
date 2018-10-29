package com.example.seiya.dogappv2.Database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters


@Entity(tableName = "MascotaDB")
data class MascotaDB (@ColumnInfo(name = "Nombre") var Nombre: String,
    @ColumnInfo(name = "Raza") var Raza: String,
    @ColumnInfo(name = "FechaPublicacion") var FechaPublicacion: String? = null,
    @ColumnInfo(name = "CorreoElectronico") var CorreoElectronico: String? = null,
    @ColumnInfo(name = "LugarExtravio") var LugarExtravio: String? = null,
    @ColumnInfo(name = "Telefono") var Telefono: String? = null,
    @ColumnInfo(name = "idImagen") var idImagen: Int? = null,
    @ColumnInfo(name = "Favorito") var Favorito: Boolean = false,
    @ColumnInfo(name = "imagenTemporal", typeAffinity = ColumnInfo.BLOB) var imagenTemporal: ByteArray? = null){

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var _id:Int = 0
}



    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
