package com.app.paging3demo.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.paging3demo.R
import com.bumptech.glide.Glide

 class FeedModel {
    var total_page: Int? = 1

    var success_data: ArrayList<Success_data>? = null

    var radius: String? = null

    var status: String? = null

    var total_record: String? = null


    override fun toString(): String {
        return "ClassPojo [total_page = $total_page, success_data = $success_data, radius = $radius, status = $status, total_record = $total_record]"
    }

     class Success_data {
        var business_logo: String? = null
        var business_name: String? = null
        var distance: String? = null
        var rating: String? = null
        var description: String? = null
        var follow: String? = null
        var file: String? = null
        var updated_at: String? = null
        var user_id: String? = null
        var file_type: String? = null
        var review: String? = null
        var name: String? = null
        var provider_id: String? = null
        var id: String? = null
        var video_thumbnail_url: String? = null

        // important code for loading image here
        companion object {
            @JvmStatic
            @BindingAdapter("business_logo")
            fun loadImage(imageView: ImageView, imageURL: String?) {
                Glide.with(imageView.context)
                    .load(imageURL)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView)
            }
        }


        override fun toString(): String {
            return "ClassPojo [business_logo = $business_logo, business_name = $business_name, distance = $distance, rating = $rating, description = $description, follow = $follow, file = $file, updated_at = $updated_at, user_id = $user_id, file_type = $file_type, review = $review, name = $name, provider_id = $provider_id, id = $id, video_thumbnail_url = $video_thumbnail_url]"
        }
    }
}