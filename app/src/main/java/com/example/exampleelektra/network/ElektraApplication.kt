package com.example.exampleelektra.network

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

private const val BASE_URL = "https://stage.ektdevelopers.com/_graphql"

var okHttpClient = OkHttpClient.Builder()
    .retryOnConnectionFailure(true)
    .build()

var apolloClient = ApolloClient.builder()
    .serverUrl(BASE_URL)
    .okHttpClient(okHttpClient)
    .build()