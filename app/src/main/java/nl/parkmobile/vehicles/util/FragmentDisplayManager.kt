package nl.parkmobile.vehicles.util

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import nl.parkmobile.vehicles.R
import nl.parkmobile.vehicles.service.model.vehicles.VehiclesData
import nl.parkmobile.vehicles.util.Constants.EXTRAS_BUNDLE

class FragmentDisplayManager {

    fun displayView(context: Context, NavigateToFragment: androidx.fragment.app.Fragment,
                    tag: String, extras: VehiclesData = VehiclesData()) {
        try {
            val input = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(View(context).windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            val fragmentManager = (context as androidx.fragment.app.FragmentActivity).supportFragmentManager
            val data = Bundle()
            data.putParcelable(EXTRAS_BUNDLE, extras)
            NavigateToFragment.arguments = data

            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, NavigateToFragment, tag)
                    .addToBackStack(tag)
                    .commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}