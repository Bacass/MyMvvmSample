package com.lee.mymvvmsample.ui.main.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lee.mymvvmsample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToolsFragment : Fragment() {
    private val viewModel: ToolsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}