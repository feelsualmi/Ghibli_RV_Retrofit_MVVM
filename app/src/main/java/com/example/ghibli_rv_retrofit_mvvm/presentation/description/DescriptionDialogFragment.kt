package com.example.ghibli_rv_retrofit_mvvm.presentation.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ghibli_rv_retrofit_mvvm.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DescriptionDialogFragment: BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(inflater, container, false)

        val banner = arguments?.getString(ARG_BANNER)
        val title = arguments?.getString(ARG_TITLE)
        val description = arguments?.getString(ARG_DESCRIPTION)

        Glide
            .with(this)
            .load(banner)
            .into(binding.ivBanner)
        binding.tvTitle.text = title
        binding.tvDescription.text = description
        return binding.root
    }

    companion object {
        const val ARG_BANNER = "banner"
        const val ARG_TITLE = "title"
        const val ARG_DESCRIPTION = "description"

        @JvmStatic
        fun newInstance(banner: String, title: String, description:String): DescriptionDialogFragment {
            val fragment = DescriptionDialogFragment()

            val bundle = Bundle().apply {
                putString(ARG_BANNER, banner)
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

}