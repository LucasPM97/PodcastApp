package com.example.podcast_player_ui.data.mediaItem
//
//import android.content.res.AssetManager
//import android.net.Uri
//import androidx.media3.common.MediaItem
//import androidx.media3.common.MediaMetadata
//import androidx.media3.common.util.Util
//import com.google.common.collect.ImmutableList
//
//object MediaItemTree {
//    private var mediaItems: MutableList<MediaItem> = mutableListOf()
//
//    fun getItem(mediaItemId: String): MediaItem? {
//        return mediaItems.firstOrNull {
//            it.mediaId == mediaItemId
//        }
//    }
//
//    fun getChildren(id: String): List<MediaItem>? {
//        return treeNodes[id]?.getChildren()
//    }
//
//    fun getRandomItem(): MediaItem {
//        var curRoot = getRootItem()
//        while (curRoot.mediaMetadata.folderType != FOLDER_TYPE_NONE) {
//            val children = getChildren(curRoot.mediaId)!!
//            curRoot = children.random()
//        }
//        return curRoot
//    }
//
//    fun getItemFromTitle(title: String): MediaItem? {
//        return titleMap[title]?.item
//    }
//}