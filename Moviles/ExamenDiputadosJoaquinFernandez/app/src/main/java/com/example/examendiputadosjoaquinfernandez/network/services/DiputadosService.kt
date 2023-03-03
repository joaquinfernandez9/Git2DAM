package com.example.examendiputadosjoaquinfernandez.network.services

import com.example.examendiputadosjoaquinfernandez.domain.model.Diputado
import com.example.examendiputadosjoaquinfernandez.domain.model.Partido
import retrofit2.Response
import retrofit2.http.*


interface DiputadosService {
    @GET("/diputados/{idPartido}")
    suspend fun getDiputados(@Path("idPartido") id: String): Response<List<Diputado>>

    @GET("/diputados")
    suspend fun getDiputados(): Response<List<Diputado>>

    @GET("/partidos")
    suspend fun getParidos(): Response<List<Partido>>

    //delete partido
    @DELETE("/partidos/{id}")
    suspend fun deletePartido(@Path("id") id: String): Response<Partido>
    //update partido
    @PUT("/partidos")
    suspend fun updatePartido(
        @Body partido: Partido
    ): Response<Partido>
    @POST("/partidos")
    suspend fun postPartido(@Body partido: Partido): Response<Partido>
}
