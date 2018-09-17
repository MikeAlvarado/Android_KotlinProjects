package com.example.seiya.a01400121_p1k_mascotas

/**
 * Clase: perro_add.kt
 * Descripción:
 *      Propia de la vista, es quien se encarga de manejar los datos que le estás agregando al
 *      mutable list de la clase.
 *      Tiene funcionalidad de agregar temporalmente a la lista.
 *      Funciona Camara y pide permisos
 * Autor: Miguel Angel Alvarado López, A01400121
 * Fecha de creación: 16/09/2018
 * Fecha de última modificación: 16/09/2018
 */

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_perro_add.*
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.jar.Manifest

//private lateinit var perro: Perro
private lateinit var thumbnailBitmap : Bitmap

class perro_add : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perro_add)



        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            bt_TomarFoto.isEnabled = false

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 20)
        }

        bt_TomarFoto.setOnClickListener {
            takePhoto()
        }

        fab_SaveInformation.setOnClickListener(){
            addPerro()
        }

        /*
        val perro = Perro("NotMilou", "Wire Fox Terrier", "14/06/96", "MikeAlvaradoL06@gmail.com", "My House", "4492058964", R.drawable.imgmilou, true)

        val intent = Intent().apply {
            putExtra(MainActivity.PERRO_KEY, perro)
        }

        setResult(Activity.RESULT_OK, intent)
        finish()
        */

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 20) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                bt_TomarFoto.isEnabled = true
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var hasThumbnail= false

        when (requestCode) {
            21 -> {
                if (resultCode == Activity.RESULT_OK && data != null){
                    thumbnailBitmap = data.extras.get("data") as Bitmap
                    hasThumbnail = true

                    iv_Foto.setImageBitmap(thumbnailBitmap)
                } else if (resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(applicationContext, "Image Cancelled", Toast.LENGTH_LONG).show()
                }
            }
            else -> {
                Toast.makeText(this, "Unrecognized Text Code", Toast.LENGTH_LONG)
            }
        }

    }

    fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null)
            startActivityForResult(intent, 21)
    }

    fun addPerro(){

        val Fecha = Calendar.getInstance()
        val fDia = Fecha.get(Calendar.DAY_OF_MONTH).toString()
        val fMes = Fecha.get(Calendar.MONTH)+1
        val fsMes = fMes.toString()
        val fAno = Fecha.get(Calendar.YEAR).toString()

        val stream = ByteArrayOutputStream()
        thumbnailBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val perro = Perro(
                Nombre = et_Nombre.text.toString(),
                Raza = et_Raza.text.toString(),
                FechaPublicacion = "$fDia/$fsMes/$fAno",
                CorreoElectronico = et_Correo.text.toString(),
                LugarExtravio = et_Lugar.text.toString(),
                Telefono = et_Telefono.text.toString(),
                idImagen = 0,
                Favorito = false,
                imagenTemporal = stream.toByteArray()
        )


        val intent = Intent().apply {
            putExtra(MainActivity.PERRO_KEY, perro)
        }



        setResult(Activity.RESULT_OK, intent)

        finish()


    }


}
