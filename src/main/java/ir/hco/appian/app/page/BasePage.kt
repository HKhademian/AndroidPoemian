package ir.hco.appian.app.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.hco.appian.app.MainActivity
import ir.hco.appian.app.data.Repository

abstract class BasePage : Fragment() {
	open val title: String? = null

	override fun onResume() {
		super.onResume()
		val title = title
		updateTitle(title)
	}

	internal fun updateTitle(title: String?) {
		(activity as? MainActivity)?.supportActionBar?.subtitle = title
	}

	internal inline fun observeFontSizeMultiplier(crossinline observer: (Float) -> Unit) {
		Repository.observeSettings(this) {
			observer.invoke(it.fontSizeMultiplier)
		}
	}

	internal inline fun observeDarkMode(crossinline observer: (Boolean) -> Unit) {
		Repository.observeSettings(this) {
			observer.invoke(it.darkMode)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

}
