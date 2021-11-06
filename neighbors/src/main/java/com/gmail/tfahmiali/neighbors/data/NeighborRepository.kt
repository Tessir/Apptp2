package com.gmail.tfahmiali.neighbors.data

import com.gmail.tfahmiali.neighbors.data.service.DummyNeighborApiService
import com.gmail.tfahmiali.neighbors.data.service.NeighborApiService
import com.gmail.tfahmiali.neighbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours
    fun removeNeighbours(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)
    fun addNeighbours(neighbor: Neighbor) = apiService.createNeighbour(neighbor)
//    fun updateNeigbours (neighbor: Neighbor)= apiService.updateDataNeighbour(neighbor)
    fun getIdNeigbours ()= getNeighbours().last().id + 1
    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
