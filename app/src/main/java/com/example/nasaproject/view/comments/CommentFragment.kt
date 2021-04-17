package com.example.nasaproject.view.comments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nasaproject.R
import com.example.nasaproject.adapter.RecyclerViewAdapter
import com.example.nasaproject.database.CommentDatabase
import com.example.nasaproject.databinding.FragmentCommentBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.InternalCoroutinesApi

class CommentFragment : Fragment() {
    val argum : CommentFragmentArgs by navArgs()
    lateinit var viewmodel : CommentViewModel
    lateinit var binding : FragmentCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(argum.İd)
    }

    @SuppressLint("SetTextI18n")
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_comment,container,false)

        // Inflate the layout for this fragment
        val application = requireNotNull(this.activity).application

        val dataSource = CommentDatabase.getInstance(application).commentDao
        val photoSource = CommentDatabase.getInstance(application).photoDao

        val viewmodelFactory = CommentViewModelFactory(argum.İd,argum.position,photoSource,dataSource,application)

        val adapter = RecyclerViewAdapter()
        val recylerView = binding.recylerView
        recylerView.adapter = adapter
        recylerView.layoutManager = LinearLayoutManager(requireContext())


        viewmodel = ViewModelProvider(this,viewmodelFactory).get(CommentViewModel::class.java)

        /*viewmodel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            println("come$it")
        })*/

        viewmodel.getComment.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            println("come$it")
        })

        viewmodel.getPhoto.observe(viewLifecycleOwner, Observer {
            binding.earthDate.text = "Date:" + it.earth_date
            binding.landingDate.text = "Landing Date:" + it.landing_date
            binding.launchDate.text = "Launch Date:" + it.launch_date
            binding.roverName.text = "Rover Name:" + it.rover_name
            //Picasso.get().load(it.img_src.toString()).into(binding.imageview)
            Glide.with(requireContext()).load(it.img_src?.substring(1, it.img_src!!.length-1) ?: 0).into(binding.imageview)
            println("src"+ (it.img_src?.substring(1, it.img_src!!.length-1) ?: 0))
        })

        binding.lifecycleOwner = this

        binding.commentViewModel = viewmodel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}