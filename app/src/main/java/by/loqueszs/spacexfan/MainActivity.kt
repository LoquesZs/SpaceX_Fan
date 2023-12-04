package by.loqueszs.spacexfan

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.loqueszs.spacexfan.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.addOnDestinationChangedListener(this)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener {
            val currentDestinationID = navController.currentDestination?.id
            when (it.itemId) {
                R.id.rockets -> {
                    if (currentDestinationID != R.id.RocketsFragment) {
                        navController.navigate(
                            R.id.action_toRocketsFragment
                        )
                        it.isChecked = false
                    }
                    return@setOnItemSelectedListener true
                }

                R.id.favorites -> {
                    if (currentDestinationID != R.id.FavoritesFragment && currentDestinationID != R.id.AuthorizationFragment) {
                        navController.navigate(
                            R.id.action_toAuthFragment
                        )
                        it.isChecked = false
                    }
                }

                R.id.launches -> {
                    if (currentDestinationID != R.id.LaunchesFragment) {
                        navController.navigate(
                            R.id.action_toLaunchesFragment
                        )
                        it.isChecked = false
                    }
                    return@setOnItemSelectedListener true
                }
            }
            it.isChecked = true
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Log.d(
            "Navigation",
            "Destination changed: ${controller.previousBackStackEntry?.destination?.label} to ${destination.label}"
        )
        when (destination.id) {
            R.id.RocketsFragment -> {
                setBottomNavItemSelected(R.id.rockets)
            }

            R.id.FavoritesFragment -> {
                setBottomNavItemSelected(R.id.favorites)
            }

            R.id.LaunchesFragment -> {
                setBottomNavItemSelected(R.id.launches)
            }

            R.id.AuthorizationFragment -> {
                setBottomNavItemSelected(R.id.favorites)
            }

            else -> {
                binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

    private fun setBottomNavItemSelected(@IdRes id: Int) {
        if (binding.bottomNavigation.visibility != View.VISIBLE) binding.bottomNavigation.visibility = View.VISIBLE
        binding.bottomNavigation.menu.findItem(id).isChecked = true
    }
}
