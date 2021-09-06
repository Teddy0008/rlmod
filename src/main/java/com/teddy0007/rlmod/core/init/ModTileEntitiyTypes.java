package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.te.CrusherTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntitiyTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

	public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("crusher_tile_entity_type",
					() -> TileEntityType.Builder.create(CrusherTileEntity::new, ModMachines.CRUSHER.get()).build(null));

}