package com.example.openwrtmanager.ui.device


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.openwrtmanager.R
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.AddDeviceViewModel
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.DeviceUI
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.DeviceItemRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.AuthenticateRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network.ApiClient
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import com.example.openwrtmanager.databinding.AddDeviceFragmentBinding
import com.example.openwrtmanager.com.example.openwrtmanager.utils.AnyViewModelFactory
import com.example.openwrtmanager.com.example.openwrtmanager.utils.Status
import com.example.openwrtmanager.com.example.openwrtmanager.utils.isNetworkAvailable

import java.util.*

class AddDeviceFragment : Fragment() {

    private val TAG = AddDeviceFragment::class.qualifiedName
    private var _binding: AddDeviceFragmentBinding? = null
    private val binding get() = _binding!!

    val args: AddDeviceFragmentArgs by navArgs()
    private lateinit var deviceViewModel: DeviceViewModel
    private lateinit var adddeviceViewModel: AddDeviceViewModel
    private lateinit var adapter: ArrayAdapter<String>
    var deviceUI: DeviceUI? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddDeviceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setInitData()
        setUiWithData()
        saveBtnOnClick()
        deleteBtnOnClick()
        useHttpsConnectionOnClick()

        if (args.isEdit) {
            binding.delete.visibility = View.VISIBLE
        }

        binding.test.setOnClickListener {
            if (activity?.baseContext?.let { isNetworkAvailable() }!!) {
                adddeviceViewModel.authenticate("root", "wenyeh90").observe(viewLifecycleOwner) {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                            }
                            Status.ERROR -> {
                                Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }

    }

    private fun setUpViewModel() {
        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val deviceItemRepo = DeviceItemRepository(todoItemDb)
        val identityItemRpo = IdentityItemRepository(todoItemDb)
        val viewModelFactory = AnyViewModelFactory {
            DeviceViewModel(deviceItemRepo, identityItemRpo)
        }

        val a = AuthenticateRepository(ApiClient.apiService)

        val test = AnyViewModelFactory {
            AddDeviceViewModel(a)
        }

        adddeviceViewModel =
            ViewModelProvider(requireActivity(), test).get(AddDeviceViewModel::class.java)

        deviceViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
            DeviceViewModel::class.java
        )
    }

    private fun setInitData() {
        deviceViewModel.identityItemsLiveData.observe(
            viewLifecycleOwner,
            Observer { todos: List<IdentityItem> ->
                val items: Array<String> = todos.map { it ->
                    it.displayName
                }.toTypedArray()
                deviceUI?.onIdentityItemsChange(items)

            }
        )
        if (args.isEdit) {
            deviceViewModel.getDeviceItemByID(args.id).observe(viewLifecycleOwner, Observer { it ->
                deviceUI?.onDeviceItemChange(it)
            })
        }
    }

    private fun setUiWithData() {
        deviceUI = object : DeviceUI {

            override fun onIdentityItemsChange(items: Array<String>) {
                Log.d(TAG, "onIdentityItemsChange: " + "ERROR？？？")
                adapter =
                    ArrayAdapter(requireContext(), R.layout.add_router_dropdown_menu, items)
                (binding.dropdownMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
            }

            override fun onDeviceItemChange(items: DeviceItem) {
                binding.displayInput.setText(items.displayName)
                binding.ipAddressInput.setText(items.address)
                binding.dropdownMenuInput.setText(items.identityUuid)
                binding.portInput.setText(items.port)
                binding.useHttpsConnection.isChecked = items.useHttpsConnection
                binding.ignoreBadCertificate.isChecked = items.ignoreBadCertificate

                if (binding.useHttpsConnection.isChecked) {
                    binding.ignoreBadCertificate.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun saveBtnOnClick() {
        binding.save.setOnClickListener {
            if (!binding.displayInput.text.isNullOrEmpty() && !binding.ipAddressInput.text.isNullOrEmpty() && !binding.portInput.text.isNullOrEmpty() && !binding.dropdownMenuInput.text.isNullOrEmpty()) {
                val display = binding.displayInput.text.toString()
                val address = binding.ipAddressInput.text.toString()
                val identityUuid =
                    (binding.dropdownMenu.editText as AutoCompleteTextView).text.toString()
                val useHttpsConnection = binding.useHttpsConnection.isChecked
                val port = binding.portInput.text.toString()
                val ignoreBadCertificate = binding.ignoreBadCertificate.isChecked
                if (args.isEdit) {
                    deviceViewModel.updateDeviceItemById(
                        display,
                        address,
                        port,
                        identityUuid,
                        useHttpsConnection,
                        ignoreBadCertificate,
                        args.id
                    )
                } else {
                    val item = DeviceItem(
                        uuid = UUID.randomUUID().toString(),
                        displayName = display,
                        address = address,
                        identityUuid = identityUuid,
                        useHttpsConnection = useHttpsConnection,
                        port = port,
                        ignoreBadCertificate = ignoreBadCertificate,
                        createdAt = Date()
                    )
                    deviceViewModel.createDeviceItem(item)
                }
                findNavController().popBackStack()
            }
        }
    }

    private fun deleteBtnOnClick() {
        binding.delete.setOnClickListener {
            deviceViewModel.deleteDeviceItemByID(args.id)
            findNavController().popBackStack()
        }
    }

    private fun useHttpsConnectionOnClick() {
        binding.useHttpsConnection.setOnClickListener {
            if (binding.useHttpsConnection.isChecked) {
                binding.ignoreBadCertificate.visibility = View.VISIBLE
            } else {
                binding.ignoreBadCertificate.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
