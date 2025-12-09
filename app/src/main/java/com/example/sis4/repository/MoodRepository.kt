package com.example.sis4.repository

import com.example.sis4.data.model.Mood

class MoodRepository {
    private val moodList = mutableListOf<Mood>()

    fun getAllMoods(): List<Mood> = moodList

    fun addMood(mood: Mood) {
        moodList.add(mood)
    }
}
