package com.example.taskfrog.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
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
    private lateinit var listAdapter: ListAdapter
    private lateinit var mFrogListViewModel: FrogListViewModel
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        var frogListId = 0
        var frogListName = "Tasks"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(listItemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(listItemView, savedInstanceState)
        listFloatingActionButton = view?.findViewById(R.id.list_fab)!!
        listRecyclerView = view?.findViewById(R.id.recyclerView)!!
        listAdapter = ListAdapter(this.requireContext(), this){ position -> onListItemClick(position)}
        listRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        listRecyclerView.adapter = listAdapter

        mFrogListViewModel = ViewModelProvider(this).get(FrogListViewModel::class.java)
        mFrogListViewModel.getAllLists.observe(viewLifecycleOwner) {
                frogLists -> listAdapter.setFrogLists(frogLists)
        }
        listFloatingActionButton.setOnClickListener {
            addList()
        }

    }

    private fun addList() {
        val inflater = LayoutInflater.from(this.requireContext())
        val v = inflater.inflate(com.example.taskfrog.R.layout.add_list, null)
        val listName = v.findViewById<EditText>(com.example.taskfrog.R.id.listName)

        val addDialog = AlertDialog.Builder(this.requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_ ->
            val name = listName.text.toString()
            val frogList = FrogList(null, name)
            mFrogListViewModel.addFrogList(frogList)
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
        val item = listRecyclerView[position]
        val listIdElement = item.findViewById<TextView>(R.id.list_id)
        val listIdText = listIdElement.text.toString()
        val listId = listIdText.toInt()
        frogListId = listId
        frogListName = item.findViewById<TextView>(R.id.mTitle).text.toString()
        val taskFragment = TaskFragment()
        taskFragment.setListId(listId)
        findNavController().navigate(R.id.action_navigation_list_to_taskFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}