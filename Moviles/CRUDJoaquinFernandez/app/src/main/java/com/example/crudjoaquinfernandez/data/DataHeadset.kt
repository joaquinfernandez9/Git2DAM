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
        headsetList.add(Headset(4, "HyperX Cloud II",
                true, bluetooth = false,))
        headsetList.add(Headset(5, "HyperX Cloud Revolver S",
                true, bluetooth = false,))
        headsetList.add(Headset(6, "HyperX Cloud Orbit S",
                true, bluetooth = true,))
        headsetList.add(Headset(7, "HyperX Cloud Orbit",
                true, bluetooth = true,))
        headsetList.add(Headset(8, "HyperX Cloud Alpha S",
                true, bluetooth = false,))
        headsetList.add(Headset(9, "HyperX Cloud Flight S",
                true, bluetooth = true,))
    }

    fun getHeadset(id: Int): Headset {
        return this.headsetList[id]
    }

    fun getAllHeadsets(): List<Headset> {
        return headsetList
    }

    fun addHeadset(cascos: Headset) = headsetList.add(cascos)

    fun removeHeadset(id: Int) =
        headsetList.remove(headsetList.find { it.id == id })


    fun updateHeadset(headset: Headset) {
        val headsetLista = headsetList.find { it.id == headset.id }
        headsetLista?.name = headset.name
        headsetLista?.mic = headset.mic
        headsetLista?.bluetooth = headset.bluetooth

    }




}