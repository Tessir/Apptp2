package com.gmail.tfahmiali.neighbors.fragements

import android.app.AlertDialog
// import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.tfahmiali.neighbors.NavigationListener
import com.gmail.tfahmiali.neighbors.R
import com.gmail.tfahmiali.neighbors.adapters.ListNeighborHandler
import com.gmail.tfahmiali.neighbors.adapters.ListNeighborsAdapter
import com.gmail.tfahmiali.neighbors.data.NeighborRepository
import com.gmail.tfahmiali.neighbors.models.Neighbor
//import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        val addNeighbor = view.findViewById<FloatingActionButton>(R.id.addNeighbor)
        (activity as? NavigationListener)?.updateTitle(R.string.list_name)
        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighbourFragment())
        }
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )

        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh()
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {

        activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.Confirmation_Message)
                .setPositiveButton(
                    R.string.btn_oui
                ) { _, _ ->
                    NeighborRepository.getInstance().removeNeighbours(neighbor)
                    refresh()
                }
                .setNegativeButton(
                    R.string.btn_non
                ) { _, _ ->
                    // User cancelled the dialog
                }
            // Create the AlertDialog object and return it
            builder.create().show()
        }
    }

    fun refresh() {
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }

}
