package com.example.apprenticeship.domain

data class OrderBook(
    val updated_at: String?,
    val sequence: String?,
    val bids: ArrayList<BidAskModel>,
    val asks: ArrayList<BidAskModel>
)

data class BidAskModel(
    val book: String?,
    val price: String?,
    val amount: String?
)
