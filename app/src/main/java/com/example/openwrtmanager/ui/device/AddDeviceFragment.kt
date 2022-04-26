package com.example.openwrtmanager.ui.device


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.openwrtmanager.R
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.OnDropDownListener
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.DeviceItemRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import com.example.openwrtmanager.databinding.AddDeviceFragmentBinding
import com.example.openwrtmanager.utils.AnyViewModelFactory
import java.util.*

class AddDeviceFragment : Fragment(){

    private val TAG = "AddDeviceFragment"


    private var _binding: AddDeviceFragmentBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: DeviceViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddDeviceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val deviceItemRepo = DeviceItemRepository(todoItemDb)
        val identityItemRpo = IdentityItemRepository(todoItemDb)
        val viewModelFactory = AnyViewModelFactory {
            DeviceViewModel(deviceItemRepo,identityItemRpo)
        }
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
            DeviceViewModel::class.java
        )

        setDropDwonData()
        saveBtnOnClick()

    }

    private fun setDropDwonData() {
        var onDropDownListener: OnDropDownListener? = null
        viewModel.identityItemsLiveData.observe(
            viewLifecycleOwner,
            Observer { todos: List<IdentityItem> ->
                val items :Array<String> = todos.map { it ->
                    it.displayName
                }.toTypedArray()
                Log.d(TAG, "onViewCreated: "+items[0])
                val adapter = ArrayAdapter(requireContext(), R.layout.add_router_dropdown_menu, items)
                (binding.dropdownMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
//                onDropDownListener?.onChange(items)
            }
        )
//        onDropDownListener = object : OnDropDownListener{
//            override fun onChange(items: Array<String>) {
//                val adapter = ArrayAdapter(requireContext(), R.layout.add_router_dropdown_menu, items)
//                (binding.dropdownMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
//            }
//        }
    }


    private fun saveBtnOnClick() {
        binding.save.setOnClickListener {
            val item = DeviceItem(
                uuid = UUID.randomUUID().toString(),
                displayName = binding.displayInput.text.toString(),
                address = binding.ipAddressInput.text.toString(),
                identityUuid = (binding.dropdownMenu.editText as AutoCompleteTextView).text.toString(),
                useHttpsConnection = binding.useHttpsConnection.isChecked,
                port = binding.portInput.text.toString(),
                ignoreBadCertificate = binding.ignoreBadCertificate.isChecked,
                createdAt = Date()
            )
            viewModel.createNewTodo(item)
            findNavController().popBackStack()
        }

    }


}