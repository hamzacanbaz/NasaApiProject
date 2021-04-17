package com.example.nasaproject.view.make_comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nasaproject.R
import com.example.nasaproject.database.CommentDatabase
import com.example.nasaproject.databinding.FragmentMakeCommentBinding
import kotlinx.coroutines.InternalCoroutinesApi

class MakeComment : Fragment() {
    val argum : MakeCommentArgs by navArgs()

    lateinit var binding: FragmentMakeCommentBinding;
    lateinit var viewmodel: MakeCommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @InternalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_make_comment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = CommentDatabase.getInstance(application).commentDao

        val viewmodelFactory = MakeCommentViewModelFactory(dataSource, application)

        viewmodel = ViewModelProvider(this, viewmodelFactory).get(MakeCommentViewModel::class.java)

        binding.lifecycleOwner = this
        binding.makeCommentViewModel = viewmodel

        binding.photoId.minValue = 0
        binding.photoId.maxValue = 50
        binding.photoId.value = argum.İd.toInt()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.sendButton.setOnClickListener {
            println(binding.inputTitle.text)
            viewmodel.insertData(
                    binding.nameInput.text.toString(),
                    binding.inputTitle.text.toString(),
                    binding.inputComment.text.toString(),
                    binding.photoId.value
            )
            val action = MakeCommentDirections.actionMakeCommentToMainFragment()
            findNavController().navigate(action)

            println(binding.photoId.value)
        }

    }


}