package avakhidov.utils;

public final class UtilsForString {

    public static String concatWithSpaceOrEmpty(String one, String two) {
        String result;
        boolean emptyNullTwo = false;
        emptyNullTwo = two == null || two.isEmpty();

        if (one == null || one.isEmpty()) {
            if (emptyNullTwo) {
                result = "";
            } else {
                result = two;
            }
        } else {
            if (emptyNullTwo) {
                result = one;
            } else {
                result = one + " " + two;
            }
        }
        return result;
    }
}
