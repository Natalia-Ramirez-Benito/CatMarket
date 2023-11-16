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
        posts.add(Post("Post 1", "This is the content of post 1", R.drawable.post1))
        posts.add(Post("Post 2", "This is the content of post 2", R.drawable.post2))
        posts.add(Post("Post 3", "This is the content of post 3", R.drawable.post3))
        posts.add(Post("Post 4", "This is the content of post 4", R.drawable.post4))
        posts.add(Post("Post 5", "This is the content of post 5", R.drawable.post5))
        posts.add(Post("Post 6", "This is the content of post 6", R.drawable.post6))
        return posts
    }
}
