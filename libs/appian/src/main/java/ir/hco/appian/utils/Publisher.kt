package ir.hco.appian.utils

import android.content.Context
import android.content.Intent

interface Publisher {
	fun createDeveloperPageIntent() : Intent

	fun createAppRatePageIntent(context: Context) : Intent
}
