package nl.parkmobile.vehicles.injection

import dagger.Subcomponent
import nl.parkmobile.vehicles.viewmodel.vehicles.ListerViewModel

/**
 * A sub component to create ViewModels.
 */
@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun listViewModel(): ListerViewModel
}