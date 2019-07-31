package ir.hco.appian.page

// import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BasePage : Fragment() {
	open val title: String? = null

	override fun onResume() {
		super.onResume()
		val title = title
		updateTitle(title)
	}

	internal fun updateTitle(title: String?) {
		// 	(activity as? AppCompatActivity)?.supportActionBar?.subtitle = title
	}

}
