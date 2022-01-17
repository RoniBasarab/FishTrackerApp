package com.hit.fishtracker.data.views.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hit.fishtracker.data.adapter.FishAdapter
import com.hit.fishtracker.data.database.FishDatabase
import com.hit.fishtracker.data.model.Fish
import com.hit.fishtracker.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mainFragmentViewModel: MainFragmentViewModel by viewModel()
    private lateinit var fishAdapter: FishAdapter

    // Valid for OnCreateView and OnDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater,container,false)
        setupUI()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupUI()
    {
        fishAdapter = FishAdapter()
        binding.rvFishRecycler.adapter = fishAdapter
        binding.rvFishRecycler.layoutManager = GridLayoutManager(this.context,2)

        setupObserver()
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.toString() != "All")
                mainFragmentViewModel.getFish(binding.etSearch.text.toString())
            else
                mainFragmentViewModel.getFish("All")
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setupObserver()
    {
        val fishObserver = Observer<MutableList<Fish>> { fishes ->

            FishDatabase.db!!.fishDao().addAllFish(fishes)
            fishAdapter.notifyDataSetChanged()

        }
        mainFragmentViewModel.repository.observe(viewLifecycleOwner,fishObserver)
    }
}
