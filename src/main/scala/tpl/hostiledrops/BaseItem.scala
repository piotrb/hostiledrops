package tpl.hostiledrops

import cpw.mods.fml.relauncher.SideOnly
import cpw.mods.fml.relauncher.Side
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import java.util.logging.Level
import cpw.mods.fml.common.FMLLog
import net.minecraft.util.Icon
import cpw.mods.fml.common.registry.LanguageRegistry

import java.lang.String;

abstract class BaseItem(id : Int) extends Item(id) {
  setMaxStackSize(64)
  setMaxDamage(0)
  setCreativeTab(CreativeTabs.tabMisc)
  setUnlocalizedName(getUnlocalizedName)

  @SideOnly(Side.CLIENT)
  override def registerIcons(icon : IconRegister) {
    itemIcon = icon.registerIcon(HostileDropsMod.modName + ":" + getUnlocalizedName);
  }

  override def getUnlocalizedName = {
    this.getClass.getSimpleName
  }

  def setLocalizedName(name : String) {
    LanguageRegistry.instance.addStringLocalization("item." + getUnlocalizedName + ".name", name)
  }

  def setLocalizedName(locale : String, name : String) {
    LanguageRegistry.instance.addStringLocalization("item." + getUnlocalizedName + ".name", locale, name)
  }
}