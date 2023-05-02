package com.example.rafaelanastacioalves.moby.repository

import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.database.DAO
import com.example.rafaelanastacioalves.moby.repository.http.APIClient

class AppRepository(
    private val appDao: DAO,
    var apiClient: APIClient) {

    suspend fun mainEntity(): Resource<List<Match>> {
        return object : NetworkBoundResource<List<Match>, List<Match>>() {
            override suspend fun fecthFromHttp(): List<Match>? {

                return apiClient.getMatchList()
            }

            override suspend fun getFromDB(): List<Match>? {
                return null
            }

            override fun saveIntoDB(resultData: List<Match>?) {}

        }.fromHttpOnly()
    }
    

    suspend fun Match(requestId: String): Resource<Match> {
        return object : NetworkBoundResource<Match, Match>() {
            override suspend fun fecthFromHttp(): Match? {
                return apiClient.getMatchDetails(requestId)
            }

            override suspend fun getFromDB(): Match? {
                TODO("Not yet implemented")
            }

            override fun saveIntoDB(resultData: Match?) {
                TODO("Not yet implemented")
            }
        }.fromHttpOnly()
    }
}