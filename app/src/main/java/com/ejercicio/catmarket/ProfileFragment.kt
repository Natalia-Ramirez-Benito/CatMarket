// ProfileFragment.kt
package com.ejercicio.catmarket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {

    private lateinit var logoutButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var reportAdapter: ReportAdapter

    private lateinit var dbHelper: DBClass
    companion object {
        // Agregar un método estático para crear una nueva instancia del fragmento
        fun newInstance(username: String): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString("username", username)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        logoutButton = view.findViewById(R.id.logoutButton)
        recyclerView = view.findViewById(R.id.reportRecyclerView)
        val userTextView = view.findViewById<TextView>(R.id.UserTextView)

        val username = arguments?.getString("username") ?: ""

        dbHelper = DBClass(requireContext())
        userTextView.text = username

        reportAdapter = ReportAdapter(dbHelper.getReportsForUser(username))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = reportAdapter

        logoutButton.setOnClickListener {
            val intent = Intent(activity, login_form::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }
}
