package com.example.crudjoaquinfernandez.domain.usecases.headset

import com.example.crudjoaquinfernandez.data.DataHeadset
import com.example.crudjoaquinfernandez.domain.model.Headset

class AddHeadsetUsecase {
    // se pone como usecase o cmo usecases?¿?¿?¿
    operator fun invoke(headset: Headset) =
        DataHeadset.addHeadset(headset)

}