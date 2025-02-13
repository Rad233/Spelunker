package de.leximon.spelunker.mixin.client;

import com.mojang.authlib.GameProfile;
import de.leximon.spelunker.SpelunkerMod;
import de.leximon.spelunker.SpelunkerModClient;
import de.leximon.spelunker.core.SpelunkerEffectRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.encryption.PlayerPublicKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {


    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile, @Nullable PlayerPublicKey publicKey) {
        super(world, profile, publicKey);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickInject(CallbackInfo ci) {
        SpelunkerEffectRenderer renderer = SpelunkerModClient.spelunkerEffectRenderer;
        if (renderer.setActive(hasStatusEffect(SpelunkerMod.STATUS_EFFECT_SPELUNKER)))
            renderer.clear();
    }

}
