package com.example.data.net.interceptors

import android.util.Log
import com.example.data.net.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject
constructor(@param:Named("public_key") private val publicKey: String, @param:Named("private_key") private val privateKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var url = request.url()

        val timeStamp = System.currentTimeMillis()
        val hash = digest(HASH_ALGORITHM, timeStamp.toString(), privateKey, publicKey)

        val urlBuilder = url.newBuilder()
        urlBuilder.addEncodedQueryParameter(ApiConstants.QUERY_PARAM_TS, timeStamp.toString())
        urlBuilder.addEncodedQueryParameter(ApiConstants.QUERY_PARAM_API_KEY, publicKey)
        urlBuilder.addEncodedQueryParameter(ApiConstants.QUERY_PARAM_HASH, hash)

        url = urlBuilder.build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private fun digest(algorithm: String, vararg params: String): String {
        var message = ""
        for (param in params) {
            message += param
        }
        try {
            val digest = MessageDigest.getInstance(algorithm)
            digest.update(message.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) {
                    h = "0" + h
                }
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, e.message)
        }

        return ""
    }

    companion object {

        val HASH_ALGORITHM = "MD5"
        private val TAG = AuthInterceptor::class.java.name
    }
}
