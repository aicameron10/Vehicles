package nl.parkmobile.vehicles.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nl.parkmobile.vehicles.view.ui.fragment.DetailFragment
import nl.parkmobile.vehicles.view.ui.fragment.ListerFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeListerFragment(): ListerFragment

}
