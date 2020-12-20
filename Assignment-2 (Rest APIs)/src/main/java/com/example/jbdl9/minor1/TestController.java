package com.example.jbdl9.minor1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@RestController
public class TestController {

    private RestTemplate template;

    private static final String IMAGE_FOLDER = "/tmp/images";

    TestController(){
        this.template = new RestTemplate();
    }

    // the request was rejected because its size (21934268) exceeds the configured maximum (10485760)

    // the request was rejected because its size (43868491) exceeds the configured maximum (10485760)
    @PostMapping(value = "/uploadFile")
    public void uploadFile(HttpServletRequest request) throws IOException, ServletException {

        Part part = request.getPart("file");
//        Part part2 = request.getPart("file2");

//        System.out.println("name is " + part.getName());
//        System.out.println("submitted file name is " + part.getSubmittedFileName());
//        System.out.println("size of content is " + part.getSize());
//        System.out.println("content type is " + part.getContentType());


        // 1. Directory / folder in which I need to unzip

        File outputFolder = new File("/tmp");

        ZipInputStream zipInputStream = new ZipInputStream(part.getInputStream());

        ZipEntry zipEntry = zipInputStream.getNextEntry();

        byte[]buffer = new byte[1024];
        while(zipEntry != null){
            String file_name = zipEntry.getName();
            if(!zipEntry.isDirectory()){
                File outfile = new File(outputFolder + File.separator + file_name);
                FileOutputStream fos = new FileOutputStream(outfile);
                int len = 0;
                while((len = zipInputStream.read(buffer)) > 0){
                    fos.write(buffer, 0, len);
                }
                fos.close();
            } else{
                new File(outputFolder + File.separator + file_name).mkdirs();
            }
            zipInputStream.closeEntry();
            zipEntry = zipInputStream.getNextEntry();
        }
    }

    @GetMapping(value = "/getImage/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("id") int id){

        byte[] data = this.template.getForObject("https://picsum.photos/id/" + id + "/200/300", byte[].class);

        return data;
    }

    // 1:100:10:2:20

    @GetMapping("/getMultipleImages")
    public void getMultipleImages(@RequestParam("ids") String ids, HttpServletResponse response) throws IOException {

        new File(IMAGE_FOLDER).mkdirs();
        String [] id_arr = ids.split(":");
        List<Integer> idArr = Arrays.stream(id_arr).map(id -> Integer.parseInt(id)).collect(Collectors.toList());

        for(Integer id : idArr) {
            byte[] data = this.template.getForObject("https://picsum.photos/id/" + id + "/200/300", byte[].class);

            FileOutputStream fos = new FileOutputStream(new File(IMAGE_FOLDER + "/" + id + ".png"));
            fos.write(data);
            fos.close();
        }

        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for(Integer id: idArr){
            FileSystemResource fileSystemResource = new FileSystemResource(IMAGE_FOLDER + "/" + id + ".png");
            ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());

            zipEntry.setSize(fileSystemResource.contentLength());
            zipOut.putNextEntry(zipEntry);

            StreamUtils.copy(fileSystemResource.getInputStream(), zipOut);

            zipOut.closeEntry();

        }

        zipOut.finish();
        zipOut.close();

        response.setStatus(HttpServletResponse.SC_OK);

    }
}
