package com.example.taskfrog.ui.list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var listFab : FloatingActionButton
    private lateinit var listRecyclerView : RecyclerView
    private lateinit var itemList : ArrayList<ListData>
    private lateinit var listAdapter: ListAdapter
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
        itemList = ArrayList()
        listFab = view?.findViewById(R.id.list_fab)!!
        listRecyclerView = view?.findViewById(R.id.recyclerView)!!
        listAdapter = ListAdapter(this.requireContext(),itemList){ position -> onListItemClick(position)}
        listRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        listRecyclerView.adapter = listAdapter
        listFab.setOnClickListener {
            addName()
        }

    }

    private fun addName() {
        val inflater = LayoutInflater.from(this.requireContext())
        val v = inflater.inflate(R.layout.add_list, null)
        val listName = v.findViewById<EditText>(R.id.listName)

        val addDialog = AlertDialog.Builder(this.requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_ ->
            val name = listName.text.toString()
            itemList.add(ListData(name))
            listAdapter.notifyDataSetChanged()
            Toast.makeText(this.requireContext(), "Adding List Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this.requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(this.requireContext(), TaskActivity::class.java).apply {

        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}