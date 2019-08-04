package ir.hco.appian.app

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import ir.hco.util.DrawableSource
import ir.hco.util.StringSource
import ir.hco.util.views.ads
import ir.hco.util.views.simpleToolbar
import org.jetbrains.anko.*

internal class MainUI : AnkoComponent<MainActivity> {
	override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
		val context = ui.owner

		verticalLayout {
			backgroundColorResource = R.color.background

			simpleToolbar(
				title = StringSource.of(R.string.app_title),
				subtitle = StringSource.of(R.string.app_subtitle)
			) {
				action(icon = DrawableSource.of(R.drawable.ic_share))
				action(icon = DrawableSource.of(R.drawable.ic_copy))
				action(icon = DrawableSource.of(R.drawable.ic_rate))
				action(icon = DrawableSource.of(R.drawable.ic_apps))
			}

			frameLayout {
				id = R.id.header
			}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)

			frameLayout {
				id = R.id.fragment
			}.lparams(width = MATCH_PARENT, height = 0, weight = 1f)

			frameLayout {
				id = R.id.footer
			}.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)

			ads()
		}
	}
}
