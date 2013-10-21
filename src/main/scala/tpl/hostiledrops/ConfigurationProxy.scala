package tpl.hostiledrops
import net.minecraftforge.common.Configuration

class ConfigurationProxy(cfg: Configuration) {
	def getItem(name: String, default: Int) : Int = {
	  cfg.getItem(name, default).getInt()
	}
	def getBoolean(category: String, name: String, default: Boolean) : Boolean = {
	  cfg.get(category, name, default).getBoolean(default)
	}
}