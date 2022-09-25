package com.jmarcelo.foodapp.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import com.jmarcelo.foodapp.R
import com.jmarcelo.foodapp.databinding.ActivityOnBoardingActivityBinding
import com.jmarcelo.foodapp.domain.model.OnBoardingDataDomain
import com.jmarcelo.foodapp.ui.onboarding.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityOnBoardingActivityBinding
    private var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter?=null
    private val viewModel:OnBoardingViewModel by viewModels()
    private var position = 0
    private var onBoardingDataList= emptyList<OnBoardingDataDomain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel.getInformationOnbarding()
        viewModel.data.observe(this){
            onBoardingDataList=it
            setOnBoardingViewPagerAdapter(it)
            position = binding.screenPager.currentItem
        }

        binding.buttonNext.setOnClickListener {
            if (position<onBoardingDataList.size){
                position++
                binding.screenPager.currentItem = position
            }
        }

        binding.tabIndicator.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position==onBoardingDataList.size-1){
                    binding.buttonNext.text =getString(R.string.str_start)
                }else{
                    binding.buttonNext.text = getString(R.string.str_next)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardDataModelList:List<OnBoardingDataDomain>){
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this,onBoardDataModelList)
        binding.screenPager.adapter = onBoardingViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(binding.screenPager)
    }
}