package nl.parkmobile.vehicles.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.*
import nl.parkmobile.vehicles.R
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import nl.parkmobile.vehicles.util.Constants
import nl.parkmobile.vehicles.view.ui.activity.MainActivity

class DetailFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val extras = arguments?.getParcelable<VehiclesData>(Constants.EXTRAS_BUNDLE)

        (activity as MainActivity).changeTitle(resources.getText(R.string.app_name_details).toString(), true)

        idVehicle.text = String.format(getString(R.string.text_vehicleId), extras?.vehicleId)
        vrn.text = String.format(getString(R.string.text_vrn), extras?.vrn)
        country.text = String.format(getString(R.string.text_country), extras?.country)
        color.text = String.format(getString(R.string.text_color), extras?.color)
        type.text = String.format(getString(R.string.text_type), extras?.type)

    }
}
