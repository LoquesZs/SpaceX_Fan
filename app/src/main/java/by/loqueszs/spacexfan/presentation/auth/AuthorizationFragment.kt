package by.loqueszs.spacexfan.presentation.auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.loqueszs.spacexfan.R
import by.loqueszs.spacexfan.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthorizationViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.emailInputLayout.editText?.setText(viewModel.login)
        binding.passwordInputLayout.editText?.setText(viewModel.password)

        viewModel.result.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Failure -> {
                    binding.progressIndicator.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.authorization_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is UIState.Success -> {
                    binding.progressIndicator.visibility = View.GONE
                    navController.popBackStack()
                    navController.navigate(R.id.FavoritesFragment)
                }
                is UIState.Loading -> {
                    binding.progressIndicator.visibility = View.VISIBLE
                }
                null -> {}
            }
        }

        binding.passwordInputLayout.editText?.setOnEditorActionListener { textView, i, keyEvent ->
            when (i) {
                EditorInfo.IME_ACTION_DONE -> {
                    viewModel.auth(
                        binding.emailInputLayout.editText?.text.toString(),
                        binding.passwordInputLayout.editText?.text.toString()
                    )
                    false
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        checkAndAuthenticate {
            navController.popBackStack()
            navController.navigate(R.id.FavoritesFragment)
        }
        binding.emailInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                binding.emailInputLayout.error = null
            } else {
                binding.emailInputLayout.error = getString(R.string.invalid_email_address)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.emailInputLayout.editText?.text?.let { login ->
            viewModel.setLogin(login.toString())
        }
        binding.passwordInputLayout.editText?.text?.let { password ->
            viewModel.setPassword(password.toString())
        }
    }

    private fun checkAndAuthenticate(onSuccess: () -> Unit) {
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                showBiometricPrompt(
                    onSuccess = onSuccess,
                    onError = { code, message ->
                        Toast.makeText(requireContext(), "$code:$message", Toast.LENGTH_LONG).show()
                    },
                    onFail = {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.authentication_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.biometric_hardware_is_unavailable),
                    Toast.LENGTH_LONG
                ).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_biometric_hardware),
                    Toast.LENGTH_LONG
                ).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.biometric_none_enrolled),
                    Toast.LENGTH_LONG
                ).show()
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
