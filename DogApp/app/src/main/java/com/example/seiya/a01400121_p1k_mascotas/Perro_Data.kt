package com.example.seiya.a01400121_p1k_mascotas

/**
 * Clase: Perro_Data.kt
 * Descripción:
 *      Información Dummy para la actividad principal, vienen con valores por default.
 * Autor: Miguel Angel Alvarado López, A01400121
 * Fecha de creación: 13/09/2018
 * Fecha de última modificación: 16/09/2018
 */

class Perro_Data {

    var ListaPerros: ArrayList<Perro> = ArrayList()

    init {
        dataList()
    }

    fun dataList(){
        ListaPerros.add(Perro("Milou", "Wire Fox Terrier", "14/06/96", "MikeAlvaradoL06@gmail.com", "My House", "4492058964", R.drawable.imgmilou, true))
        ListaPerros.add(Perro("Snoopy", "Beagle", "14/06/96", "MikeAlvaradoL06@gmail.com", "My House", "4492058964", R.drawable.imgsnoopy, false))
        ListaPerros.add(Perro("Scooby Doo", "Grán Danés", "14/06/96", "MikeAlvaradoL06@gmail.com", "My House", "4492058964", R.drawable.imgscooby, true))
        ListaPerros.add(Perro("Clifford", "Labrador/Giant Vizla", "14/06/96", "MikeAlvaradoL06@gmail.com", "My House", "4492058964", R.drawable.imgclifford, false))
    }
}