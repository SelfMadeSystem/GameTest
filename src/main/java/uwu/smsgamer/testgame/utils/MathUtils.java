package uwu.smsgamer.testgame.utils;

public class MathUtils {
    private static final float[] SIN_TABLE = new float[65536];
    public static final double PI = Math.PI;

    static {
        for (int i = 0; i < 65536; ++i) {
            SIN_TABLE[i] = (float) Math.sin(i * Math.PI * 2.0D / 65536.0D);
        }
    }

    /**
     * sin looked up in a table
     */
    public static float sin(float v) {
        return SIN_TABLE[(int) (Math.toRadians(v) * 10430.378F) & 65535];
    }

    /**
     * cos looked up in the sin table with the appropriate offset
     */
    public static float cos(float v) {
        return SIN_TABLE[(int) (Math.toRadians(v) * 10430.378F + 16384.0F) & 65535];
    }

    /**
     * sin looked up in a table
     */
    public static float sinr(float v) {
        return SIN_TABLE[(int) (v * 10430.378F) & 65535];
    }

    /**
     * cos looked up in the sin table with the appropriate offset
     */
    public static float cosr(float v) {
        return SIN_TABLE[(int) (v * 10430.378F + 16384.0F) & 65535];
    }

    public static float wrapAngle180(double f) {
        return wrapAngle180((float) f);
    }

    public static float wrapAngle180(float f) {
        f %= 360.0F;
        if (f >= 180.0F) {
            f -= 360.0F;
        }
        if (f < -180.0F) {
            f += 360.0F;
        }
        return f;
    }

    public static float getAngleDifference(float a, float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }
}
