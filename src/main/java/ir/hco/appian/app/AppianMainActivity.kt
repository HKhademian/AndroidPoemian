package ir.hco.appian.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.transaction
import ir.hco.appian.app.page.home.HomePage
import ir.hossainco.utils.packages.forceLayoutDir
import org.jetbrains.anko.AnkoContext

interface AppianMainActivity {
	private val activity get() = this as MainActivity

	fun onCreateActivity(savedInstanceState: Bundle?) = with(activity) {
		forceLayoutDir(dir = View.LAYOUT_DIRECTION_RTL)
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
