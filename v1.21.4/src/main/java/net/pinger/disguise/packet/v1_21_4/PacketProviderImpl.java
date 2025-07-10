package net.pinger.disguise.packet.v1_21_4;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.*;
import javax.annotation.Nonnull;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.pinger.disguise.annotation.PacketHandler;
import net.pinger.disguise.packet.PacketProvider;
import net.pinger.disguise.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_21_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@PacketHandler(version = "1.21.4")
public class PacketProviderImpl implements PacketProvider {
    private final Plugin plugin;
    public PacketProviderImpl(Plugin plugin) { this.plugin = plugin; }

    @Override
    public GameProfile getGameProfile(Player player) {
        return ((CraftPlayer) player).getProfile();
    }

    @Override
    public Skin getProperty(Player player) {
        GameProfile profile = getGameProfile(player);
        Collection<Property> textures = profile.getProperties().get("textures");
        return textures.stream()
            .findFirst()
            .map(p -> new Skin(p.getValue(), p.getSignature()))
            .orElse(null);
    }

    @Override
    public void updateProperties(Player player, @Nonnull Skin skin) {
        if (!(skin.getHandle() instanceof Property)) return;
        Property prop = (Property) skin.getHandle();
        GameProfile profile = getGameProfile(player);
        profile.getProperties().removeAll("textures");
        profile.getProperties().put("textures", prop);
    }

    @Override
    public void clearProperties(Player player) {
        getGameProfile(player).getProperties().removeAll("textures");
    }

    @Override
    public void sendPacket(Player player, Object... packet) {
        ServerPlayer sp = ((CraftPlayer) player).getHandle();
        for (Object p : packet) {
            sp.connection.send((Packet<?>) p);
        }
    }

    @Override
    public void sendServerPackets(Player player) {
        ServerPlayer sp = ((CraftPlayer) player).getHandle();
        ServerLevel level = sp.serverLevel();

        CommonPlayerSpawnInfo spawnInfo = sp.createCommonSpawnInfo(level);
        ClientboundRespawnPacket resp = new ClientboundRespawnPacket(spawnInfo, ClientboundRespawnPacket.KEEP_ALL_DATA);

        sendPacket(player,
            new ClientboundPlayerInfoRemovePacket(Collections.singletonList(player.getUniqueId())),
            ClientboundPlayerInfoUpdatePacket.createPlayerInitializing(Collections.singletonList(sp)),
            resp
        );

        sp.onUpdateAbilities();
        sp.connection.teleport(player.getLocation());
        sp.resetSentInfo();

        if (plugin.isEnabled()) {
            Bukkit.getScheduler().runTask(plugin, () -> {
                PlayerList pl = sp.server.getPlayerList();
                pl.sendPlayerPermissionLevel(sp);
                pl.sendLevelInfo(sp, level);
                pl.sendAllPlayerInfo(sp);
            });
        }

        PacketProvider.refreshPlayer(player, plugin);
    }
}
