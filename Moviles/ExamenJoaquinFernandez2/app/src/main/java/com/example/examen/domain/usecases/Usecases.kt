package com.example.examen.domain.usecases

import com.example.examen.data.local.repos.HospitalRepository
import javax.inject.Inject

class GetHospitales @Inject constructor(private val repo: HospitalRepository){
    suspend fun invoke() = repo.fetchHospitales()
}