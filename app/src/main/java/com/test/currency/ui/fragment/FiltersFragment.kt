package com.test.currency.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.currency.MainActivity
import com.test.currency.R
import com.test.currency.databinding.FragmentFiltersBinding
import com.test.currency.service.FilterType
import com.test.currency.ui.FiltersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : Fragment() {
    private var binding: FragmentFiltersBinding? = null
    private val viewModel: FiltersViewModel by viewModels()

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
        binding?.selector?.check(
            when (viewModel.selectedFilterType) {
                FilterType.Z_A.type -> R.id.za
                FilterType.ACS.type -> R.id.acs
                FilterType.DESC.type -> R.id.desc
                else -> R.id.az
            }
        )
        binding?.selector?.setOnCheckedChangeListener { _, id ->
            viewModel.save(
                when (id) {
                    R.id.za -> FilterType.Z_A.type
                    R.id.acs -> FilterType.ACS.type
                    R.id.desc -> FilterType.DESC.type
                    else -> FilterType.A_Z.type
                }
            )
        }
        (activity as? MainActivity)?.let {
            it.setTitle("Filters")
            it.backButtonVisibility(true)
            it.setBottomBarVisibility(false)
        }
    }
}