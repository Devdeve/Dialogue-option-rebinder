package com.DialogueOptionRebinderPlugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("dialogue-option-rebinder")
public interface DialogueOptionRebinderConfig extends Config
{
	@ConfigItem(
			keyName = "rebindOne",
			name = "Option 1 key",
			description = "Rebind the 1 key in dialogue options",
			position = 0
	)
	default Keybind rebindDialogueOne()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "rebindTwo",
			name = "Option 2 key",
			description = "Rebind the 2 key in dialogue options",
			position = 0
	)
	default Keybind rebindDialogueTwo()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "rebindThree",
			name = "Option 3 key",
			description = "Rebind the 3 key in dialogue options",
			position = 0
	)
	default Keybind rebindDialogueThree()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "rebindFour",
			name = "Option 4 key",
			description = "Rebind the 4 key in dialogue options",
			position = 0
	)
	default Keybind rebindDialogueFour()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "rebindFive",
			name = "Option 5 key",
			description = "Rebind the 5 key in dialogue options",
			position = 0
	)
	default Keybind rebindDialogueFive()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
			keyName = "rebindSix",
			name = "Option 6 key",
			description = "Rebind the 6 key in dialogue options",
			position = 0
	)
	default Keybind rebindDialogueSix()
	{
		return Keybind.NOT_SET;
	}
}
