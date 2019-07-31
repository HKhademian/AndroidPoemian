package ir.hco.appian.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import ir.hco.appian.app.page.home.HomePage
import org.jetbrains.anko.AnkoContext

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		MainUI().createView(AnkoContext.create(this, this, true))

		setSupportActionBar(findViewById(R.id.toolbar))

		if (savedInstanceState == null) {
			supportFragmentManager.transaction {
				replace(R.id.fragment, HomePage())
			}
		}
	}
}
