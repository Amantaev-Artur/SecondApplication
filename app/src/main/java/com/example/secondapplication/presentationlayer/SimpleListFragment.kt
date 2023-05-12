package com.example.secondapplication.presentationlayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondapplication.R
import com.example.secondapplication.businesslayer.CoroutineItemProvider
import com.example.secondapplication.datalayer.IItemAccessor2
import com.example.secondapplication.datalayer.RetrofitProvider
import com.example.secondapplication.presentationlayer.adapters.ItemAdapter

class SimpleListFragment : Fragment() {
    protected val provider by lazy { initializeProvider() }
    protected val itemAdapter = ItemAdapter()

    private val accessor = RetrofitProvider().provide().create(IItemAccessor2::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
            adapter = itemAdapter
        }

        provider.load {
            itemAdapter.submitList(it)
        }
    }

    private fun initializeProvider(): CoroutineItemProvider {
        return CoroutineItemProvider(accessor)
    }


    companion object {
        protected const val COLUMN_COUNT = 3

        fun newInstance(): SimpleListFragment {
            return SimpleListFragment()
        }
    }
}