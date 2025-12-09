package com.example.sis4.ui.theme.progress

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sis4.R
import com.example.sis4.ui.theme.viewmodel.MoodViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProgressFragment : Fragment() {

    private lateinit var viewModel: MoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MoodViewModel::class.java]

        val chart = view.findViewById<LineChart>(R.id.lineChart)
        chart.description.isEnabled = false
        chart.setNoDataText("No mood data yet")
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setScaleEnabled(false)
        chart.legend.isEnabled = true
        chart.xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)

        viewModel.moods.observe(viewLifecycleOwner) { moods ->
            if (moods.isEmpty()) return@observe

            val entries = moods.mapIndexed { index, mood ->
                Entry(index.toFloat(), mood.moodLevel.toFloat())
            }

            val dataSet = LineDataSet(entries, "Mood Level").apply {
                mode = LineDataSet.Mode.CUBIC_BEZIER  // сглаженная линия
                lineWidth = 3f
                circleRadius = 6f
                setDrawValues(true)
                valueTextSize = 12f
                // circle colors by mood
                val colors = moods.map { mood ->
                    when (mood.moodLevel) {
                        1 -> android.graphics.Color.parseColor("#D32F2F")
                        2 -> android.graphics.Color.parseColor("#FBC02D")
                        3 -> android.graphics.Color.parseColor("#388E3C")
                        else -> android.graphics.Color.GRAY
                    }
                }.toIntArray()
                setCircleColors(*colors)
                color = android.graphics.Color.parseColor("#1976D2") // line color
            }

            chart.data = LineData(dataSet)
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(moods.map { it.day })
            chart.xAxis.granularity = 1f
            chart.axisRight.isEnabled = false
            chart.axisLeft.axisMinimum = 0f
            chart.axisLeft.axisMaximum = 3f
            chart.animateY(400)
            chart.invalidate()
        }
    }

}
