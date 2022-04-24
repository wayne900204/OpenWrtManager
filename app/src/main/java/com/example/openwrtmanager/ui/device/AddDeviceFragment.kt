package com.example.openwrtmanager.ui.device


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.openwrtmanager.R
import com.example.openwrtmanager.databinding.AddDeviceFragmentBinding
import com.example.openwrtmanager.databinding.FragmentDeviceBinding

class AddDeviceFragment : Fragment() {

    private val TAG = "AddDeviceFragment"
    private lateinit var addDeviceViewModel: AddDeviceViewModel
    private lateinit var viewModel: AddDeviceViewModel
    private var _binding: AddDeviceFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDeviceViewModel =
            ViewModelProvider(this).get(AddDeviceViewModel::class.java)

        _binding = AddDeviceFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.add_router_dropdown_menu, items)
        (binding.dropdownMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
//      binding.addRouter.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//       }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddDeviceViewModel::class.java)
        // TODO: Use the ViewModel


    }


}