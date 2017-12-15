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

package nz.co.lolnet.hungergames.listeners;

import nz.co.lolnet.hungergames.HungerGames;
import nz.co.lolnet.hungergames.util.Reference;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HungerGamesListener implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() == null || HungerGames.getInstance().getConfig() == null) {
            return;
        }
        
        if (HungerGames.getInstance().getConfig().getBoolean("LolnetHungerGames.TeleportOnJoin")) {
            Location location = getLocation();
            if (location != null) {
                event.getPlayer().teleport(location);
            }
        }
        
        for (String command : HungerGames.getInstance().getConfig().getStringList(Reference.PLUGIN_NAME + ".Commands")) {
            command = StringUtils.replace(command, "[PLAYER]", event.getPlayer().getName());
            
            if (StringUtils.isBlank(command) || !Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)) {
                HungerGames.getInstance().getLogger().info("Failed to run '" + command + "' on '" + event.getPlayer().getName() + "'.");
            }
        }
    }
    
    private Location getLocation() {
        try {
            return Location.deserialize(HungerGames.getInstance().getConfig().getConfigurationSection("LolnetHungerGames.Location").getValues(false));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}