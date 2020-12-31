package com.example.apprenticeship.data.remote.entities

import com.google.gson.annotations.SerializedName

data class OrderBookEntity(
        val success:Boolean,
        @SerializedName("payload")
        val info : OrderBookInfoEntity
)

data class OrderBookInfoEntity (
    val updated_at :String?,
    val sequence :String?,
    val bids: ArrayList<BidAskEntity>,
    val asks: ArrayList<BidAskEntity>
        )

data class BidAskEntity(
    val book:String?,
    val price:String?,
    val amount:String?
)