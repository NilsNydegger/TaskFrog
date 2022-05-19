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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentListBinding
import com.example.taskfrog.room.FrogList
import com.example.taskfrog.room.FrogListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var listViewModel: FrogListViewModel
    private lateinit var listFloatingActionButton : FloatingActionButton
    private lateinit var listRecyclerView : RecyclerView
    private lateinit var itemList : List<FrogList>
    private val adapter = ListAdapter()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listViewModel = ViewModelProvider(this)[FrogListViewModel::class.java]
        listViewModel.getAllLists.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }

        return binding.root
    }

    //TODO Change Data Retrieval to Room DB Access
    override fun onViewCreated(listItemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(listItemView, savedInstanceState)
        itemList = ArrayList()
        listFloatingActionButton = view?.findViewById(R.id.list_fab)!!
        listRecyclerView = view?.findViewById(R.id.recyclerView)!!
        listRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        listFloatingActionButton.setOnClickListener {
            insertList()
        }

    }

    //This Method allows for insertion of Lists to Room Database and confirmation via Toast
    private fun insertList(){
        val inflater = LayoutInflater.from(this.requireContext())
        val v = inflater.inflate(R.layout.add_list, null)
        val addListName = v.findViewById<EditText>(R.id.listName)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)

        addDialog.setPositiveButton("ye") { dialog, _ ->

            val listName = addListName.text.toString()

            if (!inputCheck(listName)) {
                val frogList = FrogList(0, listName)
                listViewModel.addFrogList(frogList)
                if (listName == "forg") {
                    Toast.makeText(
                        requireContext(), "Good job forg <3", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(
                        requireContext(),"Successfully added $listName", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    requireContext(), "Please add a List Name", Toast.LENGTH_LONG).show()
            }

            adapter.setData(adapter.listOfFrogLists)

            dialog.dismiss()

        }

        addDialog.setNegativeButton("no"){
                dialog, _ ->
            dialog.dismiss()
            Toast.makeText(
                requireContext(), "List was not created qwq", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    //This Method checks the input of the user
    private fun inputCheck(addListName: String) : Boolean{
        return addListName.isEmpty()
    }

    private fun navigateBack(){

    }

    //TODO Use Fragment instead of Intent
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