package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.domain.model.Headset

object DataHeadset {
    private val headset = mutableListOf<Headset>()

    init {
        headset.add(Headset(1, "HyperX Cloud Stinger",
            true, bluetooth = false,))
        headset.add(Headset(2, "HyperX Cloud Alpha",
             true, bluetooth = false,))
        headset.add(Headset(3, "HyperX Cloud Flight",
             true, bluetooth = true,))
    }

    //CRUD create read update delete
    fun addHeadset(cascos: Headset) = headset.add(cascos)

    fun removeHeadset(id: Int) {
        headset.remove(headset.find { it.id == id })
    }

    fun getHeadset(id: Int): Headset {
        return this.headset[id]
    }




}