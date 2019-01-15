package nl.parkmobile.vehicles.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import nl.parkmobile.vehicles.VehiclesApplication
import nl.parkmobile.vehicles.injection.module.ActivityModule
import nl.parkmobile.vehicles.injection.module.AppModule
import nl.parkmobile.vehicles.injection.module.DaoModule
import nl.parkmobile.vehicles.injection.module.ServiceModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class,
    ServiceModule::class, DaoModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder //Provides app context to modules

        fun build(): AppComponent
    }

    fun inject(vehiclesApplication: VehiclesApplication)
}
