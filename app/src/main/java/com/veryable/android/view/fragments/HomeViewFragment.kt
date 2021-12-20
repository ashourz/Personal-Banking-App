package com.veryable.android.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.DataViewModel
import com.veryable.android.R
import com.veryable.android.databinding.FragmentHomeViewBinding
import com.veryable.android.view.adapter.RecyclerViewAdapter

/*
* Home View Fragment
* Provides function to initial navigation view and bank and card accounts recycler views
* */
class HomeViewFragment : Fragment(){

    companion object {
        fun newInstance() = HomeViewFragment()
        private lateinit var dataViewModel: DataViewModel
        private lateinit var bankAccountsRecyclerManager: RecyclerView.LayoutManager
        private lateinit var cardAccountsRecyclerManager: RecyclerView.LayoutManager
    }
    //Generated Data Binding Class
    private lateinit var binding : FragmentHomeViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate Data Binding View
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_home_view, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recycler View  Managers
        bankAccountsRecyclerManager = LinearLayoutManager(this.context)
        cardAccountsRecyclerManager = LinearLayoutManager(this.context)

        //Get instance of dataViewModel
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        //Update Accounts Database and LiveData
        dataViewModel.updateLiveDataAllAccounts()
        //Observe LiveData accounts feed and apply to account and cards recycle view
        dataViewModel.lifeDataAllAccountsSorted.observe(viewLifecycleOwner, Observer { bankCardAccountList ->
            //Pass data to bank accounts recycler view
            binding.bankRecyclerView.apply {
                adapter = RecyclerViewAdapter(bankCardAccountList.bankAccountList, findNavController())
                layoutManager = bankAccountsRecyclerManager
            }
            //Pass data to card accounts recycler view
            binding.cardRecyclerView.apply {
                adapter = RecyclerViewAdapter(bankCardAccountList.cardAccountList, findNavController())
                layoutManager = cardAccountsRecyclerManager
            }
        })
    }


}