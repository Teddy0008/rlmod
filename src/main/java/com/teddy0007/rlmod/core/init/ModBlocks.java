package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

	public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE).harvestTool(ToolType.PICKAXE).harvestLevel(1)
					.setRequiresTool()));
	public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE)
					.harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE).harvestTool(ToolType.PICKAXE).harvestLevel(1)
					.setRequiresTool()));
	public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE)
					.harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Block> BRONZE_BLOCK = BLOCKS.register("bronze_block",
			() -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE)
					.harvestLevel(2).setRequiresTool()));
}
