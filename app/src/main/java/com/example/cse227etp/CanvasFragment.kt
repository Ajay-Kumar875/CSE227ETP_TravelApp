package com.example.cse227etp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CanvasFragment : Fragment() {

    private lateinit var drawingCanvas: CustomCanvas
    private lateinit var fabSave: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_canvas, container, false)

        drawingCanvas = view.findViewById(R.id.drawing_canvas)
        fabSave = view.findViewById(R.id.fab_save)

        fabSave.setOnClickListener {
            saveDrawing()
        }

        return view
    }

    private fun saveDrawing() {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "Notes_$timestamp"
        drawingCanvas.saveDrawingToStorage(fileName)
        Toast.makeText(requireContext(), "Drawing saved!", Toast.LENGTH_SHORT).show()
    }

}