package nl.parkmobile.vehicles.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nl.parkmobile.vehicles.view.ui.activity.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}

