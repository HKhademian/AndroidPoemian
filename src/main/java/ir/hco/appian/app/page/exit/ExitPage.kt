package ir.hco.appian.app.page.exit

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity.CENTER
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import ir.hco.appian.app.R
import ir.hco.util.BaseApp
import ir.hco.util.ads.Advertiser
import ir.hco.util.views.ads
import org.jetbrains.anko.matchParent

class ExitPage : DialogFragment() {
	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val activity = activity!!
		val baseApp = activity.application as BaseApp
		val advertiser = baseApp.advertiser

		val view = FrameLayout(activity)
		val adView = view.ads(unitName = Advertiser.UNIT_CONTENT)
		adView.layoutParams = FrameLayout.LayoutParams(matchParent, matchParent, CENTER)

		fun recycle() = advertiser.recycleBanner(adView)

		return AlertDialog.Builder(activity)
			.setCancelable(true)
			.setView(view)
			.setTitle(R.string.dialog_exit_title)
			.setNeutralButton(R.string.dialog_exit_dismiss) { _, _ ->
				recycle()
			}
			.setNegativeButton(R.string.dialog_exit_confirm) { _, _ ->
				recycle()
				advertiser.showFull(activity)
				activity.finish()
			}
			.setOnCancelListener {
				recycle()
			}
			.create()!!
	}
}
