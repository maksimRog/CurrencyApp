package com.test.currency.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.currency.MainActivity
import com.test.currency.databinding.FragmentFiltersBinding

class FiltersFragment : Fragment() {
    private var binding: FragmentFiltersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.let {
            it.setTitle("Filters")
            it.backButtonVisibility(true)
            it.setBottomBarVisibility(false)
        }
    }
}