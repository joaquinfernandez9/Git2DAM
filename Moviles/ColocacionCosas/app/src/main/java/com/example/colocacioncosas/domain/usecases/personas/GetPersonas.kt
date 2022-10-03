package com.example.colocacioncosas.domain.usecases.personas

import com.example.colocacioncosas.data.Repository

class GetPersonas {
    operator fun invoke() = Repository.createSingleton().getLista()
}