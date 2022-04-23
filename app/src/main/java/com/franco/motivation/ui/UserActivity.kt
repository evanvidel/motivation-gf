package com.franco.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.franco.motivation.infra.MotivationCostants
import com.franco.motivation.R
import com.franco.motivation.infra.SecurityPreferences
import com.franco.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        verifyUserName()

        binding.buttonSave.setOnClickListener(this)

    }



    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUserName() {
       val name = SecurityPreferences(this).getString(MotivationCostants.KEY.USER_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {

            SecurityPreferences(this).storeString(MotivationCostants.KEY.USER_NAME, name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}