package ir.hco.appian.app

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.transaction
import ir.hco.appian.app.page.home.HomePage
import ir.hossainco.utils.packages.forceLayoutDir
import org.jetbrains.anko.AnkoContext

class MainActivity : AppCompatActivity() {

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	override fun onCreate(savedInstanceState: Bundle?) {
		forceLayoutDir(dir = View.LAYOUT_DIRECTION_RTL)

		super.onCreate(savedInstanceState)

		MainUI().createView(AnkoContext.create(this, this, true))

		val drawer = findViewById<DrawerLayout>(R.id.drawer)
		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		supportActionBar?.let { actionbar ->
			// val toggle = ActionBarDrawerToggle(
			// 	this, drawer, toolbar,
			// 	R.string.drawer_open,
			// 	R.string.drawer_close
			// )
			// toggle.isDrawerIndicatorEnabled = true
			// toggle.toolbarNavigationClickListener = View.OnClickListener { drawer.openDrawer(GravityCompat.START) }

			//actionbar.setHomeAsUpIndicator(R.drawable.ic_menu)
			//actionbar.setDisplayHomeAsUpEnabled(true)

			actionbar.setDisplayShowHomeEnabled(true)
			actionbar.setIcon(R.mipmap.ic_launcher)
		}

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
		if (intent == null)
			return false

		val extras = intent.extras
		val appLinkAction = intent.action
		val appLinkData = intent.data

		val storyId = when {
			Intent.ACTION_VIEW == appLinkAction && appLinkData != null ->
				appLinkData.lastPathSegment?.toIntOrNull() ?: -1

			extras?.containsKey("story_id") == true ->
				extras.getInt("story_id", -1)

			extras?.containsKey("storyId") == true ->
				extras.getInt("storyId", -1)

			else -> -1
		}

		//if (!checkStoryId(storyId))
		//	return false

		//supportFragmentManager.transaction {
		//replace(R.id.fragment, StoryDetailPage(storyId))
		//addToBackStack("storyDetail")
		//}
		//return true

		return false
	}

//	override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
//			return Repository.fontSizeMultiplierUp()
//		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//			return Repository.fontSizeMultiplierDown()
//		}
//
//		return super.onKeyDown(keyCode, event)
//	}
}
