package com.andela.gads.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.andela.gads.R
import com.andela.gads.api.LearnersApi
import com.andela.gads.api.LearnersRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.learners_fragment.*

class LearnersFragment : Fragment() {

    companion object {
        fun newInstance() = LearnersFragment()
    }

    private lateinit var factory: LearnersViewModelFactory
    private lateinit var viewModel: LearnersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.learners_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = LearnersApi()
        val repository = LearnersRepository(api)
        factory = LearnersViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(LearnersViewModel::class.java)
        try {
            viewModel.getLearners()
        } catch (e: Exception) {
            //Snackbar.make(requireView(),e.localizedMessage,Snackbar.LENGTH_LONG).show()
            Log.d("Learner:",e.message)
        }
        finally{
            viewModel.learners.observe(viewLifecycleOwner, Observer { learners ->
                recycler_view_learners.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = LearnersAdapter(learners)
                }
            })
        }

    }

}
