package com.veryable.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.databinding.AccountsRecyclerItemBinding
import com.veryable.android.view.datatypes.AccountViewEntity
import com.veryable.android.view.fragments.HomeViewFragmentDirections

/*
* Recycler View Adapter for HomeViewFragement bank and card accounts recycler view
* */
class RecyclerViewAdapter(private val data: List<AccountViewEntity>, private val navController: NavController): RecyclerView.Adapter<RecyclerViewAdapter.AccountsViewHolder>() {

    inner class AccountsViewHolder(val binding: AccountsRecyclerItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //Inflate Recycler Item Binding
        val listItemBinding = AccountsRecyclerItemBinding.inflate(inflater, parent, false)
        return AccountsViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        //Data Binding Account Details to Recycler View Item
        holder.binding.bindingRecyclerItemTitle = data[position].accountName
        holder.binding.bindingRecyclerItemDesc = data[position].desc
        holder.binding.bindingRecyclerItemType = data[position].transferType

        //Update Account Icon
        holder.binding.accountIcon.setImageResource(data[position].icon)

        //Set On Click Lister to Display Account Details
        holder.binding.itemWindow.setOnClickListener {
            //Pass SafeArgs to Detail View Fragment
            val action = HomeViewFragmentDirections.actionHomeViewFragmentToDetailViewFragment(accountName = data[position].accountName, accountDesc = data[position].desc, accountIcon = data[position].icon )
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}