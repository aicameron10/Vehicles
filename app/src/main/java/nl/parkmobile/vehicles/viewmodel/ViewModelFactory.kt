package nl.parkmobile.vehicles.viewmodel

import androidx.collection.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.parkmobile.vehicles.injection.ViewModelSubComponent
import nl.parkmobile.vehicles.viewmodel.vehicles.ListerViewModel
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject
constructor(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {
    private val creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    init {
        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators[ListerViewModel::class.java] = Callable<ViewModel> { viewModelSubComponent.listViewModel() }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]
        if(creator == null) {
            for ((key, value) in creators) {
                if(modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if(creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}