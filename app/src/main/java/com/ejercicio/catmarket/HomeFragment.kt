// HomeFragment.kt
package com.ejercicio.catmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    companion object {
        // Agregar un método estático para crear una nueva instancia del fragmento
        fun newInstance(username: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString("username", username)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        postAdapter = PostAdapter(createDummyPosts())
        recyclerView.adapter = postAdapter

        return view
    }

    private fun createDummyPosts(): List<Post> {
        val posts = mutableListOf<Post>()
        posts.add(Post("Dato 1", "Se comunican mediante vocalizaciones como maullidos, " +
                "aullidos, ronroneos y gemidos, además de ciertas poses y movimientos.", R.drawable.post1))
        posts.add(Post("Dato 2", "Su esperanza de vida va de entre 12 y 14 años.", R.drawable.post2))
        posts.add(Post("Dato 3", "Además de saltar hasta 3.5 metros, se valen de sus " +
                "uñas para escalar muros, árboles o superficies blandas.", R.drawable.post3))
        posts.add(Post("Dato 4", "Si su pelaje es de tres o hasta cuatro colores, " +
                "se trata de una hembra.", R.drawable.post4))
        posts.add(Post("Dato 5", "Cada una de sus orejas tiene " +
                "32 músculos y movimientos independientes.", R.drawable.post5))
        posts.add(Post("Dato 6", "Duermen entre 13 y 14 " +
                "horas al día (quién como ellos).", R.drawable.post6))
        return posts
    }
}
