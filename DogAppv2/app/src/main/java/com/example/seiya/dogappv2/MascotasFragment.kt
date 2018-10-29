package com.example.seiya.dogappv2


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.seiya.dogappv2.Database.MascotasDBDatabase
import com.example.seiya.dogappv2.Database.ioThread
import com.example.seiya.dogappv2.mascotas.MascotaAdapter
import kotlinx.android.synthetic.main.fragment_mascotas.*


class MascotasFragment : Fragment() {

    enum class Type {
        ALL,FAVORITES,BYBREED
    }

    companion object {
        fun newInstance(type: Type) = MascotasFragment().apply {
            val args = Bundle()
            args.putSerializable("type", type)
            arguments = args
        }
    }

    private val mascotaadapter = MascotaAdapter(mutableListOf())
    private lateinit var type:Type


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments!!.getSerializable("type") as Type

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mascotas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ArrayAdapter.createFromResource(
                requireContext(),
                R.array.razas_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            breedspinner.adapter = adapter
        }

        breedspinner.visibility = when (type){

            MascotasFragment.Type.ALL,
            MascotasFragment.Type.FAVORITES -> View.GONE
            MascotasFragment.Type.BYBREED -> View.VISIBLE
        }

        recyclerview.apply {
            adapter = mascotaadapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        breedspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                loadAllMascotas(parent?.getItemAtPosition(position) as String)
            }
        }
        /*
        when (type) {
            MascotasFragment.Type.ALL,
            MascotasFragment.Type.FAVORITES -> loadAllMascotas()
        }
        */
    }

    override fun onResume() {
        super.onResume()

        when (type) {
            MascotasFragment.Type.ALL,
            MascotasFragment.Type.FAVORITES -> loadAllMascotas()
        }
    }


    private fun loadAllMascotas(raza: String? = null){
        val instanceDatabase = MascotasDBDatabase.getInstance(requireContext()) // Context: Main Activity

        ioThread {
            val mascotas = when(type) {
                MascotasFragment.Type.ALL -> {
                    instanceDatabase?.MascotaDBDao()?.loadAllMascotas()
                }
                MascotasFragment.Type.FAVORITES -> {
                    instanceDatabase?.MascotaDBDao()?.loadFavoriteMascotas()
                }
                MascotasFragment.Type.BYBREED -> {
                    instanceDatabase?.MascotaDBDao()?.loadByRaza(raza!!)
                }
            }
            requireActivity().runOnUiThread{
                mascotaadapter.mascotaList.clear()
                mascotaadapter.mascotaList.addAll(mascotas)
                mascotaadapter.notifyDataSetChanged()
            }
        }
    }


}
