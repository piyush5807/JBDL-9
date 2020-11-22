import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandling {

    public static void main(String[] args) throws Exception {

//        writeFile();

//        readFromUrlAndWriteToLocalFile();

        zipFiles();

    }



    public static void zipFiles() throws IOException{



        List<String> filesToZip = Arrays.asList("/Users/piyushaggarwal/Downloads/comp.txt",
                "/Users/piyushaggarwal/Downloads/comp2.txt", "/Users/piyushaggarwal/Downloads/result-new.png");

        FileOutputStream fos = new FileOutputStream("/Users/piyushaggarwal/Downloads/compress.zip");

        ZipOutputStream zipOutputStream = new ZipOutputStream(fos);

        for(String fileToZip : filesToZip){
            File file = new File(fileToZip);
            FileInputStream fis = new FileInputStream(file);

            System.out.println("absolute path " + file.getAbsolutePath() + " canonical path " + file.getCanonicalPath()
                    + ", file name " + file.getName());

            ZipEntry zipEntry = new ZipEntry(file.getName());

            zipOutputStream.putNextEntry(zipEntry);

            int temp;
            while((temp = fis.read()) != -1){
                zipOutputStream.write((byte) temp);
            }

            fis.close();
        }

        zipOutputStream.close();
        fos.close();

    }

    public static void writeFile() throws Exception{

        try {
            FileInputStream file = new FileInputStream("/Users/piyushaggarwal/Downloads/comp.txt");

            FileOutputStream fos = new FileOutputStream("/Users/piyushaggarwal/Downloads/comp2.txt");

            int temp;
            String ans = "";
            while ((temp = file.read()) != -1) {
                fos.write((byte) temp);

            }

        }catch (Exception e){
            System.out.println("Some exception occured");
            e.printStackTrace();
            throw new Exception();
        }
    }

    public static void readFromUrlAndWriteToLocalFile() throws IOException {

        URL url = new URL("https://picsum.photos/200/300");

        InputStream inputStream = new BufferedInputStream(url.openStream());

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/piyushaggarwal/Downloads/result-new.png");

        int temp;
        while((temp = inputStream.read()) != -1){
            fileOutputStream.write((byte) temp);
        }
    }

    public static void readFromFile() throws IOException{
        FileInputStream file = new FileInputStream("/Users/piyushaggarwal/Downloads/comp.txt");

        int temp ;
        String ans = "";
        while((temp = file.read()) != -1){

            ans += (char) temp;

        }
        System.out.println(ans);
    }

}
