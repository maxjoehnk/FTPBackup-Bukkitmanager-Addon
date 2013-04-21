package org.efreak.bukkitmanager.addon.ftpbackup;

import org.efreak.bukkitmanager.addons.BukkitmanagerAddon;
import org.efreak.bukkitmanager.language.Language;
import org.efreak.bukkitmanager.util.BackupHelper;

public class FTPBackup extends BukkitmanagerAddon {

	@Override
	public void onLoad() {
		super.onLoad();
		name = "FTPBackup";
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		//Setup Config
		config.update("Autobackup.FTP.Enabled", true);
		config.update("Autobackup.FTP.Host", "localhost");
		config.update("Autobackup.FTP.Port", 21);
		config.update("Autobackup.FTP.Username", "minecraft");
		config.update("Autobackup.FTP.Password", "123456");
		config.update("Autobackup.FTP.Path", "backups");
		config.update("Autobackup.FTP.Logging", true);
		config.update("Autobackup.FTP.FTPS.Enabled", false);
		if (config.getBoolean("Autobackup.FTP.FTPS")) {
			config.update("Autobackup.FTP.Protocol", "");
			config.update("Autobackup.FTP.isImplicit", false);
			config.update("Autobackup.FTP.TrustManager", "none");
		}
		config.addToList("PluginUpdater.Blacklist.List", "FTPBackupAddon");
		config.update("Notifications.Autobackup.FTP.Started", true);
		config.update("Notifications.Autobackup.FTP.Finished", true);
		config.save();
		
		//Add Language Entries
		Language.addKey("en", "FTPBackup.Start", "Uploading Backup to %host%");
		Language.addKey("de", "FTPBackup.Start", "Lade Backup hoch zu %host%");
		Language.addKey("en", "FTPBackup.Connected", "Connected to %host%");
		Language.addKey("de", "FTPBackup.Connected", "Verbunden mit %host%");
		Language.addKey("en", "FTPBackup.Refused", "FTP server refused connection.");
		Language.addKey("de", "FTPBackup.Refused", "FTP Server verweigert eine Verbindung");
		Language.addKey("en", "FTPBackup.LoginError", "Couldn't upload Backup. Wrong Login Details");
		Language.addKey("de", "FTPBackup.LoginError", "Backup konnte nicht hochgeladen werden. Falsche Logininformationen");
		Language.addKey("en", "FTPBackup.Uploading", "Uploading Backup to %location%...");
		Language.addKey("de", "FTPBackup.Uploading", "Lade Backup hoch zu %location%...");
		Language.addKey("en", "FTPBackup.Uploaded", "Uploaded Backup to %host%");
		Language.addKey("de", "FTPBackup.Uploaded", "Backup hochgeladen zu %host%");
		Language.addKey("en", "FTPBackup.Error", "Error uploading Backup to %host%");
		Language.addKey("de", "FTPBackup.Error", "Fehler beim hochladen des Backups zu %host%");
		Language.addKey("en", "FTPBackup.CantConnect", "Could not connect to server %host%");
		Language.addKey("de", "FTPBackup.CantConnect", "Fehler beim verbinden mit Server %host%");
		
		//Register Backup Storage
		BackupHelper.registerBackupStorage(new FTPStorage());
	}
}