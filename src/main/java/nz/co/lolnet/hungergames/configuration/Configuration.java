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

package nz.co.lolnet.hungergames.configuration;

import com.google.common.io.ByteStreams;
import nz.co.lolnet.hungergames.HungerGames;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Configuration {
    
    private YamlConfiguration config;
    
    public void loadConfig() {
        if (!HungerGames.getInstance().getDataFolder().exists() && !HungerGames.getInstance().getDataFolder().mkdir()) {
            HungerGames.getInstance().getLogger().severe("Failed to create directory");
            return;
        }
        
        setConfig(loadFile("config.yml"));
    }
    
    private YamlConfiguration loadFile(String name) {
        try {
            File file = new File(HungerGames.getInstance().getDataFolder(), name);
            
            if (!file.exists() && file.createNewFile()) {
                InputStream inputStream = getClass().getResourceAsStream("/" + name);
                OutputStream outputStream = new FileOutputStream(file);
                ByteStreams.copy(inputStream, outputStream);
                HungerGames.getInstance().getLogger().info("Successfully created " + name);
            }
            
            return YamlConfiguration.loadConfiguration(file);
        } catch (IOException | NullPointerException | SecurityException ex) {
            HungerGames.getInstance().getLogger().severe("Encountered an error processing Configuration::loadFile");
            ex.printStackTrace();
            return null;
        }
    }
    
    public YamlConfiguration getConfig() {
        return config;
    }
    
    private void setConfig(YamlConfiguration config) {
        this.config = config;
    }
}