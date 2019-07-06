package ir.hco.appian.app

import android.graphics.Color
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import ir.hco.appian.app.data.Repository
import ir.hco.appian.utils.views.drawer
import ir.hossainco.utils.App
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.factory
import org.jetbrains.anko.*

internal class MainUI : AnkoComponent<MainActivity> {
	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		drawer {
			id = R.id.drawer

			fitsSystemWindows = true

			setupDrawerContent(ui)
//			setupDrawerSide(ui)
//			openDrawer(GravityCompat.START)
		}
	}

	private fun DrawerLayout.setupDrawerContent(ui: AnkoContext<MainActivity>) {
		verticalLayout {
			factory(::Toolbar, theme = R.style.ThemeOverlay_AppCompat_ActionBar) {
				id = R.id.toolbar

				backgroundColorResource = R.color.primaryColor
				setTitleTextColor(Color.WHITE)
				setSubtitleTextColor(Color.WHITE)
			}

			frameLayout {
				id = R.id.fragment

				Repository.settingsLiveData.observe(ui.owner, Observer { settings ->
					backgroundColor = if (settings?.darkMode == true)
						Color.parseColor("#FFeecc66")
					else
						Color.parseColor("#FFeeeeee")
				})
			}.lparams(width = MATCH_PARENT, height = 0, weight = 1f)

			frameLayout {
				backgroundColorResource = R.color.primaryDarkColor

				appTextView(textRes = R.string.ads, dark = false)
					.lparams(gravity = CENTER)

				(App.app as MainApp).createBanner(this)
					.lparams(width = MATCH_PARENT, height = WRAP_CONTENT, gravity = CENTER)
			}.lparams(width = MATCH_PARENT, height = dip(50))
		}
	}

//	private fun DrawerLayout.setupDrawerSide(ui: AnkoContext<MainActivity>) {
//		frameLayout {
//			id = R.id.side
//
//			backgroundColor = Color.WHITE
//			textView("NULL")
//		}.drawerLParams(width = dip(196), height = MATCH_PARENT) {
//			gravity = GravityCompat.START
//		}
//	}
}
