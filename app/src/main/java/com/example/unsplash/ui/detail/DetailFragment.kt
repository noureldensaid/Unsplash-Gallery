package com.example.unsplash.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val photo = args.photo
        binding.apply {
                      Glide.with(this@DetailFragment)
                .load(photo.urls.regular)
                .error(R.drawable.ic_error)
                .into(imageView)

            textViewDescription.text = photo.description
            textViewCreator.text = "Photo by ${photo.user.name} on Unsplash"

        }

    }


}