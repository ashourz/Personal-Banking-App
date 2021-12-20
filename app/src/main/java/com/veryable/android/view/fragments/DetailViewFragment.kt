package com.veryable.android.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.DataViewModel
import com.veryable.android.R
import com.veryable.android.databinding.FragmentDetailViewBinding

/*
* Details View Fragment
* Provides function to details view
* */
class DetailViewFragment : Fragment() {

    companion object {
        fun newInstance() = DetailViewFragment()
        private lateinit var dataViewModel: DataViewModel
        private lateinit var recyclerManager: RecyclerView.LayoutManager
    }
    //Generated Data Binding Classes
    private lateinit var binding : FragmentDetailViewBinding
    private val args: DetailViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate Databinding View
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_detail_view, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recycler View  Managers
        recyclerManager = LinearLayoutManager(this.context)

        //Get instance of DataViewModel
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        val actionReturnHome  = DetailViewFragmentDirections.actionDetailViewFragmentToHomeViewFragment()

        //Set Data Binding Detail Item Values
        binding.bindingDetailsTitle  = args.accountName
        binding.bindingDetailsDesc = args.accountDesc
        //Set Details Icon value
        binding.detailsIcon.setImageResource( args.accountIcon)

        //Set Back Button onClick behavior
        binding.backButton.setOnClickListener {
            findNavController().navigate(actionReturnHome)
        }
        //Set Done Button onClick behavior
        binding.doneButton.setOnClickListener {
            findNavController().navigate(actionReturnHome)
        }


    }


}