package com.app.unsplashapp.presentation.ui.imagesearch

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import androidx.navigation.fragment.findNavController
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_image_search.*

class ImageSearchFragment: BaseFragment(R.layout.fragment_image_search) {

    override var useDefaultToolbar = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchNavBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        searchView.startSearch()
        searchView.voiceBtnClickListener = {
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                startActivityForResult(this, REQUEST_CODE_VOICE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE_VOICE && resultCode == RESULT_OK) {
            val voiceResult = data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
            searchView.searchText = voiceResult
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val REQUEST_CODE_VOICE = 1
    }
}