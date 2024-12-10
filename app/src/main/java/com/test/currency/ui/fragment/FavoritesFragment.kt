package com.test.currency.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.currency.MainActivity
import com.test.currency.databinding.FragmentFavoritesBinding
import com.test.currency.ui.CurrencyFavoritesAdapter
import com.test.currency.ui.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.load()
        viewModel.result.observe(viewLifecycleOwner) {
            binding?.list?.adapter = CurrencyFavoritesAdapter(it)
        }

        (activity as? MainActivity)?.let {
            it.setTitle("Favorites")
            it.backButtonVisibility(false)
            it.setBottomBarVisibility(true)
        }
    }
}