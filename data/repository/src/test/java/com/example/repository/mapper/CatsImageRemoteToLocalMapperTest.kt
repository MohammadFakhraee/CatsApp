package com.example.repository.mapper

import com.example.repository.utils.mockCatsImageRemote
import com.example.repository.utils.mockLocalBreed
import com.example.repository.utils.mockLocalCatsImage
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
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote)
        assertThat(result.first).isEqualTo(mockLocalCatsImage)
    }

    @Test
    fun `test breedMapper success`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote)
        assertThat(result.second).isEqualTo(mockLocalBreed)
    }

    @Test
    fun `test catsImageMapper fail`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote.copy(id = null))
        assertThat(result.first).isNotEqualTo(mockLocalCatsImage)
    }

    @Test
    fun `test breedMapper fail`() {
        val result = catsImageRemoteToLocalMapper(mockCatsImageRemote.copy(breeds = arrayListOf()))
        assertThat(result.second).isNotEqualTo(mockLocalBreed)
    }
}