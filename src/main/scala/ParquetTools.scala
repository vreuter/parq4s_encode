object ParquetTools {
  import org.apache.parquet.schema.{LogicalTypeAnnotation, PrimitiveType} // dependency solved by parquet4s
  import com.github.mjakubowski84.parquet4s.{RequiredValueEncoder, Value, ValueCodecConfiguration}
  import com.github.mjakubowski84.parquet4s.{LogicalTypes, SchemaDef}

  /**
   * Create a Parquet encoder for a value that's required in a schema.
   * @param buildValue How to make a {code V} from a {@code T}
   * @tparam T The 'abstract' type to encode
   * @tparam V The 'concrete' data type to write
   * @return A Parquet encoder for values of type {@code T}
   */
  def buildRequiredEncoder[T, V <: Value](buildValue: T => V): RequiredValueEncoder[T] = {
    new RequiredValueEncoder[T] {
      def encodeNonNull(data: T, configuration: ValueCodecConfiguration): V = buildValue(data)
    }
  }

  /** Create a schema that can be used for types to encode as {@code String}. */
  val buildStringSchema: Boolean => SchemaDef =
    buildSchemaDef(PrimitiveType.PrimitiveTypeName.BINARY, LogicalTypes.StringType) _

  // Create a basic schema definition, which can then by typed by called .typed[TypeName].
  private[this] def buildSchemaDef(primitive: PrimitiveType.PrimitiveTypeName, logical: LogicalTypeAnnotation)(
    required: Boolean
  ): SchemaDef = {
    SchemaDef.primitive(primitiveType = primitive, required = required, logicalTypeAnnotation = Option(logical))
  }
}
