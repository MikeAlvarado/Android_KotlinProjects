package com.example.seiya.a01400121_p1k_mascotas

/**
 * Clase: MainActivity.kt
 * Descripción:
 *      Controlador Principal de la clase, sirve para manejar las diferentes actividades que hay,
 *      aquí es donde se instancia la lista principal y se maneja en las demás vistas con los
 *      extras del intent
 * Autor: Miguel Angel Alvarado López, A01400121
 * Fecha de creación: 13/09/2018
 * Fecha de última modificación: 16/09/2018
 */

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var adapter : PerroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PerroAdapter(this, perros.toMutableList())
        list_Perros.adapter = adapter
        adapter?.notifyDataSetChanged()
        list_Perros.setOnItemClickListener(this)

        fab_AddPerro.setOnClickListener {
            val intent = Intent(this, perro_add::class.java)
            startActivityForResult(intent, ADD_RESULT_KEY)
        }

        s_FavOnly.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                var perrosNuevos = perros.filter {
                    it.Favorito
                }
                adapter.setPerros(perrosNuevos)
            }
            else {
                adapter.setPerros(perros)
            }
        }



    }

    companion object {
        const val PERRO_KEY = "PERRO"
        /*
        const val TITULO_KEY:String="Nombre"
        const val ID_IMAGEN_KEY:String="idImagen"
        const val RAZA_KEY:String="Raza"
        const val LUGAR_KEY:String="LugarExtravio"
        const val CORREO_KEY:String="CorreoElectronico"
        const val TELEFONO_KEY:String="Telefono"
        const val FECHA_KEY:String="FechaPublicacion"
        const val FAVORITO_KEY:String="Favorito"
        */
        const val DETAIL_RESULT_KEY=1000

        const val ADD_RESULT_KEY=1001
    }

    val perros: MutableList<Perro> = Perro_Data().ListaPerros

    override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        val perro = perros[position]
        val intent = Intent(this, perro_detail::class.java).apply {
            putExtra(PERRO_KEY, perro)
        }
        /*
        intent.putExtra(TITULO_KEY, perro.Nombre)
        intent.putExtra(ID_IMAGEN_KEY, perro.idImagen)
        intent.putExtra(RAZA_KEY, perro.Raza)
        intent.putExtra(LUGAR_KEY, perro.LugarExtravio)
        intent.putExtra(CORREO_KEY, perro.CorreoElectronico)
        intent.putExtra(TELEFONO_KEY, perro.Telefono)
        intent.putExtra(FECHA_KEY, perro.FechaPublicacion)
        intent.putExtra(FAVORITO_KEY, perro.Favorito)
        */
        startActivityForResult(intent, DETAIL_RESULT_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DETAIL_RESULT_KEY && resultCode == Activity.RESULT_OK) {
            val perroNuevo = data!!.getSerializableExtra(PERRO_KEY) as Perro
            val index = perros.indexOfFirst { perro ->
                perro.Nombre == perroNuevo.Nombre
            }
            perros.removeAt(index)
            perros.add(index, perroNuevo)
            adapter?.notifyDataSetChanged()

        }

        if (requestCode == ADD_RESULT_KEY && resultCode == Activity.RESULT_OK){
            val perroNuevo = data!!.getSerializableExtra(PERRO_KEY) as Perro
            perros.add(perroNuevo)

            if (s_FavOnly.isChecked){
                    var perrosNuevos = perros.filter {
                        it.Favorito
                    }
                    adapter.setPerros(perrosNuevos)
                }
                else {
                    adapter.setPerros(perros)
                }


            //adapter.setPerros(perros)
            //adapter?.notifyDataSetChanged()
        }



    }
}
