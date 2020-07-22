package learn.mt.hk.extreme.util;

public enum Memory {
    BYTES {
        public double toBytes(double d) {
            return d;
        }

        public double toKiloBytes(double d) {
            return toBytes(d) / KIBI;
        }

        public double toMegaBytes(double d) {
            return toKiloBytes(d) / KIBI;
        }

        public double toGigaBytes(double d) {
            return toMegaBytes(d) / KIBI;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / KIBI;
        }
    },
    KILOBYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * KIBI;
        }

        public double toKiloBytes(double d) {
            return d;
        }

        public double toMegaBytes(double d) {
            return toKiloBytes(d) / KIBI;
        }

        public double toGigaBytes(double d) {
            return toMegaBytes(d) / KIBI;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / KIBI;
        }
    },
    MEGABYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * KIBI;
        }

        public double toKiloBytes(double d) {
            return toMegaBytes(d) * KIBI;
        }

        public double toMegaBytes(double d) {
            return d;
        }

        public double toGigaBytes(double d) {
            return toMegaBytes(d) / KIBI;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / KIBI;
        }
    },
    GIGABYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * KIBI;
        }

        public double toKiloBytes(double d) {
            return toMegaBytes(d) * KIBI;
        }

        public double toMegaBytes(double d) {
            return toGigaBytes(d) * KIBI;
        }

        public double toGigaBytes(double d) {
            return d;
        }

        public double toTeraBytes(double d) {
            return toGigaBytes(d) / KIBI;
        }
    },
    TERABYTES {
        public double toBytes(double d) {
            return toKiloBytes(d) * KIBI;
        }

        public double toKiloBytes(double d) {
            return toMegaBytes(d) * KIBI;
        }

        public double toMegaBytes(double d) {
            return toGigaBytes(d) * KIBI;
        }

        public double toGigaBytes(double d) {
            return toTeraBytes(d) * KIBI;
        }

        public double toTeraBytes(double d) {
            return d;
        }
    };

    public static final int KIBI = 1024;

    public abstract double toBytes(double d);

    public abstract double toKiloBytes(double d);

    public abstract double toMegaBytes(double d);

    public abstract double toGigaBytes(double d);

    public abstract double toTeraBytes(double d);

    public static String format(double d, Memory unit, int decimals) {
        String unitStr;
        double val;
        double bytes = unit.toBytes(d);
        if (bytes < KIBI) {
            val = bytes;
            unitStr = "B";
        } else if (bytes < KIBI * KIBI) {
            val = BYTES.toKiloBytes(bytes);
            unitStr = "KB";
        } else if (bytes < KIBI * KIBI * KIBI) {
            val = BYTES.toMegaBytes(bytes);
            unitStr = "MB";
        } else if (bytes < KIBI * KIBI * KIBI * 1024L) {
            val = BYTES.toGigaBytes(bytes);
            unitStr = "GB";
        } else {
            val = BYTES.toTeraBytes(bytes);
            unitStr = "TB";
        }
        return String.format("%." + decimals + "f%s", val, unitStr);
    }
}