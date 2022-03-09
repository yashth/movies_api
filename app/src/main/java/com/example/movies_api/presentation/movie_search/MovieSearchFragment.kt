package com.example.movies_api.presentation.movie_search

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movies_api.R
import com.example.movies_api.common.ConnectionReceiver
import com.example.movies_api.databinding.LayoutMovieSearchBinding
import com.example.movies_api.databinding.MovieSearchFragmentBinding
import com.example.movies_api.domain.model.MovieTable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_movie_search.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieSearchFragment : Fragment() {

    private var _binding:MovieSearchFragmentBinding?=null

    val binding:MovieSearchFragmentBinding
        get() = _binding!!

    var movieSearchAdapter = MovieSearchAdapter()

    private lateinit var cld: ConnectionReceiver
    var internetConnected = false

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    private lateinit var viewModel: MovieSearchViewModel

    //Context
    private var mContext:Context? = null

    var internetDialog:AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MovieSearchFragment","onAttach context $context")
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MovieSearchFragment","onDetach context $context")
        mContext = null
    }

    override fun onResume() {
        super.onResume()
        Log.d("MovieSearchFragment","onResume")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MovieSearchFragmentBinding.inflate(inflater,container,false)
        Log.d("MovieSearchFragment","onCreateView _binding: $_binding")

        return _binding?.root

       // return inflater.inflate(R.layout.movie_search_fragment, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieSearchViewModel::class.java)

        viewModel.setupDatabase(mContext!!)

        checkNetworkConnection()

        binding.searchButton.setOnClickListener {
            showSearchDialog()
        }

        binding.favRecycler.apply {
            adapter = movieSearchAdapter
        }

        viewModel.favMovies.observe(viewLifecycleOwner,{
            movieSearchAdapter.setContentList(it as MutableList<MovieTable>)
        })

        movieSearchAdapter.itemClickListener {
            Log.d("MovieSearchFragment","it.imdbID: ${it.imdbID}")
            findNavController().navigate(MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieDetailsFragment(movieId = it.imdbID,type = it.Type))
        }


    }

    @InternalCoroutinesApi
    private fun showSearchDialog(){

       var alertDialog = AlertDialog.Builder(mContext)

        var itemView:View = LayoutInflater.from(mContext).inflate(R.layout.layout_movie_search,null)
        var _dialogBinding:LayoutMovieSearchBinding?= DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.layout_movie_search,null,false)

        Log.d("MovieSearchFragment","_dialogBinding:$_dialogBinding")
        alertDialog.setView(itemView)
        var dialog = alertDialog.create()
        dialog.setContentView(_dialogBinding!!.root)
        dialog.show()

        itemView.search_movie.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                p0?.let {

                    Log.d("MovieSearchFragment","it:$it")
                    viewModel.searchMovie(it,"67565327")
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        itemView.addToFav.setOnClickListener {


            if (_dialogBinding.movieData!=null){
                var movieTable = MovieTable(_dialogBinding!!.movieData!!.Title,
                    _dialogBinding!!.movieData!!.Poster,
                    _dialogBinding!!.movieData!!.Plot,
                    _dialogBinding!!.movieData!!.Genre,
                    _dialogBinding!!.movieData!!.imdbRating,
                    _dialogBinding!!.movieData!!.Type,
                    _dialogBinding!!.movieData!!.imdbID,
                    true,
                    false)

                GlobalScope.launch {
                    viewModel.insert(movieTable)
                }
            }

        }


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.movieSearchList.collect {
                if (it.isLoading){
                    Log.d("MovieSearchFragment","lifecycle isLoading")
                }

                if (it.error.isNotBlank()){
                    Log.d("MovieSearchFragment","lifecycle error: ${it.error.toString()}")
                }

                it.data?.let {
                    Log.d("MovieSearchFragment","lifecycle it.data $it")

                    _dialogBinding.movieData = it

                    Log.d("MovieSearchFragment","lifecycle _dialogBinding!!.movieData ${_dialogBinding!!.movieData}")

                    itemView.movieDetailTitle.text = it.Title
                    itemView.movieDetailGenre.text = it.Genre
                   // itemView.movieDetailInfo.text = it.Plot
                    itemView.movieDetailRating.rating = it.imdbRating.toFloat()
                    itemView.movieDetailRatingText.text = it.imdbRating
                    Glide.with(itemView).load(it.Poster).into(itemView.movieDetailPoster)
                }
            }
        }
    }

    private fun checkNetworkConnection() {

        cld = ConnectionReceiver(requireActivity().application)
        cld.observe(viewLifecycleOwner,{isConnected ->
            if (isConnected){
                internetConnected = true
                Log.d("MovieSearchFragment","checkNetworkConnection internetConnected: $internetConnected")

                if (internetDialog!=null){
                    internetDialog!!.dismiss()
                }

            }else{
                internetConnected = false
                Log.d("MovieSearchFragment","checkNetworkConnection internetConnected: $internetConnected")

                internetDialog =  AlertDialog.Builder(mContext).create()

                var itemView:View = LayoutInflater.from(mContext).inflate(R.layout.layout_network_state,null)

                internetDialog!!.setView(itemView)
                internetDialog!!.setCancelable(false)
                internetDialog!!.show()


            }

        })
    }



}