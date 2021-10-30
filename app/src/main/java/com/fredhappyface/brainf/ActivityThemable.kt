package com.fredhappyface.brainf

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

/**
 * Provides theming capabilities for my activity classes. These are applied in the onCreate method.
 */
@SuppressLint("Registered")
open class ActivityThemable : AppCompatActivity() {
	internal lateinit var mSharedPreferences: SharedPreferences
	private var mCurrentTheme = 0

	/**
	 * Triggered when the activity is created. Sets the theme to one
	 * that the user selected
	 * @param savedInstanceState activity saved data
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
		mCurrentTheme = mSharedPreferences.getInt("theme", 3)
		when (mCurrentTheme) {
			0 -> setTheme(R.style.LightTheme)
			1 -> setTheme(R.style.DarkTheme)
			2 -> setTheme(R.style.BlackTheme)
			else -> when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
				Configuration.UI_MODE_NIGHT_YES -> setTheme(R.style.DarkTheme)
				Configuration.UI_MODE_NIGHT_NO -> setTheme(R.style.LightTheme)
			}
		}
	}

	/**
	 * Triggered when an activity is resumed. If the theme differs from the currently active theme,
	 * then the activity is recreated
	 */
	override fun onResume() {
		super.onResume()
		val theme = mSharedPreferences.getInt("theme", 3)
		if (mCurrentTheme != theme) {
			mCurrentTheme = theme
			recreate()
		}
	}
}
