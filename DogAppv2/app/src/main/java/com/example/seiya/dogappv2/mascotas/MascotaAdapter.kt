package com.example.seiya.dogappv2.mascotas

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.seiya.dogappv2.DetailActivity
import com.example.seiya.dogappv2.Mascota
import com.example.seiya.dogappv2.R
import kotlinx.android.synthetic.main.mascota_item_layout.view.*

class MascotaAdapter(var mascotaList:MutableList<Mascota>) :RecyclerView.Adapter<MascotaViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MascotaViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.mascota_item_layout, p0, false)
        return MascotaViewHolder(v)
    }


    override fun getItemCount(): Int {
        return mascotaList.size
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, index: Int) {
        holder.bindMascota(mascotaList[index])
    }
}

class MascotaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    fun bindMascota(mascota: Mascota) = with(itemView){
        itemView.tv_Nombre.text = mascota.Nombre
        itemView.tv_Fecha.text = mascota.FechaPublicacion
        itemView.tv_Lugar.text = mascota.LugarExtravio
        itemView.tv_Raza.text = mascota.Raza
        // itemView.iv_Foto
        if (mascota.imagenTemporal != null) {
            val byteArray = mascota.imagenTemporal!!
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            itemView.iv_Foto.setImageBitmap(bmp)
        }
        else{
            itemView.iv_Foto.setImageResource(mascota.idImagen)
        }

        itemView.setOnClickListener{

                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra("mascota", mascota)
                }
                itemView.context.startActivity(intent)

        }


    }
}