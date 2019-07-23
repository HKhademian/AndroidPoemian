package ir.hco.appian.page

// import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ir.hco.appian.R

abstract class BasePage : Fragment() {
	open val title: String? = null
	open val hasAds: Boolean = true

	override fun onResume() {
		super.onResume()
		val title = title
		updateTitle(title)
		updateAds(hasAds)
	}

	internal fun updateTitle(title: String?) {
		// 	(activity as? AppCompatActivity)?.supportActionBar?.subtitle = title
	}

	internal fun updateAds(hasAds: Boolean) {
		activity?.findViewById<View>(R.id.ads)?.let { it.isVisible = hasAds }
	}

}
