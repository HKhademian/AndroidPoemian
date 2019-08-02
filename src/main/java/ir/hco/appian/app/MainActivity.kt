package ir.hco.appian.app

import android.os.Bundle
import androidx.core.view.ViewCompat.LAYOUT_DIRECTION_RTL
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.transaction
import ir.hco.appian.app.page.splash.SplashPage
import ir.hco.util.BaseApp
import ir.hossainco.utils.packages.forceLayoutDir
import ir.hossainco.utils.packages.setLocale
import org.jetbrains.anko.AnkoContext

class MainActivity : FragmentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		setLocale(this, (application as BaseApp).locale)
		forceLayoutDir(dir = LAYOUT_DIRECTION_RTL)
		super.onCreate(savedInstanceState)

		MainUI().createView(AnkoContext.create(this, this, true))

		if (savedInstanceState == null) {
			supportFragmentManager.transaction {
				replace(R.id.fragment, SplashPage())
			}
		}

		//if (handleIntent(intent)) {
		//	 we are directly displaying the story
		//}
	}

	// private fun handleIntent(intent: Intent?): Boolean {
	// 	return false
	// }
}
