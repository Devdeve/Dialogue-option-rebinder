package com.dialogueOptionRebinder;


import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
	name = "Dialogue Option Rebinder",
	description = "Allows use of other keybinds for dialogue options",
	tags = {"dialog", "dialogue", "chat", "options", "keys", "npc"},
	enabledByDefault = false
)
@Slf4j
public class dialogueOptionRebinderPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	ClientThread clientThread;
	@Inject
	private dialogueOptionRebinderConfig config;

	private int componentID = ComponentID.DIALOG_OPTION_OPTIONS;
	private int groupID = 219;
	@Override
	protected void startUp() throws Exception
	{}

	@Override
	protected void shutDown() throws Exception
	{}

	@NoArgsConstructor
	@Accessors(fluent = true, chain = true)
	class DialogueOptionItem {
		@Setter
		Widget dialogueRebindWidget;


		public void keyChecker() {
			for (Object item : dialogueRebindWidget.getOnKeyListener()){
				if (Integer.valueOf(String.valueOf(dialogueRebindWidget.getOnKeyListener()[7]))== 1){
					setOnKeyListener(config.rebindDialogueOne().getKeyCode());
				}
				if (Integer.valueOf(String.valueOf(dialogueRebindWidget.getOnKeyListener()[7])) == 2){
					setOnKeyListener(config.rebindDialogueTwo().getKeyCode());
				}
				if (Integer.valueOf(String.valueOf(dialogueRebindWidget.getOnKeyListener()[7])) == 3){
					setOnKeyListener(config.rebindDialogueThree().getKeyCode());
				}
				if (Integer.valueOf(String.valueOf(dialogueRebindWidget.getOnKeyListener()[7])) == 4){
					setOnKeyListener(config.rebindDialogueFour().getKeyCode());
				}
				if (Integer.valueOf(String.valueOf(dialogueRebindWidget.getOnKeyListener()[7])) == 5){
					setOnKeyListener(config.rebindDialogueFive().getKeyCode());
				}
				if (Integer.valueOf(String.valueOf(dialogueRebindWidget.getOnKeyListener()[7])) == 6){
					setOnKeyListener(config.rebindDialogueSix().getKeyCode());
				}
			}
		}

		void setOnKeyListener(int keyCode) {
			Object[] listener = dialogueRebindWidget.getOnKeyListener();
			if (listener == null) {
				return;
			}
			listener[4] = String.valueOf(keyCode);
			dialogueRebindWidget.setOnKeyListener(listener);
			dialogueRebindWidget.revalidate();
		}
	}
	@Subscribe
	void onClientTick(ClientTick e){
		Widget dialogueWidget = client.getWidget(componentID);
		if (dialogueWidget != null){
			for (Widget dialogueRebindWidget : dialogueWidget.getChildren()){
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
	dialogueOptionRebinderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(dialogueOptionRebinderConfig.class);
	}
}
