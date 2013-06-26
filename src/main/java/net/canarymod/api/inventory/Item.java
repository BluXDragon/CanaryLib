package net.canarymod.api.inventory;

import net.canarymod.api.nbt.CompoundTag;

/**
 * This wraps an item stack
 * 
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Jos
 */
public interface Item extends Cloneable {

    /**
     * gets this item's id
     * 
     * @return
     */
    public int getId();

    /**
     * Set this Items Id
     */
    public void setId(int id);

    /**
     * Gets this item's damage value
     * 
     * @return
     */
    public int getDamage();

    /**
     * Sets this item's damage value
     * 
     * @param damage
     */
    public void setDamage(int damage);

    /**
     * Gets this item's quantity
     * 
     * @return
     */
    public int getAmount();

    /**
     * Sets this item's quantity
     * 
     * @param amount
     */
    public void setAmount(int amount);

    /**
     * Gets this item's max amount in a stack
     * 
     * @return
     */
    public int getMaxAmount();

    /**
     * Sets this item's max amount in a stack
     * 
     * @param amount
     */
    public void setMaxAmount(int amount);

    /**
     * Get the inventory slot for this item
     * 
     * @return
     */
    public int getSlot();

    /**
     * Return the Type of this item.
     * 
     * @return
     */
    public ItemType getType();

    /**
     * Set the inventory slot of this item
     * 
     * @param slot
     */
    public void setSlot(int slot);

    /**
     * Return the BaseItem for this ItemStack, containing item statistics like
     * max stack size, and max amount of damage
     * 
     * @return
     */
    public BaseItem getBaseItem();

    /**
     * gets whether this item is enchanted
     * 
     * @return
     */
    public boolean isEnchanted();

    /**
     * gets the first enchantment of this item if exists
     * 
     * @return
     */
    public Enchantment getEnchantment();

    /**
     * gets the enchantment at the specified index if exists
     * 
     * @param index
     * @return
     */
    public Enchantment getEnchantment(int index);

    /**
     * gets an array of enchantments for this item if they exist
     * 
     * @return
     */
    public Enchantment[] getEnchantments();

    /**
     * adds enchantments to this item
     * 
     * @param enchantments
     */
    public void addEnchantments(Enchantment... enchantments);

    /**
     * sets this item's enchantments (removes all others)
     * 
     * @param enchantment
     */
    public void setEnchantments(Enchantment... enchantment);

    /**
     * removes specified enchantment from this item
     * 
     * @param enchantment
     */
    public void removeEnchantment(Enchantment enchantment);

    /**
     * removes all enchantments from this item
     */
    public void removeAllEnchantments();

    /**
     * Checks if this item has a display name.
     * 
     * @return {@code true} if has name; {@code false} if not
     */
    public boolean hasDisplayName();

    /**
     * Gets the visible name of this item.
     * Names can be set using an anvil or {@link #setName(java.lang.String)}.
     * 
     * @return The item name
     */
    public String getDisplayName();

    /**
     * Sets the visible name of this item.
     * Equivalent to renaming this item using an anvil.
     * 
     * @param name
     *            The item's new name
     */
    public void setDisplayName(String name);

    /**
     * Removes the display name if present
     */
    public void removeDisplayName();

    /**
     * Gets the cost to repair the Item
     * 
     * @return repair cost
     */
    public int getRepairCost();

    /**
     * Sets the cost to repair the Item
     * 
     * @param cost
     *            the repair cost
     */
    public void setRepairCost(int cost);

    /**
     * Returns the text that shows up under this item's name in the player's inventory.
     * 
     * @return the lore or {@code null} if no lore
     */
    public String[] getLore();

    /**
     * Sets the text that shows up under the item's name in the player's inventory
     * 
     * @param lore
     *            The lore to set, each line should be in a separate string in the array
     */
    public void setLore(String... lore);

    /**
     * Checks if the Item has lore
     * 
     * @return {@code true} if has lore; {@code false} if not
     */
    public boolean hasLore();
    
	/**
	 * Colour this item (leather armour, etc)
	 * This will convert the given values to a single RGB integer (Red<<16 + Green<<8 + Blue)
	 * 
	 * @param red
	 *            the red value (0-255)
	 * @param green
	 *            the green value (0-255)
	 * @param blue
	 *            the blue value (0-255)
	 */
	public void colourItem(int red, int green, int blue){
		if (red < 0)red = 0;
		if (red > 255)red = 255;
		if (green < 0)green = 0;
		if (green > 255)green = 255;
		if (blue < 0)blue = 0;
		if (blue > 255)blue = 255;
		int rgb = red;
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
		colourItem(rgb);
	}
	
	/**
	 * Colour this item (leather armour, etc)
	 * 
	 * @param rgbInt
	 *            The RGB value (Red<<16 + Green<<8 + Blue)
	 */
	public void colourItem(int rgbInt){
		if (getType() != ItemType.LeatherBoots && getType() != ItemType.LeatherLeggings && getType() != ItemType.LeatherChestplate && getType() != ItemType.LeatherHelmet)return;
		CompoundTag nbt;
		CompoundTag tag;
		if (hasDataTag()){
			nbt = getDataTag();
			tag = nbt.getCompoundTag("display");
		}else{
			nbt = Canary.factory().getNBTFactory().newCompoundTag("tag");
			tag = Canary.factory().getNBTFactory().newCompoundTag("display");
			nbt.put("display", tag);
			setDataTag(nbt);
		}
		tag.put("color", rgbInt);
	}

    /**
     * Checks if the Item has a MetaTag or not
     * 
     * @return {@code true} if MetaTag is present; {@code false} otherwise
     * @see #getMetaTag()
     */
    public boolean hasMetaTag();

    /**
     * Returns an CompoundTag that is saved with this object.<br>
     * This tag is for plugin use only.<br>
     * Changing values in this tag will not affect the behavior of the object.
     * 
     * @return the CompoundTag of special metadata
     */
    public CompoundTag getMetaTag();

    /**
     * Checks if the Item has a DataTag
     * 
     * @return {@code true} if has DataTag; {@code false} if not
     */
    public boolean hasDataTag();

    /**
     * Gets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     * 
     * @param tag
     *            the data tag
     */
    public CompoundTag getDataTag();

    /**
     * Sets the tag containing data for this item.
     * Should be named 'tag'.
     * Setting this to null removes name and lore data.
     * 
     * @param tag
     *            the data tag
     */
    public void setDataTag(CompoundTag tag);

    /**
     * Writes this item's data to an NBTTagCompound.
     * 
     * @param tag
     *            The tag to write to
     * @return CompoundTag
     */
    public CompoundTag writeToTag(CompoundTag tag);

    /**
     * Sets this item's data to that in an CompoundTag.
     * 
     * @param tag
     *            The tag to read from
     */
    public void readFromTag(CompoundTag tag);

    /**
     * Clones the Item
     * 
     * @return clone of the Item
     */
    public Item clone();

}
