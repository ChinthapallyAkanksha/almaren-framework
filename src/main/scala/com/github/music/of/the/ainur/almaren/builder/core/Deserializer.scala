package com.github.music.of.the.ainur.almaren.builder.core

import com.github.music.of.the.ainur.almaren.builder.Core
import com.github.music.of.the.ainur.almaren.state.core._
import com.github.music.of.the.ainur.almaren.{Tree, InvalidDecoder, SchemaRequired, State}

private[almaren] trait Deserializer extends Core {
  def deserializer(decoder:String,columnName:String,schemaInfo:Option[String] = None): Option[Tree] = {

    def json(): State =
      JsonDeserializer(columnName,schemaInfo)
    def xml(): State =
      XMLDeserializer(columnName,schemaInfo)
    def avro(): State =
      AvroDeserializer(columnName,schemaInfo.getOrElse(throw SchemaRequired(decoder)))


    decoder.toUpperCase match {
      case "JSON" => json
      case "XML" => xml
      case "AVRO" => avro
      case d => throw InvalidDecoder(d)
    }
  }
}
