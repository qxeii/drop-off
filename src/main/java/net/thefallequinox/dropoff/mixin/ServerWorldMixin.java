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

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.thefallequinox.dropoff.logic.BlockHandler;
import net.thefallequinox.dropoff.Dropoff;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

	public final HashSet<BlockPos> disruption = new HashSet<>();
	public final HashSet<BlockPos> neighborDisruptions = new HashSet<>();
	@Inject(method = "tick", at = @At("RETURN"))
	public void disruption$tickDisruptions(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
		ServerWorld world = (ServerWorld) (Object) this;
		if(disruption.size() > 0) {
			((HashSet<BlockPos>) disruption.clone()).forEach((pos) -> BlockHandler.updatePosAndNeighbors(world, pos));
			disruption.clear();
		}
		if(neighborDisruptions.size() > 0) {
			((HashSet<BlockPos>) neighborDisruptions.clone()).forEach((pos) -> BlockHandler.updatePosAndNeighbors(world, pos));
			neighborDisruptions.clear();
		}
	}
	@Inject(method = "emitGameEvent", at = @At("RETURN"))
	public void disruption$detectDisruption(GameEvent event, Vec3d pos, GameEvent.Context context, CallbackInfo CIR) {
		BlockPos blockPos = BlockPos.fromPosition(pos);

		if (event.isIn(Dropoff.ENTITY_DISRUPTION)) {
			disruption.add(blockPos);
			return;
		}
		if (event.isIn(Dropoff.DISRUPTION)) {
			disruption.add(blockPos);
			return;
		}
		if (event.isIn(Dropoff.ENTITY_NEIGHBOR_DISRUPTION)) {
			neighborDisruptions.add(blockPos);
			return;
		}
		if (event.isIn(Dropoff.NEIGHBOR_DISRUPTION)) {
			neighborDisruptions.add(blockPos);
		}
	}
}

