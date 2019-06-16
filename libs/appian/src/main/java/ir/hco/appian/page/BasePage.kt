package ir.hco.appian.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

abstract class BasePage : Fragment() {
	open val title: String? = null

	override fun onResume() {
		super.onResume()
		val title = title
		updateTitle(title)
	}

	internal fun updateTitle(title: String?) {
		(activity as? AppCompatActivity)?.supportActionBar?.subtitle = title
	}

}
