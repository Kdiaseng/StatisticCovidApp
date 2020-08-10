package br.itbam.statisticcovid.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import java.util.*


class LocateHelper(val context: Context) {

    companion object {
         fun setAppLocale(context: Context, localeCode: String) {
            val resources: Resources = context.resources
            val dm = resources.displayMetrics
            val config = resources.configuration
            config.setLocale(Locale(localeCode.toLowerCase(Locale.ROOT)))
            resources.updateConfiguration(config, dm)
        }
    }

}