package ir.hco.appian.app.page.home

import ir.hossainco.utils.view.verticalScrollView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

internal class HomeUI : AnkoComponent<HomePage> {
	override fun createView(ui: AnkoContext<HomePage>) = with(ui) {
		val context = ui.ctx

		verticalScrollView {

		}
	}
}
