import os

proc listAllJavaFiles(dir: string): seq[string]=
  for i in walkDir(dir,true):
    if i.kind == pcDir:
      result.add(i.path)

for i in listAllJavaFiles("src/main/java/io/github/offbeat_stuff/random_skyblock_mixins/mixin"):
  echo ".addMixinOption(\"" & i & "\", false)"
