package com.example.movies_api.presentation.movie_details

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movies_api.R
import com.example.movies_api.databinding.MovieDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {


    private var _binding:MovieDetailsFragmentBinding?=null
    val binding:MovieDetailsFragmentBinding
        get() = _binding!!

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel

    //Context
    private var mContext: Context? = null

    private val args:MovieDetailsFragmentArgs by navArgs()

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MovieDetailsFragmentBinding.inflate(inflater,container,false)
        return _binding?.root
       // return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        args.movieId?.let {
            Log.d("MovieDetailsFragment","it: $it")

            args.type?.let { it2 ->
                Log.d("MovieDetailsFragment","it2: $it2")

                viewModel.setSeriesMovies(it2)

                if (it2=="series"){
                    viewModel.getSeriesDetails(it,"67565327",it2)

                    lifecycle.coroutineScope.launchWhenCreated {
                        viewModel.seriesDetails.collect {
                            if (it.isLoading){
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            if (it.error.isNotBlank()){
                                Log.d("MovieDetailsFragment","it.error: ${it.error}")
                                binding.progressBar.visibility = View.INVISIBLE
                            }

                            it.data?.let {
                                Log.d("MovieDetailsFragment"," series it.data: ${it}")
                                binding.progressBar.visibility = View.INVISIBLE
                                binding.seriesDetails = it

                            }
                        }
                    }
                }else{
                    viewModel.getMovieDetails(it,"67565327",it2)
                    lifecycle.coroutineScope.launchWhenCreated {
                        viewModel.movieDetails.collect {
                            if (it.isLoading){
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            if (it.error.isNotBlank()){
                                Log.d("MovieDetailsFragment","it.error: ${it.error}")
                                binding.progressBar.visibility = View.INVISIBLE
                            }

                            it.data?.let {
                                Log.d("MovieDetailsFragment"," movies it.data: ${it}")
                                binding.progressBar.visibility = View.INVISIBLE
                                binding.movieDetails = it
                            }
                        }

                    }
                }

            }


        }

      /*  lifecycle.coroutineScope.launchWhenCreated {
            viewModel.movieDetails.collect {
                if (it.isLoading){
                    binding.progressBar.visibility = View.VISIBLE
                }

                if (it.error.isNotBlank()){
                    Log.d("MovieDetailsFragment","it.error: ${it.error}")
                    binding.progressBar.visibility = View.INVISIBLE
                }

                it.data?.let {
                    Log.d("MovieDetailsFragment"," movies it.data: ${it}")
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.movieDetails = it
                }
            }

            viewModel.seriesDetails.collect {
                if (it.isLoading){
                    binding.progressBar.visibility = View.VISIBLE
                }

                if (it.error.isNotBlank()){
                    Log.d("MovieDetailsFragment","it.error: ${it.error}")
                    binding.progressBar.visibility = View.INVISIBLE
                }

                it.data?.let {
                    Log.d("MovieDetailsFragment"," series it.data: ${it}")
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.seriesDetails = it

                }
            }
        }*/

        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }

    }




}