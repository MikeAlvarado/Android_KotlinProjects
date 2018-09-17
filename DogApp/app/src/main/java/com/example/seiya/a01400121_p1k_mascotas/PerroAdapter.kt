package com.example.seiya.a01400121_p1k_mascotas

/**
 * Clase: PerroAdapter.kt
 * Descripción:
 *      Es el adapter que pasa la información a través del MainActivity hacia elementos de la vista
 *      CardView en el perro_item
 * Autor: Miguel Angel Alvarado López, A01400121
 * Fecha de creación: 13/09/2018
 * Fecha de última modificación: 16/09/2018
 */

import android.content.Context
import android.graphics.BitmapFactory
//import android.support.design.resources.MaterialResources.getDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_perro_detail.*

class PerroAdapter(
        context: Context,
        val perros: MutableList<Perro>
) : ArrayAdapter<Perro>(context, 0, perros){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = LayoutInflater.from(context).inflate(R.layout.activity_perro_item, parent, false)
        val perro = getItem(position)

        val tvNombre = rowView.findViewById(R.id.tv_Nombre) as TextView
        val tvRaza = rowView.findViewById(R.id.tv_Raza) as TextView
        val tvLugar = rowView.findViewById(R.id.tv_Lugar) as TextView
        val ivPerro = rowView.findViewById(R.id.iv_Foto) as ImageView
        //val tgFavorito = rowView.findViewById(R.id.tg_Favorite) as ImageButton


        tvNombre.text = perro.Nombre
        tvRaza.text = perro.Raza
        tvLugar.text = perro.LugarExtravio

        if (perro.Favorito == true){
            //tgFavorito.toggle()
        }

        if (perro.idImagen == 0) {
            val byteArray = perro.imagenTemporal!!
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            ivPerro.setImageBitmap(bmp)
        }
        else{
            ivPerro.setImageResource(perro.idImagen)
        }

        return rowView
    }

    fun setPerros(perrosNuevos: List<Perro>){
        perros.clear()
        perros.addAll(perrosNuevos)
        notifyDataSetChanged()
    }
}