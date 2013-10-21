package tpl.hostiledrops

import java.util.logging.Level
import cpw.mods.fml.common.FMLLog
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.Init
import cpw.mods.fml.common.Mod.PostInit
import cpw.mods.fml.common.Mod.PreInit
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.Configuration
import cpw.mods.fml.common.Mod.EventHandler

case class HostileDropsItemsConfig(
  var cooledBlazeRodItemId : Int = 4200,
  var soulTearItemId : Int = 4201,
  var netherShardItemId : Int = 4202,
  var curedFleshItemId : Int = 4203)

case class HostileDropsRecipeConfig(
  var enableBlazeRodFromGold : Boolean = true,
  var enableSoulSandToGhastTear : Boolean = true,
  var enableRottenFleshToLeather : Boolean = true,
  var enableMilkShroomsToSlime : Boolean = true,
  var enableGhastTearToNetherStar : Boolean = true,
  var enableEnderPearlFromDiamond : Boolean = true,
  var enableEnderPearlFromEmerald : Boolean = true,
  var enableMycelium : Boolean = true)

case class HostileDropsConfig(
  val items : HostileDropsItemsConfig = new HostileDropsItemsConfig,
  val recipes : HostileDropsRecipeConfig = new HostileDropsRecipeConfig)

@Mod(modid = "HostileDropsMod", name = "HostileDrops", version = "0.0.1", modLanguage = "scala")
object HostileDropsMod {
  private var _config : HostileDropsConfig = null
  private var _cooledBlazeRod : CooledBlazeRod = null
  private var _soulTear : SoulTear = null
  private var _netherShard : NetherShard = null
  private var _curedFlesh : CuredFlesh = null

  def config = _config
  def cooledBlazeRod = _cooledBlazeRod
  def soulTear = _soulTear
  def netherShard = _netherShard
  def curedFlesh = _curedFlesh

  val modName = "hostiledrops"

  @EventHandler
  def preInit(e : FMLPreInitializationEvent) {
    _config = loadConfig(e)
    _cooledBlazeRod = new CooledBlazeRod(config.items.cooledBlazeRodItemId)
    _soulTear = new SoulTear(config.items.soulTearItemId)
    _netherShard = new NetherShard(config.items.netherShardItemId)
    _curedFlesh = new CuredFlesh(config.items.curedFleshItemId)
  }

  @EventHandler
  def init(e : FMLInitializationEvent) {
    RecipeManager.addRecipes
  }

  @EventHandler
  def postInit(e : FMLPostInitializationEvent) {
  }

  private def loadConfig(ev : FMLPreInitializationEvent) : HostileDropsConfig = {
    FMLLog.log(Level.FINE, "HostileDrops: Loading configuration")

    val defaults = new HostileDropsConfig

    ConfigLoader.load(ev.getSuggestedConfigurationFile)((cfg : ConfigurationProxy, config : HostileDropsConfig) => {
      config.items.cooledBlazeRodItemId = cfg.getItem("cooledBlazeRod", defaults.items.cooledBlazeRodItemId)
      config.items.soulTearItemId = cfg.getItem("soulTear", defaults.items.soulTearItemId)
      config.items.netherShardItemId = cfg.getItem("netherShard", defaults.items.netherShardItemId)
      config.items.curedFleshItemId = cfg.getItem("curedFlesh", defaults.items.curedFleshItemId)
      config.recipes.enableBlazeRodFromGold = cfg.getBoolean("recipes", "enableBlazeRodFromGold", defaults.recipes.enableBlazeRodFromGold)
      config.recipes.enableSoulSandToGhastTear = cfg.getBoolean("recipes", "enableSoulSandToGhastTear", defaults.recipes.enableSoulSandToGhastTear)
      config.recipes.enableRottenFleshToLeather = cfg.getBoolean("recipes", "enableRottenFleshToLeather", defaults.recipes.enableRottenFleshToLeather)
      config.recipes.enableMilkShroomsToSlime = cfg.getBoolean("recipes", "enableMilkShroomsToSlime", defaults.recipes.enableMilkShroomsToSlime)
      config.recipes.enableGhastTearToNetherStar = cfg.getBoolean("recipes", "enableGhastTearToNetherStar", defaults.recipes.enableGhastTearToNetherStar)
      config.recipes.enableEnderPearlFromDiamond = cfg.getBoolean("recipes", "enableEnderPearlFromDiamond", defaults.recipes.enableEnderPearlFromDiamond)
      config.recipes.enableEnderPearlFromEmerald = cfg.getBoolean("recipes", "enableEnderPearlFromEmerald", defaults.recipes.enableEnderPearlFromEmerald)
      config.recipes.enableMycelium = cfg.getBoolean("recipes", "enableMycelium", defaults.recipes.enableMycelium)
    })

  }

}

