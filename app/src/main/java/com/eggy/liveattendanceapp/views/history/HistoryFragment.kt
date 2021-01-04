package com.eggy.liveattendanceapp.views.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eggy.liveattendanceapp.R
import com.eggy.liveattendanceapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {


    private var binding:FragmentHistoryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}