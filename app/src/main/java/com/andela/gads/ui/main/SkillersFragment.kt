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
import com.andela.gads.api.SkillersApi
import com.andela.gads.api.SkillersRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.skillers_fragment.*

class SkillersFragment : Fragment() {

    companion object {
        fun newInstance() = SkillersFragment()
    }

    private lateinit var factory: SkillersViewModelFactory
    private lateinit var viewModel: SkillersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.skillers_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = SkillersApi()
        val repository = SkillersRepository(api)
        factory = SkillersViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(SkillersViewModel::class.java)
        try {
            viewModel.getSkillers()
        } catch (e: Exception) {
            Log.d("Skiller:",e.message)
            //Snackbar.make(requireView(),e.localizedMessage, Snackbar.LENGTH_LONG).show()

        }
        finally {
            viewModel.skillers.observe(viewLifecycleOwner, Observer { skillers ->
                recycler_view_skillers.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = SkillersAdapter(skillers)
                }
            })
        }

    }

}
