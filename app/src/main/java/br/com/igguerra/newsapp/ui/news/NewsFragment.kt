package br.com.igguerra.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.igguerra.newsapp.databinding.FragmentNewsBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val newsViewModel: NewsViewModel by viewModels()
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNewsRecyclerView()
        observeUiState()
        newsViewModel.getNews()
    }

    private fun observeUiState() {
        lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.newsUiState.collect {
                    when (it) {
                        is NewsUiState.Loading -> {
                            binding.newsLoading.viewNewsLoadingState.startShimmer()
                            binding.recyclerNews.visibility = View.GONE
                            binding.newsFailure.viewNewsErrorState.visibility = View.GONE
                            binding.newsLoading.shimmerNewsContent.visibility = View.VISIBLE
                        }
                        is NewsUiState.Failure -> {
                            binding.newsLoading.viewNewsLoadingState.stopShimmer()
                            binding.newsLoading.viewNewsLoadingState.visibility = View.GONE
                            binding.newsFailure.viewNewsErrorState.visibility = View.VISIBLE
                            binding.newsFailure.tryAgain.setOnClickListener { newsViewModel.getNews() }
                        }
                        is NewsUiState.Loaded -> {
                            newsAdapter.submitList(it.data.news)
                            binding.newsLoading.viewNewsLoadingState.stopShimmer()
                            binding.newsLoading.viewNewsLoadingState.visibility = View.GONE
                            binding.recyclerNews.visibility = View.VISIBLE
                        }
                        else -> {}
                    }
                }
            }

        }
    }

    private fun setupNewsRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.recyclerNews.apply {
            setHasFixedSize(true)
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}