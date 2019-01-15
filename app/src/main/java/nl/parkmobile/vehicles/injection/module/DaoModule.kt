package nl.parkmobile.vehicles.injection.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import nl.parkmobile.vehicles.service.model.dao.VehiclesDao
import nl.parkmobile.vehicles.service.model.database.AppDatabase
import javax.inject.Singleton

@Module
class DaoModule {
    @Singleton
    @Provides
    fun provideVehiclesDatabase(app: Application): AppDatabase = Room.databaseBuilder(app,
            AppDatabase::class.java, "vehicles_db").build()

    @Singleton
    @Provides
    fun provideVehiclesDao(database: AppDatabase): VehiclesDao = database.vehiclesDao()

}
