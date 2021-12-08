package com.reallyinvincible.veto;

import java.io.PrintWriter;
import java.security.InvalidKeyException;

import Twofish.Twofish_Properties;

final class Twofish_Algorithm {
    static final String NAME = "Twofish_Algorithm";
    static final boolean IN = true;
    static final boolean OUT = false;
    static final boolean DEBUG = false;
    static final int debuglevel = 0;
    static final PrintWriter err = null;
    static final boolean TRACE = false;
    static final int BLOCK_SIZE = 16;
    private static final int ROUNDS = 16;
    private static final int MAX_ROUNDS = 16;
    private static final int INPUT_WHITEN = 0;
    private static final int OUTPUT_WHITEN = 4;
    private static final int ROUND_SUBKEYS = 8;
    private static final int TOTAL_SUBKEYS = 40;
    private static final int SK_STEP = 33686018;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final byte[][] P = new byte[][]{{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, -128, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 37, 11, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, 127, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, 15, 0, 111, -99, 54, 66, 74, 94, -63, -32}, {117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, -68, -99, 109, -63, -79, 14, -128, 93, -46, -43, -96, -124, 7, 20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, -114, 120, 92, 88, 25, -115, -27, -104, 87, 103, 127, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int[][] MDS = new int[4][256];
    private static final int RS_GF_FDBK = 333;
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    //static void debug(String var0) {
       // null.println(">>> Twofish_Algorithm: " + var0);
  //  }

//    static void trace(boolean var0, String var1) {
//        if (TRACE) {
//            null.println((var0 ? "==> " : "<== ") + "Twofish_Algorithm" + "." + var1);
//        }
//
//    }

//    static void trace(String var0) {
//        if (TRACE) {
//            null.println("<=> Twofish_Algorithm." + var0);
//        }
//
//    }

    private static final int LFSR1(int var0) {
        return var0 >> 1 ^ ((var0 & 1) != 0 ? 180 : 0);
    }

    private static final int LFSR2(int var0) {
        return var0 >> 2 ^ ((var0 & 2) != 0 ? 180 : 0) ^ ((var0 & 1) != 0 ? 90 : 0);
    }

    private static final int Mx_1(int var0) {
        return var0;
    }

    private static final int Mx_X(int var0) {
        return var0 ^ var0 >> 2 ^ ((var0 & 2) != 0 ? 180 : 0) ^ ((var0 & 1) != 0 ? 90 : 0);
    }

    private static final int Mx_Y(int var0) {
        return var0 ^ var0 >> 1 ^ ((var0 & 1) != 0 ? 180 : 0) ^ var0 >> 2 ^ ((var0 & 2) != 0 ? 180 : 0) ^ ((var0 & 1) != 0 ? 90 : 0);
    }

    private static final int b0(int var0) {
        return var0 & 255;
    }

    private static final int b1(int var0) {
        return var0 >>> 8 & 255;
    }

    private static final int b2(int var0) {
        return var0 >>> 16 & 255;
    }

    private static final int b3(int var0) {
        return var0 >>> 24 & 255;
    }

    public static synchronized Object makeKey(byte[] var0) throws InvalidKeyException {
        if (var0 == null) {
            throw new InvalidKeyException("Empty key");
        } else {
            int var1 = var0.length;
            if (var1 != 8 && var1 != 16 && var1 != 24 && var1 != 32) {
                throw new InvalidKeyException("Incorrect key length");
            } else {
                int var2 = var1 / 8;
                int[] var3 = new int[4];
                int[] var4 = new int[4];
                int[] var5 = new int[4];
                int var8 = 0;
                int var6 = 0;

                for(int var7 = var2 - 1; var6 < 4 && var8 < var1; --var7) {
                    var3[var6] = var0[var8++] & 255 | (var0[var8++] & 255) << 8 | (var0[var8++] & 255) << 16 | (var0[var8++] & 255) << 24;
                    var4[var6] = var0[var8++] & 255 | (var0[var8++] & 255) << 8 | (var0[var8++] & 255) << 16 | (var0[var8++] & 255) << 24;
                    var5[var7] = RS_MDS_Encode(var3[var6], var4[var6]);
                    ++var6;
                }

                int[] var12 = new int[40];
                int var9 = 0;

                for(var6 = 0; var6 < 20; var9 += 33686018) {
                    int var10 = F32(var2, var9, var3);
                    int var11 = F32(var2, var9 + 16843009, var4);
                    var11 = var11 << 8 | var11 >>> 24;
                    var10 += var11;
                    var12[2 * var6] = var10;
                    var10 += var11;
                    var12[2 * var6 + 1] = var10 << 9 | var10 >>> 23;
                    ++var6;
                }

                int var13 = var5[0];
                int var14 = var5[1];
                int var15 = var5[2];
                int var16 = var5[3];
                int[] var21 = new int[1024];

                for(var6 = 0; var6 < 256; ++var6) {
                    int var20 = var6;
                    int var19 = var6;
                    int var18 = var6;
                    int var17 = var6;
                    switch(var2 & 3) {
                        case 0:
                            var17 = P[1][var6] & 255 ^ var16 & 255;
                            var18 = P[0][var6] & 255 ^ var16 >>> 8 & 255;
                            var19 = P[0][var6] & 255 ^ var16 >>> 16 & 255;
                            var20 = P[1][var6] & 255 ^ var16 >>> 24 & 255;
                        case 3:
                            var17 = P[1][var17] & 255 ^ var15 & 255;
                            var18 = P[1][var18] & 255 ^ var15 >>> 8 & 255;
                            var19 = P[0][var19] & 255 ^ var15 >>> 16 & 255;
                            var20 = P[0][var20] & 255 ^ var15 >>> 24 & 255;
                        case 2:
                            var21[2 * var6] = MDS[0][P[0][P[0][var17] & 255 ^ var14 & 255] & 255 ^ var13 & 255];
                            var21[2 * var6 + 1] = MDS[1][P[0][P[1][var18] & 255 ^ var14 >>> 8 & 255] & 255 ^ var13 >>> 8 & 255];
                            var21[512 + 2 * var6] = MDS[2][P[1][P[0][var19] & 255 ^ var14 >>> 16 & 255] & 255 ^ var13 >>> 16 & 255];
                            var21[512 + 2 * var6 + 1] = MDS[3][P[1][P[1][var20] & 255 ^ var14 >>> 24 & 255] & 255 ^ var13 >>> 24 & 255];
                            break;
                        case 1:
                            var21[2 * var6] = MDS[0][P[0][var6] & 255 ^ var13 & 255];
                            var21[2 * var6 + 1] = MDS[1][P[0][var6] & 255 ^ var13 >>> 8 & 255];
                            var21[512 + 2 * var6] = MDS[2][P[1][var6] & 255 ^ var13 >>> 16 & 255];
                            var21[512 + 2 * var6 + 1] = MDS[3][P[1][var6] & 255 ^ var13 >>> 24 & 255];
                    }
                }

                Object[] var22 = new Object[]{var21, var12};
                return var22;
            }
        }
    }

    public static byte[] blockEncrypt(byte[] var0, int var1, Object var2) {
        Object[] var3 = (Object[])var2;
        int[] var4 = (int[])var3[0];
        int[] var5 = (int[])var3[1];
        int var6 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        int var7 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        int var8 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        int var9 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        var6 ^= var5[0];
        var7 ^= var5[1];
        var8 ^= var5[2];
        var9 ^= var5[3];
        int var12 = 8;

        for(int var13 = 0; var13 < 16; var13 += 2) {
            int var10 = Fe32(var4, var6, 0);
            int var11 = Fe32(var4, var7, 3);
            var8 ^= var10 + var11 + var5[var12++];
            var8 = var8 >>> 1 | var8 << 31;
            var9 = var9 << 1 | var9 >>> 31;
            var9 ^= var10 + 2 * var11 + var5[var12++];
            var10 = Fe32(var4, var8, 0);
            var11 = Fe32(var4, var9, 3);
            var6 ^= var10 + var11 + var5[var12++];
            var6 = var6 >>> 1 | var6 << 31;
            var7 = var7 << 1 | var7 >>> 31;
            var7 ^= var10 + 2 * var11 + var5[var12++];
        }

        var8 ^= var5[4];
        var9 ^= var5[5];
        var6 ^= var5[6];
        var7 ^= var5[7];
        byte[] var14 = new byte[]{(byte)var8, (byte)(var8 >>> 8), (byte)(var8 >>> 16), (byte)(var8 >>> 24), (byte)var9, (byte)(var9 >>> 8), (byte)(var9 >>> 16), (byte)(var9 >>> 24), (byte)var6, (byte)(var6 >>> 8), (byte)(var6 >>> 16), (byte)(var6 >>> 24), (byte)var7, (byte)(var7 >>> 8), (byte)(var7 >>> 16), (byte)(var7 >>> 24)};
        return var14;
    }

    public static byte[] blockDecrypt(byte[] var0, int var1, Object var2) {
        Object[] var3 = (Object[])var2;
        int[] var4 = (int[])var3[0];
        int[] var5 = (int[])var3[1];
        int var6 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        int var7 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        int var8 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        int var9 = var0[var1++] & 255 | (var0[var1++] & 255) << 8 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 24;
        var6 ^= var5[4];
        var7 ^= var5[5];
        var8 ^= var5[6];
        var9 ^= var5[7];
        int var10 = 39;

        for(int var13 = 0; var13 < 16; var13 += 2) {
            int var11 = Fe32(var4, var6, 0);
            int var12 = Fe32(var4, var7, 3);
            var9 ^= var11 + 2 * var12 + var5[var10--];
            var9 = var9 >>> 1 | var9 << 31;
            var8 = var8 << 1 | var8 >>> 31;
            var8 ^= var11 + var12 + var5[var10--];
            var11 = Fe32(var4, var8, 0);
            var12 = Fe32(var4, var9, 3);
            var7 ^= var11 + 2 * var12 + var5[var10--];
            var7 = var7 >>> 1 | var7 << 31;
            var6 = var6 << 1 | var6 >>> 31;
            var6 ^= var11 + var12 + var5[var10--];
        }

        var8 ^= var5[0];
        var9 ^= var5[1];
        var6 ^= var5[2];
        var7 ^= var5[3];
        byte[] var14 = new byte[]{(byte)var8, (byte)(var8 >>> 8), (byte)(var8 >>> 16), (byte)(var8 >>> 24), (byte)var9, (byte)(var9 >>> 8), (byte)(var9 >>> 16), (byte)(var9 >>> 24), (byte)var6, (byte)(var6 >>> 8), (byte)(var6 >>> 16), (byte)(var6 >>> 24), (byte)var7, (byte)(var7 >>> 8), (byte)(var7 >>> 16), (byte)(var7 >>> 24)};
        return var14;
    }

    public static boolean self_test() {
        return self_test(16);
    }

    private static final int RS_MDS_Encode(int var0, int var1) {
        int var2 = var1;

        for(int var3 = 0; var3 < 4; ++var3) {
            var2 = RS_rem(var2);
        }

        var2 ^= var0;

        for(int var4 = 0; var4 < 4; ++var4) {
            var2 = RS_rem(var2);
        }

        return var2;
    }

    private static final int RS_rem(int var0) {
        int var1 = var0 >>> 24 & 255;
        int var2 = (var1 << 1 ^ ((var1 & 128) != 0 ? 333 : 0)) & 255;
        int var3 = var1 >>> 1 ^ ((var1 & 1) != 0 ? 166 : 0) ^ var2;
        int var4 = var0 << 8 ^ var3 << 24 ^ var2 << 16 ^ var3 << 8 ^ var1;
        return var4;
    }

    private static final int F32(int var0, int var1, int[] var2) {
        int var3 = var1 & 255;
        int var4 = var1 >>> 8 & 255;
        int var5 = var1 >>> 16 & 255;
        int var6 = var1 >>> 24 & 255;
        int var7 = var2[0];
        int var8 = var2[1];
        int var9 = var2[2];
        int var10 = var2[3];
        int var11 = 0;
        switch(var0 & 3) {
            case 0:
                var3 = P[1][var3] & 255 ^ var10 & 255;
                var4 = P[0][var4] & 255 ^ var10 >>> 8 & 255;
                var5 = P[0][var5] & 255 ^ var10 >>> 16 & 255;
                var6 = P[1][var6] & 255 ^ var10 >>> 24 & 255;
            case 3:
                var3 = P[1][var3] & 255 ^ var9 & 255;
                var4 = P[1][var4] & 255 ^ var9 >>> 8 & 255;
                var5 = P[0][var5] & 255 ^ var9 >>> 16 & 255;
                var6 = P[0][var6] & 255 ^ var9 >>> 24 & 255;
            case 2:
                var11 = MDS[0][P[0][P[0][var3] & 255 ^ var8 & 255] & 255 ^ var7 & 255] ^ MDS[1][P[0][P[1][var4] & 255 ^ var8 >>> 8 & 255] & 255 ^ var7 >>> 8 & 255] ^ MDS[2][P[1][P[0][var5] & 255 ^ var8 >>> 16 & 255] & 255 ^ var7 >>> 16 & 255] ^ MDS[3][P[1][P[1][var6] & 255 ^ var8 >>> 24 & 255] & 255 ^ var7 >>> 24 & 255];
                break;
            case 1:
                var11 = MDS[0][P[0][var3] & 255 ^ var7 & 255] ^ MDS[1][P[0][var4] & 255 ^ var7 >>> 8 & 255] ^ MDS[2][P[1][var5] & 255 ^ var7 >>> 16 & 255] ^ MDS[3][P[1][var6] & 255 ^ var7 >>> 24 & 255];
        }

        return var11;
    }

    private static final int Fe32(int[] var0, int var1, int var2) {
        return var0[2 * _b(var1, var2)] ^ var0[2 * _b(var1, var2 + 1) + 1] ^ var0[512 + 2 * _b(var1, var2 + 2)] ^ var0[512 + 2 * _b(var1, var2 + 3) + 1];
    }

    private static final int _b(int var0, int var1) {
        int var2 = 0;
        switch(var1 % 4) {
            case 0:
                var2 = var0 & 255;
                break;
            case 1:
                var2 = var0 >>> 8 & 255;
                break;
            case 2:
                var2 = var0 >>> 16 & 255;
                break;
            case 3:
                var2 = var0 >>> 24 & 255;
        }

        return var2;
    }

    public static int blockSize() {
        return 16;
    }

    private static boolean self_test(int var0) {
        boolean var1 = false;

        try {
            byte[] var2 = new byte[]{1, 35, 69, 103, -119, -85, -51, -17, -2, -36, -70, -104, 118, 84, 50, 16, 0, 17, 34, 51, 68, 85, 102, 119};
            byte[] var3 = new byte[16];
            Object var4 = makeKey(var2);
            byte[] var5 = blockEncrypt(var3, 0, var4);
            byte[] var6 = blockDecrypt(var5, 0, var4);
            var1 = areEqual(var3, var6);
            if (!var1) {
                throw new RuntimeException("Symmetric operation failed");
            }
        } catch (Exception var7) {
        }

        return var1;
    }

    private static boolean areEqual(byte[] var0, byte[] var1) {
        int var2 = var0.length;
        if (var2 != var1.length) {
            return false;
        } else {
            for(int var3 = 0; var3 < var2; ++var3) {
                if (var0[var3] != var1[var3]) {
                    return false;
                }
            }

            return true;
        }
    }

    private static String intToString(int var0) {
        char[] var1 = new char[8];

        for(int var2 = 7; var2 >= 0; --var2) {
            var1[var2] = HEX_DIGITS[var0 & 15];
            var0 >>>= 4;
        }

        return new String(var1);
    }

    private static String toString(byte[] var0) {
        return toString(var0, 0, var0.length);
    }

    private static String toString(byte[] var0, int var1, int var2) {
        char[] var3 = new char[var2 * 2];
        int var4 = var1;

        byte var6;
        for(int var5 = 0; var4 < var1 + var2; var3[var5++] = HEX_DIGITS[var6 & 15]) {
            var6 = var0[var4++];
            var3[var5++] = HEX_DIGITS[var6 >>> 4 & 15];
        }

        return new String(var3);
    }

    public static void main(String[] var0) {
        self_test(24);
    }

    public Twofish_Algorithm() {
    }

    static {
        long var0 = System.currentTimeMillis();
        int[] var2 = new int[2];
        int[] var3 = new int[2];
        int[] var4 = new int[2];

        for(int var5 = 0; var5 < 256; ++var5) {
            int var6 = P[0][var5] & 255;
            var2[0] = var6;
            var3[0] = Mx_X(var6) & 255;
            var4[0] = Mx_Y(var6) & 255;
            var6 = P[1][var5] & 255;
            var2[1] = var6;
            var3[1] = Mx_X(var6) & 255;
            var4[1] = Mx_Y(var6) & 255;
            MDS[0][var5] = var2[1] | var3[1] << 8 | var4[1] << 16 | var4[1] << 24;
            MDS[1][var5] = var4[0] | var4[0] << 8 | var3[0] << 16 | var2[0] << 24;
            MDS[2][var5] = var3[1] | var4[1] << 8 | var2[1] << 16 | var4[1] << 24;
            MDS[3][var5] = var3[0] | var2[0] << 8 | var4[0] << 16 | var3[0] << 24;
        }

        var0 = System.currentTimeMillis() - var0;
    }
}