package by.loqueszs.spacexfan

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.loqueszs.spacexfan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    if (currentDestinationID != R.id.rockets) {
                        navController.navigate(
                            R.id.action_toRocketsFragment
                        )
                        it.isChecked = false
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.favorites -> {
                    if (currentDestinationID != R.id.favorites) {
                        checkAndAuthenticate {
                            navController.popBackStack()
                            navController.navigate(
                                R.id.action_toFavoritesFragment
                            )
                            it.isChecked = false
                        }
                    }
                }
                R.id.launches -> {
                    if (currentDestinationID != R.id.launches) {
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
        Log.d("Navigation", "Destination changed: ${controller.previousBackStackEntry?.destination?.label} to ${destination.label}")
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
            else -> {
                binding.bottomNavigation.visibility = View.GONE
            }
        }
    }

    private fun setBottomNavItemSelected(@IdRes id: Int) {
        if (binding.bottomNavigation.visibility != View.VISIBLE) binding.bottomNavigation.visibility = View.VISIBLE
        binding.bottomNavigation.menu.findItem(id).isChecked = true
    }

    private fun checkAndAuthenticate(onSuccess: () -> Unit) {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                showBiometricPrompt(
                    onSuccess = onSuccess,
                    onError = { code, message ->
                        Toast.makeText(this, "$code:$message", Toast.LENGTH_LONG).show()
                    },
                    onFail = {
                        Toast.makeText(
                            this,
                            getString(R.string.authentication_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(
                    this,
                    getString(R.string.biometric_hardware_is_unavailable),
                    Toast.LENGTH_LONG
                ).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(this, getString(R.string.no_biometric_hardware), Toast.LENGTH_LONG).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Toast.makeText(this, getString(R.string.biometric_none_enrolled), Toast.LENGTH_LONG).show()
                onSuccess()
            }
        }
    }

    private fun showBiometricPrompt(
        onSuccess: () -> Unit,
        onError: (errorCode: Int, errorMessage: String) -> Unit,
        onFail: () -> Unit
    ) {
        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.app_name))
            .setSubtitle(getString(R.string.fingerprint_authentication))
            .setNegativeButtonText(getString(R.string.cancel))
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()
        val biometricPrompt = BiometricPrompt(
            this,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onError(errorCode, errString.toString())
                    Log.d("BIOMETRIC PROMPT", "$errorCode $errString")
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFail()
                }
            }
        )
        biometricPrompt.authenticate(info)
    }
}
