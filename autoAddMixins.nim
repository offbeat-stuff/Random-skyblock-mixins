import strutils,os,sequtils

var mixinsStr = """
{
  "required": true,
  "minVersion": "0.8",
  "package": "io.github.offbeat_stuff.random_skyblock_mixins.mixin",
  "compatibilityLevel": "JAVA_17",
  "plugin": "io.github.offbeat_stuff.random_skyblock_mixins.RandomSkyblockMixinsConfigPlugin",
  "mixins": [
    $1
  ],
  "client": [],
  "injectors": {
    "defaultRequire": 1
  }
}"""

echo mixinsStr

proc listAllJavaFiles(dir: string): seq[string]=
  for i in walkDirRec(dir,relative = true):
    if i.endsWith(".java"):
      result.add(i.replace('/','.').replace(".java",""))

let mixins = listAllJavaFiles("src/main/java/io/github/offbeat_stuff/random_skyblock_mixins/mixin").mapIt('"' & it & '"').join(",\n    ")

writeFile("src/main/resources/random_skyblock_mixins.mixins.json",mixinsStr % mixins)
