package com.example.rafaelanastacioalves.moby.domain.interactors


import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import kotlinx.coroutines.flow.FlowCollector

class EntityDetailsInteractor(
    private val appRepository: AppRepository
) :
    Interactor<Resource<Match>, EntityDetailsInteractor.RequestValues>() {

    class RequestValues(val requestId: String) : Interactor.RequestValues

    override suspend fun run(
        requestValue: RequestValues?,
        flowCollector: FlowCollector<Resource<Match>>,
    ){
        flowCollector.emit(requestValue?.requestId.let { it?.let { param -> appRepository.matchDetails(param) } }
            ?: Resource(
                Resource.Status.GENERIC_ERROR,
                data = null,
                "Nenhum parametro enviado para API"
            ))
    }

}
