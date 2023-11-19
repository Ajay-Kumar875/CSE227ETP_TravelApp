package com.example.cse227etp

import android.content.Context

object TaskSharedPreferences {
    private const val TASK_PREFS = "TaskPrefs"
    private const val TASK_LIST_KEY = "taskList"

    fun saveTasks(context: Context, tasks: List<String>) {
        val sharedPreferences = context.getSharedPreferences(TASK_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val taskSet = HashSet(tasks)
        editor.putStringSet(TASK_LIST_KEY, taskSet)
        editor.apply()
    }

    fun loadTasks(context: Context): MutableList<String> {
        val sharedPreferences = context.getSharedPreferences(TASK_PREFS, Context.MODE_PRIVATE)
        val taskSet = sharedPreferences.getStringSet(TASK_LIST_KEY, HashSet()) ?: HashSet()
        return taskSet.toMutableList()
    }
}