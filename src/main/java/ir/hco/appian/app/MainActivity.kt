package ir.hco.appian.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.ViewCompat.LAYOUT_DIRECTION_RTL
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.transaction
import ir.hco.appian.app.page.exit.ExitPage
import ir.hco.appian.app.page.splash.SplashPage
import ir.hco.util.BaseApp
import ir.hossainco.utils.packages.forceLayoutDir
import ir.hossainco.utils.packages.setLocale
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.toast

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
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		val res = super.onCreateOptionsMenu(menu)
		menuInflater.inflate(R.menu.menu_main, menu)
		return res
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return if (item.itemId == R.id.item_test) {
			toast("Hello WORLD! ;D")
			true
		} else super.onOptionsItemSelected(item)
	}

	override fun onBackPressed() {
		if (supportFragmentManager.popBackStackImmediate())
			return

		ExitPage().show(supportFragmentManager, "exit")
	}
}
