package com.andela.gads.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.andela.gads.R
import com.andela.gads.api.Learner
import com.andela.gads.databinding.LearnersItemBinding

class LearnersAdapter(
        private val learners: List<Learner>
) : RecyclerView.Adapter<LearnersAdapter.LearnerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LearnerViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.learners_item,
                            parent,
                            false

                    )
            )

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        holder.learnersItemBinding.learner = learners[position]

    }

    override fun getItemCount() = learners.size

    inner class LearnerViewHolder(
            val learnersItemBinding: LearnersItemBinding
    ) : RecyclerView.ViewHolder(learnersItemBinding.root)

}