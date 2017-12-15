/*
 * Copyright 2017 lolnet.co.nz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.co.lolnet.hungergames;

import nz.co.lolnet.hungergames.commands.LolnetHungerGamesCommand;
import nz.co.lolnet.hungergames.configuration.Configuration;
import nz.co.lolnet.hungergames.listeners.HungerGamesListener;
import nz.co.lolnet.hungergames.util.Reference;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class HungerGames extends JavaPlugin {
    
    private static HungerGames instance;
    private final Configuration configuration;
    
    public HungerGames() {
        instance = this;
        configuration = new Configuration();
    }
    
    @Override
    public void onEnable() {
        getConfiguration().loadConfig();
        getCommand(Reference.PLUGIN_ID).setExecutor(new LolnetHungerGamesCommand());
        getServer().getPluginManager().registerEvents(new HungerGamesListener(), getInstance());
        getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.PLUGIN_VERSION + " has started!");
    }
    
    @Override
    public void onDisable() {
        instance = null;
        getLogger().info(Reference.PLUGIN_NAME + " v" + Reference.PLUGIN_VERSION + " has stopped!");
    }
    
    public static HungerGames getInstance() {
        return instance;
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }
    
    @Override
    public YamlConfiguration getConfig() {
        if (getConfiguration() != null) {
            return getConfiguration().getConfig();
        }
        
        return null;
    }
}