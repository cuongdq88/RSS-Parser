package com.prof.rssparser

import java.nio.charset.Charset
import kotlinx.coroutines.Dispatchers
import okhttp3.Call
import okhttp3.OkHttpClient

// TODO: or use the custom builder for better binary compatibility?
fun Parser.Companion.build(
    callFactory: Call.Factory? = null,
    okHttpClient: OkHttpClient? = null,
    charset: Charset? = null,
): Parser {
    val client = when {
        callFactory != null -> callFactory
        okHttpClient != null -> okHttpClient
        else -> OkHttpClient()
    }
    return Parser(
        xmlFetcher = JvmXmlFetcher(
            callFactory = client,
        ),
        xmlParser = JvmXmlParser(
            charset = charset,
            dispatcher = Dispatchers.IO,
        ),
    )
}
