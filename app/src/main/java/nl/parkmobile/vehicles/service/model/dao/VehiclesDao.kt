package nl.parkmobile.vehicles.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData

@Dao
interface VehiclesDao {

    @Query("SELECT * FROM vehicles ORDER BY vrn ASC")
    fun queryVehicles(): LiveData<List<VehiclesData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicle(vehiclesData: VehiclesData?)
}