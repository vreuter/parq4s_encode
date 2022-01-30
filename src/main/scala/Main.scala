import java.io.File
import scala.util.Using
import com.github.mjakubowski84.parquet4s.{ParquetWriter, Path => ParquetPath}

object Main {
  def main(args: Array[String]): Unit = {
    val basePqtOut = new File("base.out.pqt")
    val casePqtOut = new File("case.out.pqt")
    Using.Manager { use =>
      val writerBase = use(ParquetWriter.of[RecordBase].build(ParquetPath(basePqtOut.getPath)))
      val writerCase = use(ParquetWriter.of[RecordCase].build(ParquetPath(casePqtOut.getPath)))
      println(s"Writing Parquet: ${basePqtOut}")
      List(RecordBase(1, new StudyNameBasicClass("a")), RecordBase(2, new StudyNameBasicClass("b"))).foreach(r =>
        writerBase.write(r)
      )
      println(s"Writing Parquet: ${casePqtOut}")
      List(RecordCase(1, StudyNameCaseClass("a")), RecordCase(2, StudyNameCaseClass("b"))).foreach(r =>
        writerCase.write(r)
      )
    }
    println("Done.")
  }
}
