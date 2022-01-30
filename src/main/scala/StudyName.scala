/** Name of a study */
class StudyName(val get: String)

/** Implicits and helpers for working with instance of the companion class */
object StudyName {
  import com.github.mjakubowski84.parquet4s.ParquetSchemaResolver.TypedSchemaDef
  import com.github.mjakubowski84.parquet4s.{BinaryValue, RequiredValueEncoder}
  import ParquetTools.{buildRequiredEncoder, buildStringSchema}

  /** Encoder {@code StudyName} as Parquet, making it required. */
  implicit val studyNameEncoder: RequiredValueEncoder[StudyName] =
    buildRequiredEncoder((sn: StudyName) => BinaryValue(sn.get))

  /** Schema for encoding {@code StudyName} as Parquet, making it required. */
  implicit val studyNameSchema: TypedSchemaDef[StudyName] = buildStringSchema(true).typed[StudyName]
}
