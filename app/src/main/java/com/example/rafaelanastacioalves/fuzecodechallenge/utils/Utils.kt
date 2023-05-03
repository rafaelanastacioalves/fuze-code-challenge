package com.example.rafaelanastacioalves.fuzecodechallenge.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    fun formatDateTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEE, HH:mm", Locale.getDefault())

        val inputDate = inputFormat.parse("2023-03-01T13:00:00Z")
        val outputDate = outputFormat.format(inputDate)
            .replace(".", "")
            .uppercase()

        return outputDate
    }
}