package com.example.taskfrog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentHomeBinding
import com.example.taskfrog.ui.list.ListFragment
import com.example.taskfrog.ui.list.TaskFragment

class HomeFragment : Fragment() {
    private lateinit var listButton : Button
    private lateinit var calendarButton : Button
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listButton = view.findViewById(R.id.ferg)!!
        calendarButton = view.findViewById(R.id.forg)!!

        listButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_list)
        }

        calendarButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_calendar)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}