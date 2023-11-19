package com.example.cse227etp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class FeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val button = view.findViewById<Button>(R.id.button)
        val ratingScale=view.findViewById<TextView>(R.id.textView)

        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingScale.text=rating.toString()
            when(ratingBar.rating.toInt()){
                1 -> ratingScale.text = "Very Bad"
                2 -> ratingScale.text = "Bad"
                3 -> ratingScale.text = "Average"
                4 -> ratingScale.text = "Great"
                5 -> ratingScale.text = "Awesome"
            }
        }
        button.setOnClickListener {
            val message=ratingBar.rating.toString()
            Toast.makeText(requireContext(),"Ratind is: "+message,Toast.LENGTH_SHORT).show()

        }
        return  view
    }

}