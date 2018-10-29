package com.example.seiya.dogappv2.Database

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.seiya.dogappv2.R

class MascotaDBData(private val context: Context) {

    val mascotaList:MutableList<MascotaDB> = ArrayList()

    init {
        dataList()
    }

    fun getBitmap(imageId:Int): Bitmap = BitmapFactory.decodeResource(context.resources, imageId)

    fun dataList() {
        mascotaList.add(MascotaDB(
                "Milou",
                "Wire Fox Terrier",
                "14/06/96",
                "MikeAlvaradoL06@gmail.com",
                "My House",
                "4492058964",
                R.drawable.imgmilou,
                true,
                null
        ))

        mascotaList.add(MascotaDB(
                "Snoopy",
                "Beagle",
                "14/06/96",
                "MikeAlvaradoL06@gmail.com",
                "My House",
                "4492058964",
                R.drawable.imgsnoopy,
                false,
                null
        ))

        mascotaList.add(MascotaDB(
                "Scooby Doo",
                "Grán Danés",
                "14/06/96",
                "MikeAlvaradoL06@gmail.com",
                "My House",
                "4492058964",
                R.drawable.imgscooby,
                true,
                null
        ))

        mascotaList.add(MascotaDB(
                "Clifford",
                "Labrador/Giant Vizla",
                "14/06/96",
                "MikeAlvaradoL06@gmail.com",
                "My House",
                "4492058964",
                R.drawable.imgclifford,
                false,
                null
        ))


    }

}