package nl.parkmobile.vehicles.injection.module

import android.app.Application
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import nl.parkmobile.vehicles.R
import nl.parkmobile.vehicles.service.repository.vehicles.VehiclesService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {
    @Singleton
    @Provides
    fun providesVehiclesService(okHttpClient: OkHttpClient, application: Application, moshi: Moshi): VehiclesService {
        return Retrofit.Builder()
            .baseUrl(application.resources.getString(R.string.base_url))
            .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(VehiclesService::class.java)
    }
}