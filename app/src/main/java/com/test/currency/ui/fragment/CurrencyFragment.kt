package com.test.currency.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.currency.MainActivity
import com.test.currency.R
import com.test.currency.databinding.FragmentCurrencyBinding
import com.test.currency.ui.CurrencyAdapter
import com.test.currency.ui.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private var binding: FragmentCurrencyBinding? = null
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.loadSpinner()

        viewModel.progress.observe(viewLifecycleOwner) {
            binding?.progress?.isVisible = it
        }

        viewModel.result.observe(viewLifecycleOwner) {
            binding?.list?.adapter = CurrencyAdapter(it) {
                viewModel.processCurrency(it)
            }
        }
        viewModel.spinnerList.observe(viewLifecycleOwner) {
            binding?.spinner?.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    p3: Long
                ) {
                    viewModel.loadCurrencies(adapterView?.getItemAtPosition(position).toString())
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                }

            }
            binding?.spinner?.adapter = ArrayAdapter(
                view.context,
                android.R.layout.simple_spinner_item, it
            )

        }

        (activity as? MainActivity)?.let {
            it.setTitle("Currencies")
            it.backButtonVisibility(false)
            it.setBottomBarVisibility(true)
        }
        binding?.let {
            it.filter.setOnClickListener {
                findNavController().navigate(R.id.filters)
            }
        }

    }
}