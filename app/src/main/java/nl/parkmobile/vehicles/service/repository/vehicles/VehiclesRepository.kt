package nl.parkmobile.vehicles.service.repository.vehicles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.parkmobile.vehicles.service.model.dao.VehiclesDao
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesResponse
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehiclesRepository @Inject constructor(private val vehiclesService: VehiclesService, val vehiclesDao: VehiclesDao) {

    fun getVehicles(): LiveData<VehiclesResponse> {
        val data = MutableLiveData<VehiclesResponse>()

        vehiclesService.getVehicles()
            .enqueue(object : Callback<VehiclesResponse> {
                override fun onFailure(call: Call<VehiclesResponse>, t: Throwable) {
                    Log.d("vehicles error", t.toString())
                }

                override fun onResponse(call: Call<VehiclesResponse>, response: Response<VehiclesResponse>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                        response.body()?.vehicles?.let { data ->
                            for (i in 0 until data.size) {
                                doAsync { vehiclesDao.insertVehicle(data[i]) }
                            }
                        }
                    } else {
                        data.value = null
                    }
                }
            })
        return data
    }

    fun getVehiclesDb(): LiveData<List<VehiclesData>> {
        return vehiclesDao.queryVehicles()
    }
}