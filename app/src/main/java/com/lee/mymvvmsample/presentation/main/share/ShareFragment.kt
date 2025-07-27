package com.lee.mymvvmsample.presentation.main.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lee.mymvvmsample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShareFragment : Fragment() {
    private val viewModel: ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_share, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        viewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                textView.text = it
            },
        )
        return root
    }
}
