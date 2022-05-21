package com.example.openwrtmanager.com.example.openwrtmanager.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.fragment.app.Fragment
import java.lang.Math.log
import java.lang.Math.pow
import java.util.concurrent.TimeUnit
import kotlin.math.truncate


class Utils {

    //    static const bool ReleaseMode = bool.fromEnvironment('dart.vm.product', defaultValue: false);
    companion object {
        var NoSpeedCalculationText = "-----"
        fun formatSeconds(seconds: Long): String {
            val days = TimeUnit.SECONDS.toDays(seconds).toInt()
            val hours: Int =
                (TimeUnit.SECONDS.toHours(seconds) - TimeUnit.SECONDS.toDays(seconds) * 24).toInt()
            val minutes: Int =
                (TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60).toInt()
            val seconds: Int =
                (TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60).toInt()


//            var  tokens : List<String> = listOf();
            val tokens: MutableList<String> = mutableListOf();
            if (days != 0) {
//                tokens.add('${days}d');
                tokens.add("${days}d");
            }
            if (tokens.isNotEmpty() || hours != 0) {
//                tokens.add('${hours}h');
                tokens.add("${hours}h");
            }
            if (tokens.isNotEmpty() || minutes != 0) {
//                tokens.add('${minutes}m');
                tokens.add("${minutes}m");
            }
            tokens.add("${seconds}s");
            return tokens.joinToString(" ")
        }

        //        static String formatBytes(int bytes, {int decimals = 0}) {
//            if (bytes <= 0) return "0 B";
//            const suffixes = ["B", "Kb", "Mb", "Gb", "Tb", "Pb"];
//            var i = (log(bytes) / log(1024)).floor();
//            var number = (bytes / pow(1024, i));
//            return (number).toStringAsFixed(number.truncateToDouble() == number ? 0 : decimals) +
//            ' ' +
//                    suffixes[i];
//        }
        fun formatBytes(bytes: Int, decimals: Int = 0): String {
            if (bytes <= 0) return "0 B";
            var suffixes = listOf("B", "Kb", "Mb", "Gb", "Tb", "Pb")
            val i = Math.floor(log(bytes.toDouble()) / log(1_024.0));
            val number = (bytes / pow(1024.0, i));

//            return number.toStringAsFixed(if (number.truncateToDouble() === number) 0 else decimals) +
//                    ' ' +
//                    suffixes[i.toInt()]
            val a = if (truncate(number) == number) 0 else decimals
            return String.format("%.${a}f", number) + ' ' +
                    suffixes[i.toInt()];
        }

        fun getUrl(port: String, address: String, isUseHttpsConnection: Boolean): String {
            var postt = port
            if (port == "" || port.toInt() <= 0) {
                postt = "80"
            }
            if (isUseHttpsConnection) {
                return "https://$address:$postt/"
            } else {
                return "http://$address:$postt/"
            }
        }
    }
}
//
//// Add data to the intent, the receiving app will decide
//fun Fragment.shareData(username: String, isGist: Boolean) {
//    val share = Intent(Intent.ACTION_SEND)
//    share.type = "text/plain"
//    share.putExtra(Intent.EXTRA_SUBJECT, "Share $username link!")
//    if (isGist) {
//        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.gisturl) + username)
//    } else {
//        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.giturl) + username)
//    }
//    startActivity(Intent.createChooser(share, "Share $username link!"))
//}
//
//
//fun Fragment.openCustomTabs(url: String) {
//    if (url.length > 6 && url.contains("http")) {
//        val builder = CustomTabsIntent.Builder()
//        val customTabsIntent = builder.build()
//        context?.let { customTabsIntent.launchUrl(it, Uri.parse(url)) }
//    }
//}
//
//fun Fragment.sendEmail(email: String, username: String) {
//    if (email.length > 6 && isEmailValid(email)) {
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "text/html"
//        intent.putExtra(Intent.EXTRA_EMAIL, email)
//        intent.putExtra(Intent.EXTRA_SUBJECT, context?.getString(R.string.app_name))
//        intent.putExtra(Intent.EXTRA_TEXT, "Hi $username,\n\nThanks & Regards,\n\n")
//        startActivity(Intent.createChooser(intent, "Send Email!"))
//    }
//}
//
//fun isEmailValid(email: String): Boolean {
//    return Pattern.compile(
//        Constants.EMAIL_VERIFICATION,
//        Pattern.CASE_INSENSITIVE
//    ).matcher(email).matches()
//}
//
//// when error came then show message
//fun Fragment.snackBarError() {
//    Snackbar.make(
//        activity?.window?.decorView?.rootView!!,
//        R.string.error,
//        Snackbar.LENGTH_LONG
//    ).show()
//}

/**
 * Return current network connection status
 *
 * @param context Context
 * @return networkInfo?.isConnected Boolean
 */
fun Fragment.isNetworkAvailable(): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else false
}

//fun Fragment.openFragment(fragment: Fragment, username: String) {
//    val args = Bundle()
//    args.putString("username", username)
//    fragment.arguments = args
//    replaceFragment(fragment, R.id.fragment_container)
//}
