package ir.hco.appian.app

import android.graphics.Color
import android.view.Gravity.CENTER
import android.view.Gravity.START
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import ir.hco.appian.app.data.Repository
import ir.hco.appian.app.utils.views.drawer
import ir.hossainco.utils.App
import ir.hossainco.utils.view.appTextView
import ir.hossainco.utils.view.factory
import org.jetbrains.anko.*

internal class MainUI : AnkoComponent<MainActivity> {
	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		drawer {
			id = R.id.drawer

			fitsSystemWindows = true

			backgroundResource = R.color.background
			Repository.settingsLiveData.observe(ui.owner, Observer { settings ->
				backgroundColorResource = if (settings?.darkMode == true)
					R.color.backgroundDark
				else
					R.color.background
			})

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
				setTitleTextColor(context.resources.getColor(R.color.primaryTextColor))
				setSubtitleTextColor(context.resources.getColor(R.color.primaryTextColor))
				setTitle(R.string.app_title)
				setSubtitle(R.string.app_subtitle)

//				linearLayout {
//					layoutParams = Toolbar.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//
//					imageView(R.drawable.foroog) {
//						adjustViewBounds = true
//						padding = dip(4)
//					}.lparams(height = dip(64)) {
//						gravity = START
//					}
//
//				}

//				verticalLayout {
//					layoutParams = Toolbar.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//
//					textView {
//						id = R.id.title
//						textColorResource = R.color.primaryTextColor
//						gravity = CENTER
//						textSize = 22f
//						typeface = Typeface.SANS_SERIF
//						setText(R.string.app_title)
//					}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
//					textView {
//						id = R.id.subtitle
//						textColorResource = R.color.primaryTextColor
//						gravity = CENTER
//						textSize = 14f
//						typeface = Typeface.SANS_SERIF
//						setText(R.string.app_subtitle)
//					}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
//				}

			}

			frameLayout {
				id = R.id.fragment
			}.lparams(width = MATCH_PARENT, height = 0, weight = 1f)

			frameLayout {
				backgroundColorResource = R.color.secondaryLightColor

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
