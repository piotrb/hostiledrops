package tpl.hostiledrops

import java.io.File
import java.util.logging.Level

import cpw.mods.fml.common.FMLLog
import net.minecraftforge.common.Configuration

object ConfigLoader {

  def load(file : File)(block : (ConfigurationProxy, HostileDropsConfig) => Unit) : HostileDropsConfig = {
    val cfg = new Configuration(file)
    var config = new HostileDropsConfig
    try {
      cfg.load()
      block(new ConfigurationProxy(cfg), config)
      config
    } catch {
      case e : Exception =>
        FMLLog.log(Level.SEVERE, e, "Exception caught when trying to load the configuration for HostileDrops!  Loading defaults.")
        new HostileDropsConfig
    } finally {
      cfg.save()
    }
  }

}