package main

import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.runBlocking
import org.example.localhost.GetAllCochesQuery


fun main(args: Array<String>) {
    val apolloClient = ApolloClient.Builder()
        .serverUrl("http://localhost:8080/graphql")
        .build()

    runBlocking {
        apolloClient.query(GetAllCochesQuery()).execute()
            .data?.let {data ->
            data.getAllCoches?.forEach { println(it?.id) }
            }
    }
}