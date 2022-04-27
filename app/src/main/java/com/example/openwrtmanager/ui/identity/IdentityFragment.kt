package com.example.openwrtmanager.ui.identity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import com.example.openwrtmanager.databinding.FragmentIdentityBinding
import com.example.openwrtmanager.com.example.openwrtmanager.utils.AnyViewModelFactory


class IdentityFragment : Fragment() {

    private val TAG = IdentityFragment::class.qualifiedName
    private var _binding: FragmentIdentityBinding? = null
    private val binding get() = _binding!!

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

        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val identityItemRepo = IdentityItemRepository(todoItemDb)
        val viewModelFactory = AnyViewModelFactory {
            IdentityViewModel(identityItemRepo)
        }
        val identityViewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(IdentityViewModel::class.java)

        val adapter = IdentityAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        identityViewModel.todoLiveData.observe(
            viewLifecycleOwner,
            Observer { todos: List<IdentityItem> -> adapter.updateAdapterData(todos) })

        binding.addIdentity.setOnClickListener {
            findNavController().navigate(IdentityFragmentDirections.actionIdentityFragmentToAddIdentityFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
