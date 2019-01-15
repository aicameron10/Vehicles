package nl.parkmobile.vehicles.viewmodel.vehicles

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesResponse
import nl.parkmobile.vehicles.service.repository.vehicles.VehiclesRepository
import javax.inject.Inject

class ListerViewModel @Inject constructor(private val vehiclesRepository: VehiclesRepository, @NonNull application: Application) :
    AndroidViewModel(application) {

    val vehicleObservable: LiveData<VehiclesResponse> = vehiclesRepository.getVehicles()

    fun getVehiclesDb(): LiveData<List<VehiclesData>> {
        return vehiclesRepository.getVehiclesDb()
    }
}
