package nwawsoft.util;

/**
 * Created by Ernst on 18.07.2017.
 */
public class StringListFunctions extends ListFunctions {
    /**
     * Returns a StringList for test purposes.
     *
     * @return a StringList containing useless data
     */
    public static StringList getTestStringList() {
        StringList sList = new StringList();
        sList.append("Bla");
        sList.append("Blubb");
        sList.append("Blabla");
        sList.append("Blabel");
        sList.append("Blarb");
        return sList;
    }

    /**
     * Returns another StringList for test purposes.
     *
     * @return another StringList containing more useless data
     */
    public static StringList getAnotherTestStringList() {
        StringList sList = new StringList();
        sList.append("Cookoo");
        sList.append("Ceekee");
        return sList;
    }

    /**
     * Returns yet another StringList for test purposes.
     *
     * @return yet another StringList containing yet more useless data
     */
    public static StringList getYetAnotherTestStringList() {
        StringList sList = new StringList();
        sList.append("Kalinka");
        sList.append("Ceekee");
        sList.append("Trust");
        return sList;
    }
}
