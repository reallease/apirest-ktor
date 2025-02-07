package com.thomasd.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

object UUIDSerializer: KSerializer<UUID> {
    override fun deserialize(decoder: Decoder): UUID = // tipos de dados que iramos receber
        UUID.fromString(decoder.decodeString())

    override val descriptor: SerialDescriptor
        get() = TODO("Not yet implemented")

    override fun serialize(encoder: Encoder, value: UUID) {
        TODO("Not yet implemented")
    }
}