package com.lee.mymvvmsample.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()

    private var mBinding: FragmentHomeBinding? = null

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
        viewModel.selectImage.observe(viewLifecycleOwner, Observer {
            if (it) {
                ivCheckSelectImage.setBackgroundResource(R.drawable.icon_chk_btn_on)
                ivCheckSelectVideo.setBackgroundResource(R.drawable.icon_chk_btn_off)
            } else {
                ivCheckSelectImage.setBackgroundResource(R.drawable.icon_chk_btn_off)
                ivCheckSelectVideo.setBackgroundResource(R.drawable.icon_chk_btn_on)
            }
        })
    }
}