import com.fresco.file_operations.Solution;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class RegexReadFile {


    public static void readAndExtractFile() throws IOException {
        Pattern p = Pattern.compile("(?<= ExternalId=\\\").+?(?=\\\")");
        Pattern p1 = Pattern.compile(" ExternalId");
        Pattern p2 = Pattern.compile("(?<= ExternalId).+?(?=\\=)");

        Files.readAllLines(Paths.get("C:\\Users\\593826\\Downloads\\PMQAGW_Product_CAFR.xml"))
                .stream().
                forEach(s ->
                        {
                            int count = 0;
                            Matcher m1 = p.matcher(s);
                            while (m1.find()) {
                                System.out.println(m1.group());
                                count++;
                            }
                            //System.out.println("end of line: "+count);
                        }
                );
    }

    public static void main(String[] args) {
        try {
            readAndExtractFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}