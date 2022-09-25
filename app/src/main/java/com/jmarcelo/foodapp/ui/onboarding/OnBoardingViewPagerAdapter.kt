package com.jmarcelo.foodapp.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.jmarcelo.foodapp.R
import com.jmarcelo.foodapp.domain.model.OnBoardingDataDomain

class OnBoardingViewPagerAdapter(private val context: Context,
    private val onBoardingDatumModels: List<OnBoardingDataDomain>) : PagerAdapter() {

    override fun getCount(): Int {
        return onBoardingDatumModels.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
      //  super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=LayoutInflater.from(context).inflate(R.layout.item_onboarding,null)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val title:TextView = view.findViewById(R.id.title)
        val description:TextView = view.findViewById(R.id.description)

        imageView.setImageResource(onBoardingDatumModels[position].imageURL)
        title.text = onBoardingDatumModels[position].title
        description.text = onBoardingDatumModels[position].description

        container.addView(view)
        return view
    }
}