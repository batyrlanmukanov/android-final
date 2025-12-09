package com.example.sis4.ui.mood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sis4.R
import com.example.sis4.ui.theme.progress.ProgressFragment
import com.example.sis4.ui.theme.viewmodel.MoodViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MoodListFragment : Fragment() {

    private lateinit var viewModel: MoodViewModel
    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mood_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MoodViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMood)
        adapter = MoodAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.moods.observe(viewLifecycleOwner) { moods ->
            adapter.updateData(moods)
        }

        view.findViewById<FloatingActionButton>(R.id.fabAddMood).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AddMoodFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnProgress).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProgressFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
