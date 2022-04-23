package com.franco.motivation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.franco.motivation.R
import com.franco.motivation.data.Mock
import com.franco.motivation.databinding.ActivityMainBinding
import com.franco.motivation.infra.MotivationCostants
import com.franco.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationCostants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))


        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationCostants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationCostants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationCostants.FILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationCostants.KEY.USER_NAME)
        "${getString(R.string.hello)} $name! ".also { binding.textUserName.text = it }
    }
}