package com.codingwithset.sotech.images.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codingwithset.sotech.R
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment1(val image: String) : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.image_loading)
        requestOptions.error(R.drawable.error_icon)

        Glide.with(this).setDefaultRequestOptions(requestOptions).load(image).into(product_image)
    }

}