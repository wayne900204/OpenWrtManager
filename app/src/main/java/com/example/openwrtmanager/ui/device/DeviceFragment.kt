package com.example.openwrtmanager.ui.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.openwrtmanager.R
import com.example.openwrtmanager.databinding.FragmentDeviceBinding

class DeviceFragment : Fragment() {
    private val TAG = "DeviceFragment"
    private lateinit var deviceViewModel: DeviceViewModel
    private var _binding: FragmentDeviceBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deviceViewModel =
            ViewModelProvider(this).get(DeviceViewModel::class.java)

        _binding = FragmentDeviceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        deviceViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val navController = findNavController(this)
        binding.addRouter.setOnClickListener {
            navController.navigate(R.id.add_device_page)
        }
//      binding.addRouter.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//       }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}