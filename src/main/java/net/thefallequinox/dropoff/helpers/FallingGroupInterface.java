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

package net.thefallequinox.dropoff.helpers;

import net.thefallequinox.dropoff.logic.FallingBlockGroup;
import org.jetbrains.annotations.Nullable;


public interface FallingGroupInterface {

	void setFallingGroup(@Nullable FallingBlockGroup fallingGroup);

	@Nullable FallingBlockGroup getFallingGroup();

}
