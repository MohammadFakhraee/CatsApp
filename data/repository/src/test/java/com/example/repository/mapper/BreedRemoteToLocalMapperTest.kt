package com.example.repository.mapper

import com.example.repository.utils.mockLocalBreed
import com.example.repository.utils.mockRemoteBreed
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class BreedRemoteToLocalMapperTest {

    private lateinit var breedMapper: BreedRemoteToLocalMapper

    @Before
    fun setup() {
        breedMapper = BreedRemoteToLocalMapper()
    }

    @Test
    fun `test BreedRemoteLocalMapper success`() {
        val result = breedMapper(mockRemoteBreed)
        assertThat(result).isEqualTo(mockLocalBreed)
    }

    @Test
    fun `test BreedRemoteLocalMapper failure`() {
        val wrongResult = breedMapper(mockRemoteBreed.copy(id = null))
        assertThat(wrongResult).isNotEqualTo(mockLocalBreed)
    }
}