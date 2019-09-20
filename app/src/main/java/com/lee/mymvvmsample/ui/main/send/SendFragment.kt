package com.lee.mymvvmsample.ui.main.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.ui.main.share.ShareViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendFragment : Fragment() {
    private val viewModel: ShareViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        viewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}