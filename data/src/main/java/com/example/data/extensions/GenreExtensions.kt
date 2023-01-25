package com.example.data.extensions

import com.example.core.type.Genre


fun List<Genre?>.toGenreDomainList(): List<com.example.podcast_details_domain.models.Genre> {
    val mappedGenre = mutableListOf<com.example.podcast_details_domain.models.Genre>()

    val anyGenreWithSameText: (String) -> Boolean = { dataGenreName ->
        mappedGenre.any { domainGenre ->
            dataGenreName.contains(domainGenre.genreRemoteEnumString)
        }
    }

    forEach { dataGenre ->
        dataGenre?.let {
            val isSubCategory = anyGenreWithSameText(dataGenre.name)

            val domainGenre = com.example.podcast_details_domain.models.Genre(
                genreText = dataGenre.getGenreText(isSubCategory),
                genreRemoteEnumString = dataGenre.name
            )

            mappedGenre.add(domainGenre)
        }
    }

    return mappedGenre
}

fun com.example.podcast_details_domain.models.Genre.toDataGenre(): Genre {
    return Genre.valueOf(genreRemoteEnumString)
}

private fun Genre.getGenreText(isSubCategory: Boolean): String {
    val splitGenre = name.split('_')

    var genreText = ""

    splitGenre.forEachIndexed { index, s ->
        if (index == 0) return@forEachIndexed
        if (isSubCategory && index == 1) return@forEachIndexed

        genreText += if (genreText.isEmpty())
            s.lowercase()
        else
            " ${s.lowercase()}"
    }

    return genreText
}
