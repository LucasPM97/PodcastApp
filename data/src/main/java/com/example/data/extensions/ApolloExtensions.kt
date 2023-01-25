package com.example.data.extensions

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.exception.ApolloException

suspend fun <T : Query.Data> ApolloClient.safeQueryExecute(query: Query<T>): T? {
    return try {
        val response = query(query).execute()
        if (response.hasErrors())
            null
        else
            response.data

    } catch (ex: ApolloException) {
        return null
    }
}