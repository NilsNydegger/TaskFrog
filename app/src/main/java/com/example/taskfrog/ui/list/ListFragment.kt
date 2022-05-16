package com.example.taskfrog.ui.list

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentListBinding
import com.example.taskfrog.room.FrogList
import com.example.taskfrog.room.FrogListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var listFloatingActionButton : FloatingActionButton
    private lateinit var listRecyclerView : RecyclerView
    private lateinit var itemList : List<FrogList>
    private lateinit var listAdapter: ListAdapter
    private lateinit var listViewModel: FrogListViewModel
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listViewModel = ViewModelProvider(this)[FrogListViewModel::class.java]

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    //TODO Change Data Retrieval to Room DB Access
    override fun onViewCreated(listItemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(listItemView, savedInstanceState)
        itemList = ArrayList()
        listFloatingActionButton = view?.findViewById(R.id.list_fab)!!
        listRecyclerView = view?.findViewById(R.id.recyclerView)!!
        //listAdapter = ListAdapter(this.requireContext(),itemList){ position -> onListItemClick(position)}
        listRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        listRecyclerView.adapter = listAdapter
        listFloatingActionButton.setOnClickListener {
            insertList()
        }

    }

    //TODO delete this code segment if everything works
    /*
    private fun addList() {
        val inflater = LayoutInflater.from(this.requireContext())
        val v = inflater.inflate(R.layout.add_list, null)
        val listName = v.findViewById<EditText>(R.id.listName)

        val addDialog = AlertDialog.Builder(this.requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_ ->
            val name = listName.text.toString()
            //itemList.add(ListData(name))
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
    } */

    //TODO Inserting Lists with this Method
    private fun insertList(){
        val inflater = LayoutInflater.from(this.requireContext())
        val v = inflater.inflate(R.layout.add_list, null)
        val addListName = v.findViewById<EditText>(R.id.listName)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)

        addDialog.setPositiveButton("ye") { dialog, _ ->

            val listName = addListName.text.toString()

            if (inputCheck(listName)) {
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