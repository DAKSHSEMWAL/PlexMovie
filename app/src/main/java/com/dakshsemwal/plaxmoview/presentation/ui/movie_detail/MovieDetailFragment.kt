package com.dakshsemwal.plaxmoview.presentation.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dakshsemwal.plaxmoview.databinding.FragmentMovieDetailBinding
import com.dakshsemwal.plaxmoview.domain.model.MovieDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()
    private var _binding: FragmentMovieDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val safeArgs: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver() {
        movieDetailViewModel.getMovieById(safeArgs.movieId)
        movieDetailViewModel.state.observe(requireActivity(), {
            it.let { resource ->
                if (resource.isLoading) {

                } else {
                    if (resource.error.isNotBlank()) {
                    } else {
                        setUpView(resource.movieData)
                    }
                }
            }
        })
    }

    private fun setUpView(movieData: MovieDetails?) {
        _binding?.apply {
            movieData?.let { movie ->
                Glide.with(requireActivity()).load(movie.poster_url).into(expandedImage)
                moviename.text = movie.title
                rating.text = if (movie.adult) "R" else "U/A"
                ratings.text = movie.vote_average.toString()
                details.text = movie.overview
                ratingBar.rating = movie.vote_average.toFloat()
                tagGroup.setTags(movie.tagsList)
                date.text = movie.releaseDate
                timeLimit.text = movie.timeDuration
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}