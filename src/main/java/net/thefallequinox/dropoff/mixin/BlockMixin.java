/*
 * -------------------------------------------------------------------
 * Disruption
 * Copyright (c) 2022 SciRave
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * -------------------------------------------------------------------
 */

package net.thefallequinox.dropoff.mixin;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.thefallequinox.dropoff.Dropoff;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "onDestroyedByExplosion", at = @At("RETURN"))
    protected void disruption$blockExploded(World world, BlockPos pos, Explosion explosion, CallbackInfo ci) {
		GameEvent.Context context = GameEvent.Context.create(world.getBlockState(pos));
		world.emitGameEvent(Dropoff.BLOCK_EXPLODED, Vec3d.of(pos), context);
    }
}
