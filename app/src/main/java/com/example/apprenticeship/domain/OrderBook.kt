package com.example.apprenticeship.domain

data class OrderBook(
    val updated_at :String?,
    val sequence :String?,
    val bids: ArrayList<Bid>
)


data class Bid(
    val book:String?,
    val price:String?,
    val amount:String?
)
