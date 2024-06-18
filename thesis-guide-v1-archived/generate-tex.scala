//> using scala 3.4
//> using toolkit 0.4.0

val wd = os.pwd / "docx"
val inFiles = os.list(wd).filter(_.last.endsWith(".docx"))
val outDir = "tex-generated"

@main def GenerateTex =
  println(s"Working dir: $wd")
  println(s"Input files: ${inFiles.map(_.last)}")
  for f <- inFiles do
    val xs = f.segments.toSeq
    val last = xs.last.stripSuffix(".docx") + ".tex"
    val out = os.Path(xs.dropRight(2).appended(outDir).appended(last).mkString("/","/",""))
    println(s"Generating: $outDir/${out.last}")
    os.proc("pandoc", f, "-o", out).call()