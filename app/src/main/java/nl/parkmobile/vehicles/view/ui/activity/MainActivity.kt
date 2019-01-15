package nl.parkmobile.vehicles.view.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import nl.parkmobile.vehicles.R
import androidx.fragment.app.Fragment
import nl.parkmobile.vehicles.util.FragmentDisplayManager
import nl.parkmobile.vehicles.view.ui.fragment.ListerFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val f = supportFragmentManager.findFragmentById(R.id.frameLayout)
            if (f != null) {
                updateTitle(f)
            }
        }

        FragmentDisplayManager().displayView(context = this, NavigateToFragment = ListerFragment(), tag = "ListerFragment")
    }

    fun changeTitle(title: String, backArrow: Boolean) {
        toolbarTitle?.text = title
        if (backArrow) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
        }
    }

    private fun updateTitle(fragment: androidx.fragment.app.Fragment) {
        val fragClassName = fragment.javaClass.name
        when (fragClassName) {
            ListerFragment::class.java.name -> changeTitle(resources.getString(R.string.app_name), false)
        }
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.backStackEntryCount
        when {
            fragments > 1 -> super.onBackPressed()
            fragments == 1 -> {
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val fragments = supportFragmentManager.backStackEntryCount
                if (fragments >= 1) {
                    super.onBackPressed()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
