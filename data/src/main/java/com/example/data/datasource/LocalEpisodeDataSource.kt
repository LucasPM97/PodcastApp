package com.example.data.datasource

interface ILocalEpisodeDataSource {
    suspend fun getEpisodesHistory(pageLimit: Int)//Returns: Include Episode List Data Class
    suspend fun includeEpisodeToHistory()// Parameter: Include Episode Data Class
    suspend fun getEpisode(episodeUuid: String)//Returns: Include Nullable Episode Data Class
}

class LocalEpisodeDataSource : ILocalEpisodeDataSource {
    override suspend fun getEpisodesHistory(pageLimit: Int) {
        //TODO: Implement Database GET LIST
    }

    override suspend fun includeEpisodeToHistory() {
        //TODO: Implement Database INSET ITEM
    }

    override suspend fun getEpisode(episodeUuid: String) {
        //TODO: Implement Database GET ITEM
    }

}