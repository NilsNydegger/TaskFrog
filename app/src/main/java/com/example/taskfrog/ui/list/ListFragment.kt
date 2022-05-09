package com.example.taskfrog.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(listItemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(listItemView, savedInstanceState)
        val listRecyclerview = view?.findViewById<RecyclerView>(R.id.recyclerView)
        listRecyclerview?.layoutManager = LinearLayoutManager(this.context)

        val data = ArrayList<ListItemsViewModel>()

        val adapter = ListAdapter(data)

        listRecyclerview?.adapter = adapter

        val listFab : FloatingActionButton? = view?.findViewById(R.id.list_fab)
        listFab?.setOnClickListener {
            data.add(ListItemsViewModel("List"))
            adapter.notifyDataSetChanged()
        }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}