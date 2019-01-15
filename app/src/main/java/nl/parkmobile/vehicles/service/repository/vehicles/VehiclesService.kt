package nl.parkmobile.vehicles.service.repository.vehicles


import nl.parkmobile.vehicles.service.model.vehicles.VehiclesResponse
import retrofit2.Call
import retrofit2.http.*

interface VehiclesService {

    @GET("/vehicles")
    fun getVehicles(): Call<VehiclesResponse>
}