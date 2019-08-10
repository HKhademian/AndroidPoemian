package ir.hco.appian.app

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import ir.hco.util.views.ads
import org.jetbrains.anko.*

internal class MainUI : AnkoComponent<MainActivity> {
	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		verticalLayout {
			backgroundColorResource = R.color.background

			frameLayout {
				id = R.id.header
			}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)

			frameLayout {
				id = R.id.fragment
			}.lparams(width = MATCH_PARENT, height = 0, weight = 1f)

			frameLayout {
				id = R.id.footer
			}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)

			ads().lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
		}
	}
}
