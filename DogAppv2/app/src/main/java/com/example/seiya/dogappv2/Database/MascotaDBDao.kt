package com.example.seiya.dogappv2.Database

import android.arch.persistence.room.*
import com.example.seiya.dogappv2.Mascota



@Dao
interface MascotaDBDao{
    @Query("SELECT * FROM MascotaDB")
    fun loadAllMascotas():List<Mascota>

    @Query("SELECT * FROM MascotaDB WHERE Favorito = 1")
    fun loadFavoriteMascotas():List<Mascota>

    @Query("SELECT * FROM MascotaDB WHERE Raza = :raza")
    fun loadByRaza(raza: String): List<Mascota>

    @Query("DELETE FROM MascotaDB WHERE Nombre = :nombre")
    fun deleteOneDog(nombre: String)

    @Insert
    fun insertMascota(mascota:MascotaDB)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMascotas(mascota: MascotaDB)

    @Delete
    fun deleteMascota(mascota: MascotaDB)

}