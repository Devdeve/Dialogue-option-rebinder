package com.DialogueOptionRebinderPlugin;


import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.WidgetClosed;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import static net.runelite.client.config.Keybind.NOT_SET;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
		name = "Dialogue Option Rebinder",
		description = "Allows use of other keybinds for dialogue options",
		tags = {"dialog", "dialogue", "chat", "options", "keys", "npc"}
)
@Slf4j
public class DialogueOptionRebinderPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	ClientThread clientThread;
	@Inject
	private DialogueOptionRebinderConfig config;

	private int componentID = ComponentID.DIALOG_OPTION_OPTIONS;
	private int groupID = 219;
	private boolean swapKeybind = false;
	@Override
	protected void startUp() throws Exception
	{
		swapKeybind = false;
	}

	@Override
	protected void shutDown() throws Exception
	{
		swapKeybind = true;
	}

	@Subscribe
	void onWidgetLoaded(WidgetLoaded event){
		if (event.getGroupId() != groupID){
				return;
			}
			swapKeybind = true;
	}
	@Subscribe
	void onWidgetClosed(WidgetClosed event)
	{
		if (event.getGroupId() != groupID) {
			return;
		}
			swapKeybind = false;
	}
	@NoArgsConstructor
	@Accessors(fluent = true, chain = true)
	class DialogueOptionItem {
		@Setter
		Widget dialogueRebindWidget;


		public void keyChecker() {

			Object[] keyListener = dialogueRebindWidget.getOnKeyListener();
			if (keyListener != null && keyListener.length == 11) {
				String inputKey = String.valueOf(keyListener[7]);
				int keyCode = 0;

				switch (inputKey)
				{
					case "1":
						if (config.rebindDialogueOne() == NOT_SET) {
							return;
						}
						keyCode = config.rebindDialogueOne().getKeyCode();
						break;
					case "2":
						if (config.rebindDialogueTwo() == NOT_SET) {
							return;
						}
						keyCode = config.rebindDialogueTwo().getKeyCode();
						break;
					case "3":
						if (config.rebindDialogueThree() == NOT_SET) {
							return;
						}
						keyCode = config.rebindDialogueThree().getKeyCode();
						break;
					case "4":
						if (config.rebindDialogueFour() == NOT_SET) {
							return;
						}
						keyCode = config.rebindDialogueFour().getKeyCode();
						break;
					case "5":
						if (config.rebindDialogueFive() == NOT_SET) {
							return;
						}
						keyCode = config.rebindDialogueFive().getKeyCode();
						break;
					case "6":
						if (config.rebindDialogueSix() == NOT_SET) {
							return;
						}
						keyCode = config.rebindDialogueSix().getKeyCode();
						break;
				}

				if (keyCode != 0)
				{
					setOnKeyListener(keyCode);
				}
			}
		}

		void setOnKeyListener(int keyCode) {
			Object[] listener = dialogueRebindWidget.getOnKeyListener();
			if (listener == null) {
				return;
			}
			listener[7] = String.valueOf((char) keyCode).toLowerCase();
			dialogueRebindWidget.setOnKeyListener(listener);
			dialogueRebindWidget.revalidate();
		}
	}
	@Subscribe
	void onClientTick(ClientTick e){
		if (!swapKeybind){
			return;
		}
		Widget dialogueWidget = client.getWidget(groupID, 1);
		if (dialogueWidget != null){
			for (Widget dialogueRebindWidget : dialogueWidget.getDynamicChildren()){
				if (dialogueRebindWidget.getOnKeyListener() == null){
					continue;
				}
				new DialogueOptionItem()
						.dialogueRebindWidget(dialogueRebindWidget)
						.keyChecker();
			}
		}
	}

	@Provides
	DialogueOptionRebinderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DialogueOptionRebinderConfig.class);
	}
}
