package com.example.examendiputadosjoaquinfernandez.data.remote

import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido
import com.example.examendiputadosjoaquinfernandez.network.services.DiputadosService
import com.example.examendiputadosjoaquinfernandez.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val serv : DiputadosService,
) : BaseApiResponse(){
    suspend fun fetchPartidos(): NetworkResult<List<Partido>> {
        return safeApiCall(apiCall = {
            serv.getParidos()
        })
    }

    suspend fun deletePartido(id:String): NetworkResult<Partido>{
        return safeApiCall(apiCall = {serv.deletePartido(id)})
    }

    suspend fun updatePartido(partido: Partido): NetworkResult<Partido>{
        return safeApiCall(apiCall = {serv.updatePartido(partido)})
    }

    suspend fun postPartido(partido: Partido): NetworkResult<Partido>{
        return safeApiCall(apiCall = {serv.postPartido(partido)})
    }


    suspend fun getDiputados(id:String): NetworkResult<List<Diputado>> {
        return safeApiCall(apiCall = {
            serv.getDiputados(id)
        })
    }

    suspend fun getDiputados(): NetworkResult<List<Diputado>> {
        return safeApiCall(apiCall = {
            serv.getDiputados()
        })
    }
}