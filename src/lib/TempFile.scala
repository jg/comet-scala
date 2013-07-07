package Comet
import java.io._

object TempFile {
  def apply() =
    new TempFile(File.createTempFile("comet_tmp_", ""))

  implicit def TempFileToJavaFile(f: TempFile): File = f.toFile
}

class TempFile(f: File) {
  def toFile: File = f
}
