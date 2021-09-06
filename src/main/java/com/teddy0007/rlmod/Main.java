package com.teddy0007.rlmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.teddy0007.rlmod.core.init.ModArmor;
import com.teddy0007.rlmod.core.init.ModBlockItems;
import com.teddy0007.rlmod.core.init.ModBlocks;
import com.teddy0007.rlmod.core.init.ModContainerTypes;
import com.teddy0007.rlmod.core.init.ModFeatures;
import com.teddy0007.rlmod.core.init.ModFood;
import com.teddy0007.rlmod.core.init.ModItems;
import com.teddy0007.rlmod.core.init.ModMachines;
import com.teddy0007.rlmod.core.init.ModNotConsumedItems;
import com.teddy0007.rlmod.core.init.ModRecipes;
import com.teddy0007.rlmod.core.init.ModTileEntitiyTypes;
import com.teddy0007.rlmod.core.init.ModTools;
import com.teddy0007.rlmod.core.init.VanillaBlocks;
import com.teddy0007.rlmod.core.init.VanillaItems;
import com.teddy0007.rlmod.core.itemgroup.ModItemGroup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("rlmod")
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD)
public class Main {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "rlmod";

	public Main() {

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addGenericListener(IRecipeSerializer.class, ModRecipes::registerRecipes);

		ModItems.ITEMS.register(bus);
		VanillaItems.VANILLA_ITEMS.register(bus);
		ModFood.FOOD.register(bus);
		ModNotConsumedItems.NOT_CONSUMED_ITEMS.register(bus);
		ModTools.TOOLS.register(bus);
		ModArmor.ARMOR.register(bus);
		ModBlocks.BLOCKS.register(bus);
		ModBlockItems.BLOCKITEMS.register(bus);
		ModMachines.MACHINEBLOCKS.register(bus);
		ModContainerTypes.CONTAINER_TYPES.register(bus);
		ModTileEntitiyTypes.TILE_ENTITY_TYPE.register(bus);
		VanillaBlocks.VANILLA_BLOCKS.register(bus);

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModFeatures::addOres);
		MinecraftForge.EVENT_BUS.register(this);

	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().group(ModItemGroup.ITEMGROUP))
					.setRegistryName(block.getRegistryName()));
		});
	}
}
