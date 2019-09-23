package dev.latvian.kubejs.block;

import dev.latvian.kubejs.entity.EntityJS;
import dev.latvian.kubejs.item.ItemStackJS;
import dev.latvian.kubejs.player.PlayerEventJS;
import dev.latvian.kubejs.world.BlockContainerJS;
import dev.latvian.kubejs.world.WorldJS;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LatvianModder
 */
public class BlockDropsEventJS extends PlayerEventJS
{
	public final BlockEvent.HarvestDropsEvent event;
	public List<ItemStackJS> dropList;

	public BlockDropsEventJS(BlockEvent.HarvestDropsEvent e)
	{
		event = e;
	}

	@Override
	public WorldJS getWorld()
	{
		return worldOf(event.getWorld());
	}

	@Override
	public EntityJS getEntity()
	{
		return entityOf(event.getHarvester());
	}

	public BlockContainerJS getBlock()
	{
		return new BlockContainerJS(event.getWorld(), event.getPos())
		{
			@Override
			public IBlockState getBlockState()
			{
				return event.getState();
			}
		};
	}

	public int getFortuneLevel()
	{
		return event.getFortuneLevel();
	}

	public List<ItemStackJS> getDrops()
	{
		if (dropList == null)
		{
			dropList = new ArrayList<>();

			for (ItemStack stack : event.getDrops())
			{
				dropList.add(ItemStackJS.of(stack));
			}
		}

		return dropList;
	}

	public boolean isSilkTouching()
	{
		return event.isSilkTouching();
	}

	public float getDropChance()
	{
		return event.getDropChance();
	}

	public void setDropChance(float dropChance)
	{
		event.setDropChance(dropChance);
	}
}