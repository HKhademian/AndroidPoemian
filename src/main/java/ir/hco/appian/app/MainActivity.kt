package ir.hco.appian.app

import android.content.Intent
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.transaction
import ir.hco.appian.app.page.home.HomePage
import ir.hossainco.utils.packages.forceLayoutDir
import org.jetbrains.anko.AnkoContext

class MainActivity : FragmentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		forceLayoutDir(dir = ViewCompat.LAYOUT_DIRECTION_RTL)
		MainUI().createView(AnkoContext.create(this, this, true))

		if (savedInstanceState == null) {
			supportFragmentManager.transaction {
				replace(R.id.fragment, HomePage())
			}
		}

		if (handleIntent(intent)) {
			// we are directly displaying the story
		}
	}

	private fun handleIntent(intent: Intent?): Boolean {
		return false
	}
}
