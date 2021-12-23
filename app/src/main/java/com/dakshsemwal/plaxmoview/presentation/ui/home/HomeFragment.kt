package com.dakshsemwal.plaxmoview.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dakshsemwal.plaxmoview.R
import com.dakshsemwal.plaxmoview.common.EndlessRecyclerViewScrollListener
import com.dakshsemwal.plaxmoview.databinding.FragmentHomeBinding
import com.dakshsemwal.plaxmoview.domain.model.Movie
import com.dakshsemwal.plaxmoview.presentation.ui.adapters.MovieAdapter
import com.dakshsemwal.plaxmoview.presentation.ui.adapters.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var list: ArrayList<Movie>
    private lateinit var temp: ArrayList<Movie>

    private lateinit var adapter: MovieAdapter

    var myViewPager2: ViewPager2? = null
    private var cardAdapter: PagerAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var page = 1
    private var totalInThisPage: Int = 0
    private var last_page: Int = 10
    private val perpage = 20
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    //Initialize ui Component
    private fun setupUI() {
        list = ArrayList()
        temp = ArrayList()
        _binding!!.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        adapter = MovieAdapter(list)
        adapter.SetOnItemClickListener(
            object : MovieAdapter.ListItemClickListener {
                override fun onItemClick(listItem: Movie, position: Int) {
                    val directions =
                        HomeFragmentDirections.actionNavHomeToNavGallery(movieId = listItem.id)
                    findNavController().navigate(directions)
                }
            })

        cardAdapter = PagerAdapter(requireContext(), list)
        _binding!!.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        _binding!!.viewpager.adapter = cardAdapter
        _binding!!.viewpager.offscreenPageLimit = 3

        val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()

        _binding!!.viewpager.setPageTransformer { page, position ->
            val myOffset: Float = position * -(2 * pageOffset + pageMargin)
            when {
                position < -1 -> {
                    page.translationX = -myOffset
                }
                position <= 1 -> {
                    val scaleFactor =
                        0.7f.coerceAtLeast(1 - abs(position - 0.14285715f))
                    page.translationX = myOffset
                    page.scaleY = scaleFactor
                    page.alpha = scaleFactor
                }
                else -> {
                    page.alpha = 0f
                    page.translationX = myOffset
                }
            }
        }
        binding.recyclerView.adapter = adapter
        val scrollListener: EndlessRecyclerViewScrollListener =
            object :
                EndlessRecyclerViewScrollListener(_binding!!.recyclerView.layoutManager as GridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    Log.e("TotalPage,","$totalInThisPage")
                    if (totalInThisPage == perpage) {
                        if (this@HomeFragment.page != last_page) {
                            this@HomeFragment.page += 1
                            homeViewModel.getMovie(this@HomeFragment.page)
                        }
                    }
                }
            }
        binding.recyclerView.addOnScrollListener(scrollListener)
    }

    private fun setupObservers() {
        homeViewModel.state.observe(requireActivity(), {
            it.let { resource ->
                if (resource.isLoading) {

                } else {
                    if (resource.error.isNotBlank()) {
                    } else {
                        page = resource.movieData?.page ?: 0
                        totalInThisPage = resource.movieList?.size ?: 0
                        resource.movieList?.let { movieList ->
                            list.addAll(movieList)
                            adapter.submitList(list)
                            cardAdapter?.submitList(list)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}