package com.teddy0007.rlmod.core.init;

import com.teddy0007.rlmod.Main;
import com.teddy0007.rlmod.common.container.CrusherContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

	public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONTAINER_TYPE = CONTAINER_TYPES
			.register("crusher", () -> IForgeContainerType.create(CrusherContainer::new));
}
