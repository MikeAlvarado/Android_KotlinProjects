package com.example.seiya.dogappv2

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.seiya.dogappv2.Database.MascotaDBData
import com.example.seiya.dogappv2.Database.MascotasDBDatabase
import com.example.seiya.dogappv2.Database.ioThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_mascotas -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.MainView, MascotasFragment.newInstance(MascotasFragment.Type.ALL))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favoritos -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.MainView, MascotasFragment.newInstance(MascotasFragment.Type.FAVORITES))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_buscar -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.MainView, MascotasFragment.newInstance(MascotasFragment.Type.BYBREED))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



        val instanceDatabase = MascotasDBDatabase.getInstance(this) // Context: Main Activity
        val mascotas = MascotaDBData(this).mascotaList

        ioThread {
            if (instanceDatabase.MascotaDBDao().loadAllMascotas().isEmpty()) {
                mascotas.forEach { mascota ->
                    instanceDatabase?.MascotaDBDao()?.insertMascota(mascota)
                }
            }
        }

        navigation.selectedItemId = R.id.navigation_mascotas

        fab_Add.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java).apply {
                //putExtra("mascota", null)
            }
            startActivity(intent)
        }

    }

}
