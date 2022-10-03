package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.domain.model.Headset

object DataHeadset {
    private val headset = mutableMapOf<Int, Headset>()

    init {
        headset[1] = Headset(1, "HyperX Cloud Stinger",
            29.99, true, bluetooth = false,
        )
        headset[2] = Headset(2, "Mixcder 9",
            price = 29.99, true, bluetooth = true,
        )
        headset[3] = Headset(3, "Razer Kraken",
            price = 49.99, true, bluetooth = false,
        )
    }

    //CRUD create read update delete
    fun addHeadset(headset: Headset) {
        this.headset[headset.id] = headset
    }

    fun removeHeadset(id: Int) {
        this.headset.remove(id)
    }

    fun updateHeadset(headset: Headset) {
        this.headset[headset.id] = headset
    }

    fun getHeadset(id: Int): Headset? {
        return this.headset[id]
    }




}