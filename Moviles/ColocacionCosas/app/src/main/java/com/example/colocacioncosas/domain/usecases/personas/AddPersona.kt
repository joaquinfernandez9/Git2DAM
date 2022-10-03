package com.example.colocacioncosas.domain.usecases.personas

import com.example.colocacioncosas.data.Repository
import com.example.colocacioncosas.domain.modelo.Persona

class AddPersona {


    fun invoke(persona: Persona) = Repository.createSingleton().addPersona(persona)


}
