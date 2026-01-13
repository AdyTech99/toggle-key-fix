package io.github.adytech99.togglekeyfix.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ToggleKeyMapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(ToggleKeyMapping.class)
public class ToggleKeyMappingMixin extends KeyMapping {
    @Shadow
    @Final
    private boolean shouldRestore;

    @Shadow
    private boolean releasedByScreenWhenDown;

    @Shadow
    @Final
    private BooleanSupplier needsToggle;

    public ToggleKeyMappingMixin(String string, int i, Category category) {
        super(string, i, category);
    }

    @Inject(at = @At("HEAD"), method = "shouldRestoreStateOnScreenClosed", cancellable = true)
	private void shouldRestoreStateOnScreenClosed(CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        boolean bl = this.shouldRestore && this.needsToggle.getAsBoolean() && (isNotAttackDestroyOrUsePlace(key)) && this.releasedByScreenWhenDown;
        this.releasedByScreenWhenDown = false;
        cir.setReturnValue(bl);
	}

    @Unique
    private boolean isNotAttackDestroyOrUsePlace(InputConstants.Key key) {
        return !(Minecraft.getInstance().options.keyAttack.getName().equals(key.getName()) || Minecraft.getInstance().options.keyUse.getName().equals(key.getName()));
    }
}