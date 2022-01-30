import com.github.mjakubowski84.parquet4s.ParquetSchemaResolver.TypedSchemaDef
import com.github.mjakubowski84.parquet4s.{BinaryValue, RequiredValueEncoder}
import ParquetTools.{buildRequiredEncoder, buildStringSchema}

/** Name of a study */
class StudyNameBasicClass(val get: String)

/** Name of a study */
case class StudyNameCaseClass(get: String)

/** Implicits and helpers for working with instance of the companion class */
object StudyNameBasicClass {

  /** Encode {@code StudyNameBasicClass} as Parquet, making it required. */
  implicit val studyNameEncoder: RequiredValueEncoder[StudyNameBasicClass] =
    buildRequiredEncoder((sn: StudyNameBasicClass) => BinaryValue(sn.get))

  /** Schema for encoding {@code StudyNameBasicClass} as Parquet, making it required. */
  implicit val studyNameSchema: TypedSchemaDef[StudyNameBasicClass] = buildStringSchema(true).typed[StudyNameBasicClass]
}

/** Implicits and helpers for working with instance of the companion class */
object StudyNameCaseClass {

  /** Encode {@code StudyNameCaseClass} as Parquet, making it required. */
  implicit val studyNameEncoder: RequiredValueEncoder[StudyNameCaseClass] =
    buildRequiredEncoder((sn: StudyNameCaseClass) => BinaryValue(sn.get))

  /** Schema for encoding {@code StudyNameCaseClass} as Parquet, making it required. */
  implicit val studyNameSchema: TypedSchemaDef[StudyNameCaseClass] = buildStringSchema(true).typed[StudyNameCaseClass]
}
