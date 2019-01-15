package nl.parkmobile.vehicles.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_lister.*
import nl.parkmobile.vehicles.R
import nl.parkmobile.vehicles.databinding.FragmentListerBinding
import nl.parkmobile.vehicles.injection.Injectable
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import nl.parkmobile.vehicles.util.FragmentDisplayManager
import nl.parkmobile.vehicles.view.adapter.VehicleAdapter
import nl.parkmobile.vehicles.view.callback.VehicleItemCallback
import nl.parkmobile.vehicles.viewmodel.vehicles.ListerViewModel
import javax.inject.Inject

class ListerFragment : androidx.fragment.app.Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentListerBinding
    private lateinit var viewModel: ListerViewModel
    private var adapter: VehicleAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lister, container, false)

        adapter = VehicleAdapter(vehicleItemCallback)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListerViewModel::class.java)
        binding.vehicleViewModel = viewModel
        observeViewModel(viewModel)

        swipeRefreshLayout.setOnRefreshListener {
            getLoyaltyInfo()
        }
    }

    private fun getLoyaltyInfo(){
        viewModel.getVehiclesDb().observe(this, Observer { data ->
            if(data != null){
                binding.swipeRefreshLayout.isRefreshing = false
                adapter?.setVehicleList(data)
            }
        })
    }

    private fun observeViewModel(viewModel: ListerViewModel) {
        // Update the list when the data changes
        viewModel.vehicleObservable.observe(this, Observer { data ->
            if (data != null) {
                binding.swipeRefreshLayout.isRefreshing = false
                adapter?.setVehicleList(data.vehicles)
            }
        })
    }

    private val vehicleItemCallback = object : VehicleItemCallback {
        override fun onClick(vehiclesData: VehiclesData) {
            return when {
                lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) -> {
                    FragmentDisplayManager().displayView(context = activity!!, NavigateToFragment = DetailFragment(), tag = "DetailFragment", extras = vehiclesData)
                }
                else -> {
                    // do nothing
                }
            }
        }
    }
}
