package com.example.apprenticeship.domain

data class OrderBook(
    val updated_at :String?,
    val sequence :String?,
    val bid: ArrayList<BidAskModel>,
    val ask: ArrayList<BidAskModel>
)


data class BidAskModel(
    val book:String?,
    val price:String?,
    val amount:String?
)
