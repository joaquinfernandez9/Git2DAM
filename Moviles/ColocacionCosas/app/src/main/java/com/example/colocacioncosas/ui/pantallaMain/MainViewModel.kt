package com.example.colocacioncosas.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.colocacioncosas.domain.modelo.Persona
import com.example.colocacioncosas.domain.usecases.personas.AddPersona
import com.example.colocacioncosas.domain.usecases.personas.GetPersonas

class MainViewModel(
    private val addPersona: AddPersona,
    private val getPersonas: GetPersonas,
    // constructor metodos (use cases) que necesito,
    // se puede poner una coma al final de las cosas para que sea mas practico,
) : ViewModel() {

    // solo el viewmodel cambia el state, nadie mas
    private val _uiState = MutableLiveData<MainState>()
    //si giro con unerror va a mantener el error
    val uiState: LiveData<MainState> get() = _uiState

    fun addPersona(persona: Persona){
       if (!addPersona.invoke(persona)){
           _uiState.value = MainState(
               persona= Persona("null"),
               error = "Error al agregar persona",)
           _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
       }
    }

    fun getPersonas(id: Int){
        val personas = getPersonas()

        if (personas.size < id || id < 0) {
             _uiState.value = _uiState.value?.copy(error = "error")

        } else
            _uiState.value = _uiState.value?.copy(persona = personas[id])


    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

}






// esto esta por que no hay injeccion dependencias
/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
//    private val stringProvider: StringProvider,
    private val addPersona: AddPersona,
//    getPersonas: GetPersonas,
    private val getPersonas: GetPersonas,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
//                stringProvider,
                addPersona,
                getPersonas,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}