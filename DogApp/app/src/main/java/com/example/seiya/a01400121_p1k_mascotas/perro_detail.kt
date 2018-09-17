package com.example.seiya.a01400121_p1k_mascotas

/**
 * Clase: perro_detail.kt
 * Descripción:
 *      Detail es la vista que se encarga de presentar la información completa de las vistas,
 *      jala los datos del Intent del MainActivity.kt y se maneja con un Cardview (perro_item)
 * Autor: Miguel Angel Alvarado López, A01400121
 * Fecha de creación: 13/09/2018
 * Fecha de última modificación: 16/09/2018
 */

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_perro_detail.*
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


class perro_detail : AppCompatActivity() {

    private lateinit var perro: Perro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perro_detail)


        val extras = intent.extras?: return

        /*
        val titulo = extras.getString(MainActivity.TITULO_KEY)
        val imagen = extras.getInt(MainActivity.ID_IMAGEN_KEY)
        val raza = extras.getString(MainActivity.RAZA_KEY)
        val lugar = extras.getString(MainActivity.LUGAR_KEY)
        val correo = extras.getString(MainActivity.CORREO_KEY)
        val telefono = extras.getString(MainActivity.TELEFONO_KEY)
        val fecha = extras.getString(MainActivity.FECHA_KEY)
        //val favorito = extras.getBoolean(MainActivity.FAVORITO_KEY)


        val imagenPerro = getDrawable(imagen)
        iv_detail_Foto.setImageDrawable(imagenPerro)
        */

        perro = extras.getSerializable(MainActivity.PERRO_KEY) as Perro

        tv_detail_Nombre.text = perro.Nombre
        tv_detail_Raza.text = perro.Raza
        tv_detail_Lugar.text = perro.LugarExtravio
        tv_detail_Correo.text = perro.CorreoElectronico
        tv_detail_Telefono.text = perro.Telefono
        tv_detail_FechaRegistro.text = perro.FechaPublicacion
        tg_detail_Favorite.isChecked = perro.Favorito


        if (perro.idImagen == 0) {
            val byteArray = perro.imagenTemporal!!
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            iv_detail_Foto.setImageBitmap(bmp)
        }
        else{
            iv_detail_Foto.setImageResource(perro.idImagen)
        }


        tg_detail_Favorite.setOnClickListener(View.OnClickListener {
            //tg_detail_Favorite.toggle()
            perro.Favorito = !perro.Favorito
        })



    }


    override fun onBackPressed() {

        val intent = Intent().apply {
            putExtra(MainActivity.PERRO_KEY, perro)
        }

        setResult(Activity.RESULT_OK, intent)
        //finish()

        super.onBackPressed()

    }


}
