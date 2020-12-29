package com.example.apprenticeship.data

data class OrderBookEntity (
  val updated_at :String?,
  val sequence :String?,
  val bids: ArrayList<BidEntity>
        )


data class BidEntity(
    val book:String?,
    val price:String?,
    val amount:String?
)