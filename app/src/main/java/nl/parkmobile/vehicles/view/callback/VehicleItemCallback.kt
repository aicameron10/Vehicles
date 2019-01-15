package nl.parkmobile.vehicles.view.callback

import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData

interface VehicleItemCallback {
    fun onClick(vehiclesData: VehiclesData)
}