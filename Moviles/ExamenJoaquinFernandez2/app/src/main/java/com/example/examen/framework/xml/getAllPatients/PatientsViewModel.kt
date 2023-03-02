package com.example.examen.framework.xml.getAllPatients

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class PatientsViewModel @Inject constructor(
    private val getPatients: AllPatients
){
}