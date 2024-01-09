package fr.iut.alldev.allin.data.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object ZonedDateTimeSerializer : KSerializer<ZonedDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ZonedDateTime", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: ZonedDateTime) {
        encoder.encodeLong(value.toEpochSecond())
    }

    override fun deserialize(decoder: Decoder): ZonedDateTime {
        val epoch = decoder.decodeLong()
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.systemDefault())
    }
}

class SimpleDateSerializer : KSerializer<ZonedDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    override fun deserialize(decoder: Decoder): ZonedDateTime {
        val date = LocalDate.parse(decoder.decodeString(), formatter)
        return date.atStartOfDay(ZoneId.systemDefault())
    }

    override fun serialize(encoder: Encoder, value: ZonedDateTime) {
        val dateString = formatter.format(value)
        encoder.encodeString(dateString)
    }
}
