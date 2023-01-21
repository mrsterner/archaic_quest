package com.obsidian_core.archaic_quest.common.network;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.network.message.S2CUpdateDoorState;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketHandler {

    private static final String PROTOCOL_NAME = "ARCHAIC_Q";
    /** The network channel our mod will be
     *  using when sending messages. */
    public static final SimpleChannel CHANNEL = createChannel();

    private int messageIndex;

    private static SimpleChannel createChannel() {
        return NetworkRegistry.ChannelBuilder
                .named(ArchaicQuest.resourceLoc("channel"))
                .serverAcceptedVersions(PROTOCOL_NAME::equals)
                .clientAcceptedVersions(PROTOCOL_NAME::equals)
                .networkProtocolVersion(() -> PROTOCOL_NAME)
                .simpleChannel();
    }

    public final void registerMessages() {
        // Server -> Client
        registerMessage(S2CUpdateDoorState.class, S2CUpdateDoorState::encode, S2CUpdateDoorState::decode, S2CUpdateDoorState::handle);

        // Client -> Server
    }

    public <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) {
        CHANNEL.registerMessage(this.messageIndex++, messageType, encoder, decoder, messageConsumer, Optional.empty());
    }

    /**
     * Sends the specified message to the client.
     *
     * @param message The message to send to the client.
     * @param player The player client that should receive this message.
     * @param <MSG> Packet type.
     */
    public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
        CHANNEL.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
}
