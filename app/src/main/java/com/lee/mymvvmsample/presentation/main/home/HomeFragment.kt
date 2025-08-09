package com.lee.mymvvmsample.presentation.main.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.textChangedListener
import com.lee.mymvvmsample.common.utils.ImageLoader
import com.lee.mymvvmsample.databinding.FragmentHomeBinding
import com.lee.mymvvmsample.presentation.main.MainActivity
import com.lee.mymvvmsample.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickHandler {
    private val viewModel: HomeViewModel by viewModels()

    private var binding: FragmentHomeBinding? = null
    private var imageAdapter: HomeImageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
            }

        initEvent()

        return binding?.root
    }

    private fun initEvent() {
        binding?.etSearch?.textChangedListener { text ->
            viewModel.onQueryChanged(text)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (imageAdapter == null) {
                        imageAdapter = HomeImageAdapter(this@HomeFragment, ImageLoader(requireContext()))
                        binding?.rcList?.adapter = imageAdapter
                    }

                    if (state.resetList) {
                        imageAdapter?.setItems(emptyList())
                    }

                    imageAdapter?.setItems(state.imageList)

                    state.errorMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding?.etSearch?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                (activity as MainActivity).hideKeyboard(v)

                viewModel.onClickSearch()
            }

            false
        }

        binding?.rcList?.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                ) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastVisibleItemPosition =
                        (binding?.rcList!!.layoutManager as LinearLayoutManager)
                            .findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = imageAdapter?.itemCount!! - 1

                    if (lastVisibleItemPosition == itemTotalCount) {
                        // 리스트 바닥 도착, 다음 페이지 호출
                        viewModel.onLoadContinue()
                    }
                }
            },
        )
    }

    override fun onClickItem(url: String) {
        try {
            activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rcList?.adapter = null
        binding = null
        imageAdapter = null
    }
}
