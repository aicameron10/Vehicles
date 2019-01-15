package nl.parkmobile.vehicles.service.model.vehicles

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VehiclesResponse(var count: Int? = 0,
                            var vehicles: List<VehiclesData>,
                            var currentPage: Int? = 0,
                            var nextPage: Int? = 0,
                            var totalPages: Int? = 0)