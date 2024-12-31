package com.mohammadhf.repository.mapper

import com.mohammadhf.repository.utils.mockCatsImageRemote
import com.mohammadhf.repository.utils.mockLocalBreed
import com.mohammadhf.repository.utils.mockLocalCatsImage
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CatsImageRemoteToLocalMapperTest {
    lateinit var catsImageRemoteToLocalMapper: CatsImageRemoteToLocalMapper

    @Before
    fun setup() {
        catsImageRemoteToLocalMapper = CatsImageRemoteToLocalMapper(BreedRemoteToLocalMapper())
    }

    @Test
    fun `test catsImageMapper success`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote, mapOf())
        assertThat(result.first).isEqualTo(mockLocalCatsImage)
    }

    @Test
    fun `test catsImageMapper withFavorite success`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote, mapOf(mockCatsImageRemote.id!! to true))
        assertThat(result.first).isEqualTo(mockLocalCatsImage.copy(isFavored = true))
    }

    @Test
    fun `test breedMapper success`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote, mapOf())
        assertThat(result.second).isEqualTo(mockLocalBreed)
    }

    @Test
    fun `test catsImageMapper fail`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote.copy(id = null), mapOf())
        assertThat(result.first).isNotEqualTo(mockLocalCatsImage)
    }

    @Test
    fun `test breedMapper fail`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote.copy(breeds = arrayListOf()), mapOf())
        assertThat(result.second).isNotEqualTo(mockLocalBreed)
    }
}