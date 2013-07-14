import java.io._

object TempFile {
  def apply() =
    new TempFile(File.createTempFile("comet_tmp_", ""))

  implicit def TempFileToJavaFile(f: TempFile): File = f.toFile
}

case class TempFile(val file: File) {
  def toFile: File = file
}
