package com.DialogueOptionRebinderPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DialogueOptionRebinderPlugin.class);
		RuneLite.main(args);
	}
}