

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ApplicationUtils {
	
	private static final String LINE_BREAK = "\\r?\\n";
	
    public static File getDataSource(String fileName) {
		String dir = System.getProperty("user.dir");
        File file = new File(dir + "/input/" + fileName);

        if (!file.exists()) {
            File filePath = new File(fileName);
            if (!filePath.exists()) {
                File filePath2 = new File(ApplicationUtils.class.getClassLoader().getResource(fileName).getFile());
                if (!filePath2.exists()) {
                    throw new RuntimeException("File Not Found...");
                } else {
                    return filePath2;
                }
            } else {
                return filePath;
            }
        } else {
            return file;
        }
    }

    public static String parseFile(File file) throws IOException {
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    public static List<String> parseFromContent(String content) {
        if (content == null || content.trim().length() == 0) {
            return Collections.emptyList();
        } else {
            String[] lines = content.split(LINE_BREAK);
            return Arrays.asList(lines);
        }
    }
}
