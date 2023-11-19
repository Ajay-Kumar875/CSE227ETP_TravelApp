package com.example.cse227etp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class HomeFragment : Fragment() {

    private lateinit var location :ImageButton
    private lateinit var canvas :ImageButton
    private lateinit var feed :ImageButton
    private lateinit var logout:ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_home, container, false)

        location = view.findViewById(R.id.btnLoc)
        canvas = view.findViewById(R.id.btnCanvas)
        feed = view.findViewById(R.id.btnFeed)
        logout=view.findViewById(R.id.btnLogout)

        location.setOnClickListener {
            val fragmentB = LocationFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentB)
                .addToBackStack(null) // Optional: add to back stack for back navigation
                .commit()
        }
        canvas.setOnClickListener {
            val fragmentB = CanvasFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentB)
                .addToBackStack(null) // Optional: add to back stack for back navigation
                .commit()
        }
        feed.setOnClickListener {
            val fragmentB = FeedbackFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentB)
                .addToBackStack(null) // Optional: add to back stack for back navigation
                .commit()
        }
        logout.setOnClickListener {
            val intent=Intent(requireContext(),LoginPage::class.java)
            startActivity(intent)
        }

        return view
    }

}