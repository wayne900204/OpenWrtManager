package com.example.openwrtmanager.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network.InfoService
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.network.InfoClient
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.repository.InfoRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.InfoAdapter
import com.example.openwrtmanager.com.example.openwrtmanager.utils.AnyViewModelFactory
import com.example.openwrtmanager.databinding.FragmentIdentityBinding


class IdentityFragment : Fragment() {

    private val TAG = IdentityFragment::class.qualifiedName
    private var _binding: FragmentIdentityBinding? = null
    private val binding get() = _binding!!

    private lateinit var identityViewModel: IdentityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdentityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val a = InfoRepository(InfoClient().getInfoRetrofit().create(InfoService::class.java))
        val test = AnyViewModelFactory {
            IdentityViewModel(a, "3f8eb2c06109827a939650ac15a59bee")
        }

        identityViewModel =
            ViewModelProvider(requireActivity(), test).get(IdentityViewModel::class.java)

        val adapter = InfoAdapter()
        binding.infoRecyclerView.adapter = adapter
        binding.infoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.request.setOnClickListener {}
        identityViewModel.lceLiveData.observe(viewLifecycleOwner, Observer { lce ->
            when (lce) {
                is LCE.Content -> {
                    adapter.submitList(lce.content.toMutableList())
//                    if(lce.content[0].result[1] is Map<*, *>){
//                        Log.d(TAG,"result"+ (lce.content[0].result[1] as Map<*,*>)["memory"])
//                    }
                }
//                is LCE.Content -> Toast.makeText(
//                    requireContext(),
//                    lce.content.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
                is LCE.Error ->
                    Toast.makeText(requireContext(), (", "), Toast.LENGTH_LONG).show()
                else -> {}
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        identityViewModel.start()
    }

    override fun onPause() {
        super.onPause()
        identityViewModel.stop()
    }
}


