package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VanillaBlocks {

	public static final DeferredRegister<Block> VANILLA_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Main.MOD_ID);
	
	public static final RegistryObject<Block> GOLD_IRON_ORE = VANILLA_BLOCKS.register("gold_copper_ore",
			() -> new Block(AbstractBlock.Properties.from(Blocks.GOLD_ORE).harvestTool(ToolType.PICKAXE).harvestLevel(2)
					.setRequiresTool()));
}
