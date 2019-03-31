package nwawsoft.util;

/**
 * Used to only create command line output in programs if the DEBUG boolean in this file is set.
 */
public class DebugPrinter {
    public static final boolean DEBUG = true;

    /**
     * Prints which method called this one and prints the passed String if the DEBUG flag is set.
     *
     * @param classObject     the class calling this method. Usually "this".
     * @param printableString the String to print.
     */
    private static void debugPrint(final Object classObject, final String printableString) {
        if (DebugPrinter.DEBUG) {
            String method = classObject.getClass().getDeclaredMethods()[0].toString();
            String methodPath = "";
            String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
            if (method.contains("(")) {
                methodPath = method.substring(0, method.indexOf('('));
                if (methodPath.contains(".")) {
                    methodPath = methodPath.substring(0, methodPath.lastIndexOf('.'));
                }
            }
            if (methodName.equals("<init>")) {
                System.out.println("DebugPrinter for " + methodPath + " (constructor):");
            } else {
                System.out.println("DebugPrinter for " + methodPath + "." + methodName + ":");
            }
            System.out.println(printableString);
        }
    }

    /**
     * Prints the passed String if the DEBUG flag is set.
     *
     * @param printableString the String to print.
     */
    private static void debugPrint(final String printableString) {
        if (DebugPrinter.DEBUG) {
            System.out.println(printableString);
        }
    }

    /**
     * Wrapper for debugPrint(Class, String).
     *
     * @param classObject     the class calling this method. Usually "this"
     * @param printableString the String to print.
     */
    public static void dp(final Object classObject, final String printableString) {
        debugPrint(classObject, printableString);
    }

    /**
     * Wrapper for debugPrint(String).
     *
     * @param printableString the String to print.
     */
    public static void dp(final String printableString) {
        debugPrint(printableString);
    }

    /**
     * Prints the passed String no matter if the DEBUG flag is set or not.
     * If it is not set only the passed String will be shown.
     * If it is set the calling method's path and name will also be shown.
     *
     * @param classObject the class calling this method. Usually "this".
     * @param printableString the String to print.
     */
    public static void p (final Object classObject, final String printableString) {
        if (DebugPrinter.DEBUG) {
            String method = classObject.getClass().getDeclaredMethods()[0].toString();
            String methodPath = "";
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            if (method.contains("(")) {
                methodPath = method.substring(0, method.indexOf('('));
                if (methodPath.contains(".")) {
                    methodPath = methodPath.substring(0, methodPath.lastIndexOf('.'));
                }
            }
            if (methodName.equals("<init>")) {
                System.out.println("DebugPrinter for " + methodPath + " (constructor):");
            } else {
                System.out.println("DebugPrinter for " + methodPath + "." + methodName + ":");
            }
            System.out.println(printableString);
        } else {
            System.out.println(printableString);
        }
    }
}
