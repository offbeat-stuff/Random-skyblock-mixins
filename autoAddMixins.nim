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

proc listAllJavaFiles(dir: string): seq[string]=
  for i in walkDirRec(dir,relative = true):
    if i.endsWith(".java"):
      result.add(i.replace('/','.').replace(".java",""))

let mixins = listAllJavaFiles("src/main/java/io/github/offbeat_stuff/random_skyblock_mixins/mixin").mapIt('"' & it & '"').join(",\n    ")

writeFile("src/main/resources/random_skyblock_mixins.mixins.json",mixinsStr % mixins)

var baseString = """
package io.github.offbeat_stuff.random_skyblock_mixins;

import org.quiltmc.loader.api.QuiltLoader;
import net.caffeinemc.caffeineconfig.AbstractCaffeineConfigMixinPlugin;
import net.caffeinemc.caffeineconfig.CaffeineConfig;

public class RandomSkyblockMixinsConfigPlugin extends AbstractCaffeineConfigMixinPlugin {

  @Override
  protected CaffeineConfig createConfig() {
    return CaffeineConfig.builder("RandomSkyblockMixins")
        $1
        // .withInfoUrl("https://example.org")
        .build(QuiltLoader.getConfigDir().resolve("randomskyblockmixins.properties"));
  }

  @Override
  protected String mixinPackageRoot() {
    return "io.github.offbeat_stuff.random_skyblock_mixins.mixin.";
  }
}
"""

proc listAllMixinDirs(dir: string): seq[string]=
  for i in walkDir(dir,true):
    if i.kind == pcDir:
      result.add(i.path)

var mixinPlugin = listAllMixinDirs("src/main/java/io/github/offbeat_stuff/random_skyblock_mixins/mixin").mapIt(".addMixinOption(\"" & it & "\", false)").join("\n        ")
writeFile("src/main/java/io/github/offbeat_stuff/random_skyblock_mixins/RandomSkyblockMixinsConfigPlugin.java",baseString % mixinPlugin)
