package com.example.openwrtmanager.ui.identity

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import com.example.openwrtmanager.databinding.AddIdentityFragmentBinding
import com.example.openwrtmanager.utils.AnyViewModelFactory

class AddIdentityFragment : Fragment() {
    private var _binding: AddIdentityFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val args: AddIdentityFragmentArgs by navArgs()
    private lateinit var identityViewModel: IdentityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddIdentityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setIdentityViewModelWithAppDatabase()
        setAddIdentityFragmentArgs()
        saveBtnOnClick()
        deleteBtnOnClick()
    }

    private fun setIdentityViewModelWithAppDatabase() {
        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val identityItemRepo = IdentityItemRepository(todoItemDb)
        val viewModelFactory = AnyViewModelFactory {
            IdentityViewModel(identityItemRepo)
        }
        identityViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory
            ).get(IdentityViewModel::class.java)
    }

    private fun setAddIdentityFragmentArgs() {
        if (!args.display.isNullOrEmpty()) {
            binding.displayInput.setText(args.display)
        }
        if (!args.username.isNullOrEmpty()) {
            binding.usernameInput.setText(args.username)
        }
        if (!args.password.isNullOrEmpty()) {
            binding.passwordInput.setText(args.password)
        }
        if (args.password == null && args.username == null && args.uuid == null && args.display == null) {
            binding.delete.visibility = View.GONE
        }
    }

    private fun saveBtnOnClick() {
        binding.save.setOnClickListener {
            if (binding.displayInput.text.isNullOrEmpty()) {
                binding.displayInput.setError("hihi-test")
            }
            if (binding.usernameInput.text.isNullOrEmpty()) {
                binding.usernameInput.setError("Username is missing")
            }
            if (binding.passwordInput.text.isNullOrEmpty()) {
                binding.passwordInput.setError("Username is missing")
            }
            if (!binding.usernameInput.text.isNullOrEmpty() && !binding.passwordInput.text.isNullOrEmpty()) {
                if (!args.username.isNullOrEmpty() && !args.password.isNullOrEmpty() && !args.uuid.isNullOrEmpty()) {
                    val display = binding.displayInput.text.toString();
                    val username = binding.usernameInput.text.toString();
                    val password = binding.passwordInput.text.toString();
                    identityViewModel.updateTodo(
                        display = display,
                        username = username,
                        password = password,
                        id = args.id
                    )
                } else {
                    val display = binding.displayInput.text.toString();
                    val username = binding.usernameInput.text.toString();
                    val password = binding.passwordInput.text.toString();
                    identityViewModel.createNewTodo(
                        display = display,
                        username = username,
                        password = password
                    )
                }
                findNavController().popBackStack()
            }

        }
    }

    private fun deleteBtnOnClick() {
        binding.delete.setOnClickListener {
            identityViewModel.deleteIdentityItemByID(args.id)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}