package com.example.sis4.ui.mood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sis4.R
import com.example.sis4.ui.theme.viewmodel.MoodViewModel

class AddMoodFragment : Fragment() {

    private lateinit var viewModel: MoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_mood, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MoodViewModel::class.java]

        view.findViewById<Button>(R.id.btnRandomMood).setOnClickListener {
            viewModel.addRandomMoods()
            parentFragmentManager.popBackStack()
        }
    }
}
