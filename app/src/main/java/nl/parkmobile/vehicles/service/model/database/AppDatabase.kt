package nl.parkmobile.vehicles.service.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.parkmobile.vehicles.service.model.dao.VehiclesDao
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData

@Database(entities = [VehiclesData::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehiclesDao(): VehiclesDao
}