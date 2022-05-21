package com.example.openwrtmanager.ui.information

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network.ApiClient
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network.ApiService
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network.InfoService
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.AuthenticateRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.DeviceItemRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.network.InfoClient
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.repository.InfoRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.InfoAdapter
import com.example.openwrtmanager.com.example.openwrtmanager.utils.AnyViewModelFactory
import com.example.openwrtmanager.databinding.FragmentIdentityBinding


class IdentityFragment : Fragment() {

    private val TAG = IdentityFragment::class.qualifiedName
    private var _binding: FragmentIdentityBinding? = null
    private val binding get() = _binding!!

    private lateinit var infoViewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        val pref = requireActivity().getSharedPreferences("OpenWrt", Context.MODE_PRIVATE)

        val adapter = InfoAdapter()
        binding.infoRecyclerView.adapter = adapter
        binding.infoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.submitList(mutableListOf())

        infoViewModel.init(pref.getInt("device_select_item_id", -1))
        infoViewModel.lceLiveData.observe(viewLifecycleOwner, Observer { lce ->
            when (lce) {
                is LCE.Content -> adapter.submitList(lce.content.toMutableList())
                is LCE.Error -> Toast.makeText(requireContext(), (lce.throwable.toString()), Toast.LENGTH_SHORT).show()
                else -> {}
            }
        })
    }

    private fun setUpViewModel() {
        val infoRep = InfoRepository(InfoClient.getInfoRetrofit().create(InfoService::class.java))
        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val deviceItemRepo = DeviceItemRepository(todoItemDb)
        val authenticateRepo = AuthenticateRepository(ApiClient.getRetrofit().create(ApiService::class.java))
        val test = AnyViewModelFactory { InfoViewModel(infoRep, deviceItemRepo, authenticateRepo) }
        infoViewModel = ViewModelProvider(requireActivity(), test).get(InfoViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().viewModelStore.clear()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        infoViewModel.start()
    }

    override fun onPause() {
        super.onPause()
        infoViewModel.stop()
    }
}


