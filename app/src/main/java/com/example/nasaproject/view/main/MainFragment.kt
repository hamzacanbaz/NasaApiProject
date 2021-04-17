package com.example.nasaproject.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasaproject.NasaModel
import com.example.nasaproject.R
import com.example.nasaproject.adapter.MainRecyclerViewAdapter
import com.example.nasaproject.adapter.RecyclerViewAdapter
import com.example.nasaproject.database.CommentDatabase
import com.example.nasaproject.database.CommentsEntity
import com.example.nasaproject.databinding.FragmentMainBinding
import com.example.nasaproject.service.NasaApi
import com.example.nasaproject.view.comments.CommentViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(),MainRecyclerViewAdapter.OnItemLongClickListen {
    val BASE_URL = "https://api.nasa.gov"
    lateinit var viewmodel: MainViewModel
     lateinit var binding : FragmentMainBinding
    lateinit var adapter : MainRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main,container,false)
        // Inflate the layout for this fragment

        val application = requireNotNull(this.activity).application
        val dataSource = CommentDatabase.getInstance(application).commentDao
        val photoSource = CommentDatabase.getInstance(application).photoDao
        val viewmodelFactory = MainViewModelFactory(photoSource,dataSource,application)
        viewmodel = ViewModelProvider(this,viewmodelFactory).get(MainViewModel::class.java)
         //adapter = MainRecyclerViewAdapter()


        binding.lifecycleOwner = this


        binding.mainViewModel = viewmodel

        viewmodel.refreshData()

        viewmodel.readAllData.observe(viewLifecycleOwner, Observer {

            adapter= MainRecyclerViewAdapter(listener = this)
            adapter.setData(it)
            adapter.notifyDataSetChanged()

            binding.recylerView.layoutManager = GridLayoutManager(requireContext(),2)
            binding.recylerView.adapter = adapter
        })

        binding.swipeLayout.setOnRefreshListener {
            viewmodel.loadData()
            binding.swipeLayout.isRefreshing = false

        }

        //observeNasaObjects()

       // satir 80 109 ba kolay api ya da sqlden çek


        /*adapter.setData(nasa)
        println("x"+nasa.size)
        adapter.notifyDataSetChanged()*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        super.onViewCreated(view, savedInstanceState)
        /*binding.getcomments.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCommentFragment()
            findNavController().navigate(action)
        }*/

       /* binding.makeCommentButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToMakeComment()
            findNavController().navigate(action)

        }*/

    }

    override fun onItemLong(position: Int) {
        val action = MainFragmentDirections.actionMainFragmentToMakeComment(position.toLong())
        findNavController().navigate(action)
        Toast.makeText(context,position.toString(), Toast.LENGTH_LONG).show()
    }

//    private fun observeNasaObjects(){
//        viewmodel.nasa_objects.observe(viewLifecycleOwner, Observer {
//            it.let {
//                adapter= MainRecyclerViewAdapter()
//                adapter.setData(it)
//                adapter.notifyDataSetChanged()
//
//                binding.recylerView.layoutManager = GridLayoutManager(requireContext(),2)
//                binding.recylerView.adapter = adapter
//
//            }
//        })
//    }


//   private fun loadData(){
//        println("xx")
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(NasaApi::class.java)
//
//        val job = CoroutineScope(Dispatchers.IO).launch {
//            val response = retrofit.getData()
//
//            withContext(Dispatchers.Main){
//                if(response.isSuccessful){
//                    //println(response.body())
//                    response.body()?.get("photos")?.asJsonArray?.let { println(it.size()) }
//
//                    for (i in 0..12){
//                        var j =response.body()?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("earth_date")
//                            .toString()
//                        var k = response.body()?.get("photos")?.asJsonArray?.get(i)?.asJsonObject?.get("img_src")
//                            .toString()
//                        println("url"+k)
//                        var nasaModel = NasaModel(j,k)
//                        nasa.add(nasaModel)
//
//
//                    }
//                    adapter.setData(nasa)
//
//                }
//            }
//        }
//
//    }

}