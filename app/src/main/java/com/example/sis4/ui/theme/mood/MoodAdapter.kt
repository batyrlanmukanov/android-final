package com.example.sis4.ui.mood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sis4.R
import com.example.sis4.data.model.Mood

class MoodAdapter(private var moods: List<Mood>) :
    RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    inner class MoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDay: TextView = itemView.findViewById(R.id.txtDay)
        val txtMood: TextView = itemView.findViewById(R.id.txtMood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val mood = moods[position]
        holder.txtDay.text = mood.day
        holder.txtMood.text = when (mood.moodLevel) {
            1 -> "😢 Sad"
            2 -> "😐 Normal"
            3 -> "😊 Happy"
            else -> ""
        }

        // highlight today
        val today = java.text.SimpleDateFormat("EEE", java.util.Locale.getDefault()).format(java.util.Date())
        if (mood.day.equals(today, ignoreCase = true)) {
            holder.txtDay.setTypeface(null, android.graphics.Typeface.BOLD)
        } else {
            holder.txtDay.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        // color mood text
        val color = when (mood.moodLevel) {
            1 -> android.graphics.Color.parseColor("#D32F2F") // red
            2 -> android.graphics.Color.parseColor("#FBC02D") // yellow
            3 -> android.graphics.Color.parseColor("#388E3C") // green
            else -> android.graphics.Color.DKGRAY
        }
        holder.txtMood.setTextColor(color)
    }


    override fun getItemCount(): Int = moods.size

    fun updateData(newMoods: List<Mood>) {
        moods = newMoods
        notifyDataSetChanged()
    }
}
