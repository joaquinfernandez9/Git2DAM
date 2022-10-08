package com.example.crudjoaquinfernandez.data

import com.example.crudjoaquinfernandez.domain.model.Headset

object DataHeadset {
    private val headsetList = mutableListOf<Headset>()

    init {
        headsetList.add(Headset(1, "HyperX Cloud Stinger",
            true, bluetooth = false,))
        headsetList.add(Headset(2, "HyperX Cloud Alpha",
             true, bluetooth = false,))
        headsetList.add(Headset(3, "HyperX Cloud Flight",
             true, bluetooth = true,))
    }

    fun getHeadset(id: Int): Headset {
        return this.headsetList[id]
    }

    fun getAllHeadsets(): List<Headset> {
        return headsetList
    }

    fun addHeadset(cascos: Headset) = headsetList.add(cascos)

    fun removeHeadset(id: Int) {
        headsetList.remove(headsetList.find { it.id == id })
    }

    fun updateHeadset(id: Int, name:String, mic: Boolean, bluetooth: Boolean) {
        val headset = headsetList.find { it.id == id }
        headset?.name = name
        headset?.mic = mic
        headset?.bluetooth = bluetooth
    }




}