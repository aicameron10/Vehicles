package nl.parkmobile.vehicles.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.parkmobile.vehicles.R
import androidx.databinding.DataBindingUtil
import nl.parkmobile.vehicles.databinding.VehicleCardBinding
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import nl.parkmobile.vehicles.view.callback.VehicleItemCallback

class VehicleAdapter(private val vehicleItemCallback: VehicleItemCallback?) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    private var vehicleList: List<VehiclesData>? = null

    fun setVehicleList(newList: List<VehiclesData>) {
        vehicleList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil
                .inflate<VehicleCardBinding>(LayoutInflater.from(parent.context), R.layout.vehicle_card,
                        parent, false)

        binding.callback = vehicleItemCallback

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.info = vehicleList!![position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        if(vehicleList != null) {
            return vehicleList!!.size
        }
        return 0
    }

    class ViewHolder(val binding: VehicleCardBinding) : RecyclerView.ViewHolder(binding.root)
}