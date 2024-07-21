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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.databinding.FragmentHomeBinding
import com.lee.mymvvmsample.presentation.main.MainActivity
import com.lee.mymvvmsample.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickHandler {
    private val viewModel: HomeViewModel by viewModels()

    private var mBinding: FragmentHomeBinding? = null
    private var mImageAdapter: HomeImageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        initEvent()

        return mBinding?.root
    }

    private fun initEvent() {
        viewModel.searchResultEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                is HomeViewModel.SearchResult.Success -> {
                    Toast.makeText(context, "데이타 수신", Toast.LENGTH_SHORT).show()

                    if (mImageAdapter == null) {
                        mImageAdapter = HomeImageAdapter(this@HomeFragment)
                        mBinding?.rcList?.adapter = mImageAdapter
                    }
                    mImageAdapter?.initItem(viewModel.imageListData?.toList())
                }
                is HomeViewModel.SearchResult.Fail -> {
                    Toast.makeText(context, "데이타 없음", Toast.LENGTH_SHORT).show()
                }
                is HomeViewModel.SearchResult.NetworkError -> {
                    Toast.makeText(context, "NetworkError", Toast.LENGTH_SHORT).show()
                }
                else -> {

                }

            }
        })

        viewModel.resetList.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.resetList.value = false
                mImageAdapter?.resetList()
            }
        })

        mBinding?.etSearch?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                (activity as MainActivity).hideKeyboard(v)

                viewModel.onClickSearch()
            }

            false
        }

        mBinding?.rcList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var lastVisibleItemPosition = (mBinding?.rcList!!.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                var itemTotalCount = mImageAdapter?.itemCount!! - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    // 리스트 바닥 도착, 다음 페이지 호출
                    viewModel.onLoadContinue()
                }
            }
        })
    }

    override fun onClickItem(url: String) {
        try {
            activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
        }
    }
}