package com.busanit.androidchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busanit.androidchallenge.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding = FragmentTwoBinding.inflate(inflater, container, false)
    return binding.root
  }
}