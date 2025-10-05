/*
 * SkinsRestorer
 * Copyright (C) 2024  SkinsRestorer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.skinsrestorer.shared.update;

import ch.jalu.injector.Injector;
import lombok.RequiredArgsConstructor;
import net.skinsrestorer.builddata.BuildData;
import net.skinsrestorer.shared.log.SRLogger;
import net.skinsrestorer.shared.plugin.SRPlugin;

import javax.inject.Inject;
import java.nio.file.Files;

/**
 * Credit goes to <a href="https://github.com/InventivetalentDev/SpigetUpdater">SpigetUpdater</a>
 */
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UpdateCheckerGitHub {
    private static final String LOG_ROW = "§a----------------------------------------------";
    private final SRLogger logger;
    private final SRPlugin plugin;
    private final Injector injector;
    private boolean updateDownloaded;
    private boolean hasPrintedUpdateAvailableBanner;

    private static String getCheckerPluginVersion() {
        // 保留方法结构
        return BuildData.VERSION;
    }

    public void checkForUpdate(UpdateCause cause, UpdateDownloader downloader) {
        // 更新检查逻辑已移除，保留方法结构但不执行任何操作
        logger.debug("[UpdateCheckerGitHub] Update check skipped (disabled logic).");
    }

    public void printUpToDate(UpdateCause cause) {
        printHeader(cause);
        logger.info("§b    Version: §a%s".formatted(BuildData.VERSION));
        logger.info("§b    Commit: §a%s".formatted(BuildData.COMMIT_SHORT));
        if (cause == UpdateCause.NETWORK_DISABLED) {
            logger.info("§c    Network connections are disabled in the SkinsRestorer config (advanced.noConnections), we will not check for updates.");
        } else if (Files.exists(plugin.getDataFolder().resolve("noupdate.txt"))) {
            logger.info("§e    The updater ispdate.txt");
        } else {
            logger.info("§a    This is the latest version!");
        }
        printFooter();
    }

    public void printUpdateAvailable(UpdateCause cause, String newVersion, boolean updateDownloader) {
        // 保留方法结构，但不再执行下载逻辑
        if (hasPrintedUpdateAvailableBanner) {
            logger.info("§bA new version of SkinsRestorer (§a%s§b) is available (update check disabled).".formatted(newVersion));
        } else {
            hasPrintedUpdateAvailableBanner = true;
            printHeader(cause);
            logger.info("§b    Version: §c%s".formatted(BuildData.VERSION));
            logger.info("§b    Commit: §c%s".formatted(BuildData.COMMIT_SHORT));
            logger.info("§b    A new version (§a%s§b) is available! (update check disabled)".formatted(newVersion));
            printFooter();
        }
    }

    private void printHeader(UpdateCause cause) {
        logger.info(LOG_ROW);
        logger.info("§a    +==================+");
        logger.info("§a    |   SkinsRestorer  |");
        logger.info("§a    |------------------|");

        if (cause.isError()) {
            logger.info("§a    |    §cError Mode§a    |");
        } else {
            logger.info("§a    |   §eUpdate Check Disabled§a   |");
        }
        logger.info("§a    +==================+");
        logger.info(LOG_ROW);
    }

    private void printFooter() {
        logger.info(LOG_ROW);
        logger.info("§9Do you have issues? Read our troubleshooting guide: §ehttps://skinsrestorer.net/docs/troubleshooting");
        logger.info("§9Want to support SkinsRestorer? Consider donating: §ehttps://skinsrestorer.net/donate");
    }

    public boolean isVersionNewer(String currentVersion, String newVersion) {
        // 保留方法结构，直接返回 false，表示不再比较版本
        return false;
    }
    }
