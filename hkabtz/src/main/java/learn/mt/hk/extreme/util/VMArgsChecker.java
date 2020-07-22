package learn.mt.hk.extreme.util;

import java.lang.management.*;
import java.util.*;

public class VMArgsChecker {
    public static Optional<String> findVMArg(String arg) {
        return ManagementFactory.getRuntimeMXBean()
                .getInputArguments().stream()
                .filter(vmArg -> vmArg.startsWith(arg))
                .findFirst();
    }
}
