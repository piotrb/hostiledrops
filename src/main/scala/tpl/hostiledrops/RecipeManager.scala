package tpl.hostiledrops

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack
import net.minecraft.item.Item
import net.minecraft.block.Block
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.oredict.ShapelessOreRecipe
import net.minecraftforge.oredict.ShapedOreRecipe

object RecipeManager {

  def addRecipes {
    addGhastTearToNetherStarRecipe
    addMilkShroomsRecipe
    addBlazeRodFromGoldRecipe
    addSoulSandToGhastTearRecipe
    addEnderPearlRecipe
    addRottenFleshToLeatherRecipe
    addMyceliumRecipe
  }

  private def config = HostileDropsMod.config
  
  private def addMilkShroomsRecipe {
    if (config.recipes.enableMilkShroomsToSlime) {
      OreDictionary.registerOre("blockMushroom", new ItemStack(Block.mushroomBrown))
      OreDictionary.registerOre("blockMushroom", new ItemStack(Block.mushroomRed))

      // any mushroom + milk -> slime
      GameRegistry.addRecipe(
        new ShapelessOreRecipe(
          Item.slimeBall,
          Item.bucketMilk,
          "blockMushroom"))
    }
  }

  private def addBlazeRodFromGoldRecipe {
    if (config.recipes.enableBlazeRodFromGold) {
      // 2x Gold Ingot -> Cooled Blaze Rod
      GameRegistry.addRecipe(
        new ShapedOreRecipe(
          HostileDropsMod.cooledBlazeRod,
          "G",
          "G",
          new Character('G'),
          Item.ingotGold))
      // Cooled Blaze Rod -> Blaze Rod
      GameRegistry.addSmelting(HostileDropsMod.cooledBlazeRod.itemID, new ItemStack(Item.blazeRod), 0.1f);
    }
  }

  private def addSoulSandToGhastTearRecipe {
    if (config.recipes.enableSoulSandToGhastTear) {
      // 8x Soul Tear + TNT -> Ghast Tear
      GameRegistry.addRecipe(
        new ShapedOreRecipe(
          Item.ghastTear,
          "ttt",
          "tTt",
          "ttt",
          new Character('t'),
          HostileDropsMod.soulTear,
          new Character('T'),
          Block.tnt))
      // Soul Sand -> Soul Tear
      GameRegistry.addSmelting(Block.slowSand.blockID, new ItemStack(HostileDropsMod.soulTear), 0.1f)
    }
  }

  private def addGhastTearToNetherStarRecipe {
    if (config.recipes.enableGhastTearToNetherStar) {
      // 5x Ghast Tear -> NetherShard
      GameRegistry.addRecipe(
        new ShapedOreRecipe(
          HostileDropsMod.netherShard,
          " s ",
          "sss",
          " s ",
          new Character('s'),
          Item.ghastTear))

      // 5x NetherShard -> NetherStar
      GameRegistry.addRecipe(
        new ShapedOreRecipe(
          Item.netherStar,
          " s ",
          "sss",
          " s ",
          new Character('s'),
          HostileDropsMod.netherShard))
    }
  }

  private def addEnderPearlRecipe {
    if (config.recipes.enableEnderPearlFromDiamond || config.recipes.enableEnderPearlFromEmerald) {
      if (config.recipes.enableEnderPearlFromDiamond) {
        OreDictionary.registerOre("enderEyeMiddle", new ItemStack(Item.diamond))
      }
      if (config.recipes.enableEnderPearlFromEmerald) {
        OreDictionary.registerOre("enderEyeMiddle", new ItemStack(Item.emerald))
      }

      // 4 GunPowder + 4 Redstone + (Diamond/Emerald) -> Ender Pearl
      GameRegistry.addRecipe(
        new ShapedOreRecipe(
          Item.enderPearl,
          "grg",
          "rXr",
          "grg",
          new Character('g'),
          Item.gunpowder,
          new Character('r'),
          Item.redstone,
          new Character('X'),
          "enderEyeMiddle"))
    }
  }

  private def addRottenFleshToLeatherRecipe {
    if (config.recipes.enableRottenFleshToLeather) {
      // Sugar + Rotten Flesh + BoneMeal -> Cured Flesh
      GameRegistry.addRecipe(
        new ShapedOreRecipe(
          HostileDropsMod.curedFlesh,
          "s",
          "F",
          "b",
          new Character('s'),
          Item.sugar,
          new Character('F'),
          Item.rottenFlesh,
          new Character('b'),
          "dyeWhite"))
      // Cured Flesh -> Leather
      GameRegistry.addSmelting(HostileDropsMod.curedFlesh.itemID, new ItemStack(Item.leather), 0.1f)
    }
  }

  private def addMyceliumRecipe {
    if (config.recipes.enableMycelium) {
      OreDictionary.registerOre("fluidWater", new ItemStack(Item.bucketWater))
      OreDictionary.registerOre("fluidWater", new ItemStack(Item.potion, 1, 0))

      // red + brown + dirt + water -> mycelium
      GameRegistry.addRecipe(
        new ShapelessOreRecipe(
          Block.mycelium,
          Block.mushroomRed,
          Block.mushroomBrown,
          Block.dirt,
          "fluidWater"))
    }
  }

}