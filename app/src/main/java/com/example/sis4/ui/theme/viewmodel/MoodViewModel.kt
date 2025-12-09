package com.example.sis4.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sis4.data.model.Mood

class MoodViewModel : ViewModel() {

    private val _moods = MutableLiveData<List<Mood>>(emptyList())
    val moods: LiveData<List<Mood>> = _moods

    fun addMood(mood: Mood) {
        val current = _moods.value ?: emptyList()
        _moods.value = current + mood
    }

    fun addRandomMoods() {
        val days = listOf("Mon","Tue","Wed","Thu","Fri","Sat","Sun")
        val randomMoods = days.map { day ->
            Mood(day, (1..3).random())
        }
        _moods.value = randomMoods
    }
}
