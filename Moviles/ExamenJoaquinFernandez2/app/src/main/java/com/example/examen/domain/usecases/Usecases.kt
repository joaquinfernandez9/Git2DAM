package com.example.examen.domain.usecases

import com.example.examen.data.local.repos.HospitalRepository
import javax.inject.Inject

class GetHospitales @Inject constructor(private val repo: HospitalRepository) {
    fun invoke() = repo.listHospitales()
}