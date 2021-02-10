package com.example.apprenticeship.domain

data class Ticker(
    val high: String,
    val last: String,
    val created_at: String,
    val book: String,
    val volume: String,
    val vwap: String,
    val low: String,
    val ask: String,
    val bid: String,
    val change_24: String
)
