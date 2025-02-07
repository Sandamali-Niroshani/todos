package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

public class TestDataReader {

    static Properties pro;
    static FileInputStream propertyFile;

    /**
     * Get the suitable test data file by providing the file name along with its location
     *
     * @param fileNameWithPath Provide the file name with exact location which holds the test data for required test script
     */
    public static void getTestDataFile(String fileNameWithPath) {

        try {
            pro = new Properties();
            propertyFile = new FileInputStream(fileNameWithPath);
        } catch (FileNotFoundException e) {
            System.out.println("Exception is " + e.getMessage());
        }

    }

    public static String getTestData(String testDataKey) {

        String data = null;
        try {
            pro.load(propertyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (pro.containsKey(testDataKey)) {
            data = pro.getProperty(testDataKey);
        } else {
            throw new InvalidParameterException(MessageFormat.format("Missing value for key >>>>! : ", data));
        }

        return data;
    }

}
