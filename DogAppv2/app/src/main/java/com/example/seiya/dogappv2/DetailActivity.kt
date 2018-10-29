package com.example.seiya.dogappv2

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.seiya.dogappv2.R.id.none
import kotlinx.android.synthetic.main.mascota_item_layout.*
import android.widget.EditText
import com.example.seiya.dogappv2.R.id.tv_detail_Nombre
import kotlinx.android.synthetic.main.activity_detail.*

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.widget.Toast
import com.example.seiya.dogappv2.Database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.jar.Manifest


class DetailActivity : AppCompatActivity() {

    private var mascota:Mascota? = null
    private lateinit var thumbnailBitmap : Bitmap
    val mascotaList:MutableList<MascotaDB> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mascota = intent.extras?.getSerializable("mascota") as Mascota?

        if (mascota != null){

            tv_detail_Nombre.setText(mascota!!.Nombre)
            tv_detail_Raza.setText(mascota!!.Raza)
            tv_detail_Lugar.setText(mascota!!.LugarExtravio)
            tv_detail_Telefono.setText(mascota!!.Telefono)
            tv_detail_Correo.setText(mascota!!.CorreoElectronico)
            tv_detail_FechaRegistro.setText("Fecha de Registro: " + mascota!!.FechaPublicacion)




            if (mascota?.idImagen == 0) {
                val byteArray = mascota!!.imagenTemporal!!
                val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                iv_detail_Foto.setImageBitmap(bmp)
            }
            else{
                iv_detail_Foto.setImageResource(mascota!!.idImagen)
            }


            tg_detail_Favorite.setOnClickListener {
                //tg_detail_Favorite.toggle()
                val mascotaa = MascotaDB(
                        mascota!!.Nombre!!,
                        mascota!!.Raza!!,
                        mascota!!.FechaPublicacion!!,
                        mascota!!.CorreoElectronico!!,
                        mascota!!.LugarExtravio!!,
                        mascota!!.Telefono!!,
                        mascota!!.idImagen!!,
                        mascota!!.Favorito!!,
                        mascota!!.imagenTemporal!!
                )


                val instanceDatabase = MascotasDBDatabase.getInstance(this) // Context: Main Activity

                ioThread {
                    instanceDatabase.MascotaDBDao().deleteMascota(mascotaa)
                    //mascotaa?.Favorito = !mascota!!.Favorito
                    //instanceDatabase.MascotaDBDao().insertMascota(mascotaa)
                }
            }



            disableEditText(tv_detail_Nombre)
            disableEditText(tv_detail_Raza)
            disableEditText(tv_detail_Lugar)
            disableEditText(tv_detail_Telefono)
            disableEditText(tv_detail_Correo)
            //disableEditText(tv_detail_FechaRegistro)
            bt_TomarFoto.setVisibility(View.GONE)
            fab_SaveInformation.setVisibility(View.GONE)
            tg_detail_Favorite.setVisibility(View.VISIBLE)




        }
        else {
            enableEditText(tv_detail_Nombre)
            enableEditText(tv_detail_Raza)
            enableEditText(tv_detail_Lugar)
            enableEditText(tv_detail_Telefono)
            enableEditText(tv_detail_Correo)
            //disableEditText(tv_detail_FechaRegistro)
            //enableEditText(tv_detail_FechaRegistro)
            bt_TomarFoto.setVisibility(View.VISIBLE)
            fab_SaveInformation.setVisibility(View.VISIBLE)
            tg_detail_Favorite.setVisibility(View.GONE)
            tv_detail_FechaRegistro.setVisibility(View.GONE)

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



        }

    }

    private fun disableEditText(editText: EditText) {
        editText.isFocusable = false
        editText.isEnabled = false
        editText.isCursorVisible = false
        editText.hint = ""
        //editText.keyListener = null
        //editText.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun enableEditText(editText: EditText) {
        editText.setFocusableInTouchMode(true)
        editText.isEnabled = true
        editText.isCursorVisible = true
        //editText.keyListener = null
        //editText.setBackgroundColor(Color.TRANSPARENT)
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

                    iv_detail_Foto.setImageBitmap(thumbnailBitmap)
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


        var mascotaa = MascotaDB(
                tv_detail_Nombre.text.toString(),
                tv_detail_Raza.text.toString(),
                "$fDia/$fsMes/$fAno",
                tv_detail_Correo.toString(),
                tv_detail_Correo.text.toString(),
                tv_detail_Telefono.text.toString(),
                0,
                false,
                stream.toByteArray())

        val instanceDatabase = MascotasDBDatabase.getInstance(this) // Context: Main Activity

        ioThread {
            instanceDatabase.MascotaDBDao().insertMascota(mascotaa)
        }
        finish()


    }
}
