package list.albums.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtil {

    companion object {

        /**
         * Check if the device is connected to internet via cellular data, ethernet, WiFi or VPN.
         *
         * @return [Boolean.true] if the device is connected to internet
         */
        fun isConnectedToNetwork(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var result = 0
            connectivityManager.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            result = 1
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            result = 2
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            result = 3
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                            result = 4
                        }
                    }
                }
            }
            return result != 0
        }
    }
}
