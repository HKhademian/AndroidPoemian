package ir.hco.appian.app

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

		setSupportActionBar(findViewById(R.id.toolbar))

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
		//if (intent == null)
		//	return false

		//val extras = intent.extras
		//val appLinkAction = intent.action
		//val appLinkData = intent.data

		return false
	}
}
