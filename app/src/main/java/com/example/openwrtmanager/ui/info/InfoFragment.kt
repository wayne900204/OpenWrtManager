package com.example.openwrtmanager.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.openwrtmanager.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private val TAG = InfoFragment::class.qualifiedName
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        // TODO: Use the ViewModel
//        binding.addIdentity.setOnClickListener {
//            findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToAddInfoFragment())
//        }
    }



}