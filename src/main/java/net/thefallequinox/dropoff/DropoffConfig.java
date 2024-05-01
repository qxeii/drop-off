package net.thefallequinox.dropoff;

public class DropoffConfig {
    private static final int MAX_BLOCK_UPDATES = 500;
    private static final float CAVE_IN_DAMAGE_SCALING = 1.0F;
    private static final float HARDNESS_SCALING = 2.0F;
    private static final float BLAST_RESISTANCE_SCALING = 1.0F;
    private static final float PROTECTED_REACH_MINIMUM = 3.0F;

    public static int getMaxBlockUpdates() {
        return MAX_BLOCK_UPDATES;
    }

    public static float getCaveInDamageScaling() {
        return  CAVE_IN_DAMAGE_SCALING;
    }

    public static float getHardnessScaling() {
        return  HARDNESS_SCALING;
    }

    public static float getBlastResistanceScaling() {
        return  BLAST_RESISTANCE_SCALING;
    }

    public static float getProtectedReachMinimum() {
        return PROTECTED_REACH_MINIMUM;
    }
}
