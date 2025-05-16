package net.coreprotect.thread;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.coreprotect.CoreProtect;
import net.coreprotect.config.Config;
import net.coreprotect.config.ConfigFile;
import net.coreprotect.config.ConfigHandler;
import net.coreprotect.language.Language;
import net.coreprotect.language.Phrase;
import net.coreprotect.utility.Chat;
import net.coreprotect.utility.VersionUtils;

public class NetworkHandler extends Language implements Runnable {

    private boolean startup = true;
    private boolean background = false;
    private boolean translate = true;
    private static String latestVersion = null;
    private static String latestEdgeVersion = null;
    private static String donationKey = null;

    public NetworkHandler(boolean startup, boolean background) {
        this.startup = startup;
        this.background = background;
    }

    public static String latestVersion() {
        return latestVersion;
    }

    public static String latestEdgeVersion() {
        return latestEdgeVersion;
    }

    public static String donationKey() {
        return donationKey;
    }

    @Override
    public void run() {
    }
}
