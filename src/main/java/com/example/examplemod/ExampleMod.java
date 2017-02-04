package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		substitute(Blocks.COBBLESTONE, new ModBlock("stonebrick"));
		substitute(Blocks.LAPIS_BLOCK, new ModBlock("blockLapis"));
		substitute(Blocks.GOLD_BLOCK, new ModBlock("blockGold"));
		substitute(Blocks.IRON_BLOCK, new ModBlock("blockIron"));
		substitute(Blocks.BRICK_BLOCK, new ModBlock("brick"));
		substitute(Blocks.MOSSY_COBBLESTONE, new ModBlock("stoneMoss"));
		substitute(Blocks.DIAMOND_BLOCK, new ModBlock("blockDiamond"));
		substitute(Blocks.END_STONE, new ModBlock("whiteStone"));
		substitute(Blocks.EMERALD_BLOCK, new ModBlock("blockEmerald"));
		substitute(Blocks.COAL_BLOCK, new ModBlock("blockCoal"));
		substitute(Blocks.PURPUR_BLOCK, new ModBlock("purpurBlock"));
		substitute(Blocks.END_BRICKS, new ModBlock("endBricks"));
		substitute(Blocks.NETHER_WART_BLOCK, new ModBlock("netherWartBlock"));
		
		substitute(Blocks.GOLD_ORE, new ModBlockOre("oreGold"));
		substitute(Blocks.IRON_ORE, new ModBlockOre("oreIron"));
		substitute(Blocks.COAL_ORE, new ModBlockOre("oreCoal"));
		substitute(Blocks.LAPIS_ORE, new ModBlockOre("oreLapis"));
		substitute(Blocks.DIAMOND_ORE, new ModBlockOre("oreDiamond"));
		substitute(Blocks.EMERALD_ORE, new ModBlockOre("oreEmerald"));
		substitute(Blocks.QUARTZ_ORE, new ModBlockOre("netherquartz"));
	}
	
	public static void substitute(Block toReplace, Block newBlock) {
		substitute(toReplace, newBlock, new ItemBlock(newBlock));
	}

	public static void substitute(Block toReplace, Block newBlock, Item newItem) {
		try {
			ResourceLocation oldName = Block.REGISTRY.getNameForObject(toReplace);
			String nameToSubstitute = oldName.toString();
			String nameToRegister = ExampleMod.MODID + ":" + oldName.getResourcePath();
			
			newBlock.setRegistryName(nameToRegister);
			GameRegistry.addSubstitutionAlias(nameToSubstitute.toString(), GameRegistry.Type.BLOCK, newBlock);
			
			newItem.setRegistryName(nameToRegister);
			GameRegistry.addSubstitutionAlias(nameToSubstitute.toString(), GameRegistry.Type.ITEM, newItem);
		} catch (ExistingSubstitutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
    
    public static class ModBlock extends Block {
		public ModBlock(String name) {
			super(Material.ROCK);
    		this.setUnlocalizedName(name);
		}
    }
    
    public static class ModBlockOre extends BlockOre {
    	public ModBlockOre(String name) {
    		this.setUnlocalizedName(name);
    	}
    }
}
