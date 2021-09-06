package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.machines.CrusherBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMachines {

	public static final DeferredRegister<Block> MACHINEBLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

	public static final RegistryObject<Block> CRUSHER = MACHINEBLOCKS.register("crusher",
			() -> new CrusherBlock(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE)
					.harvestLevel(2).setRequiresTool()));
}
