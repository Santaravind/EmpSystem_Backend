package emp.EMSystem.service.docservice;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import emp.EMSystem.model.Document;
import emp.EMSystem.repository.DocumentRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class S3ImageUploader {
    @Autowired
    private DocumentRepositery repositery;

    @Autowired
    private AmazonS3 client;

    @Value("${app.s3.bucket}")
    private String bucketName;




    public String uploadImage(MultipartFile image, Long empId ) throws IOException {
        if (image== null){
            throw new IOException("image is null !!");
        }
        String actualFileName=image.getOriginalFilename();
        String fileName= UUID.randomUUID().toString()+actualFileName.substring(actualFileName.lastIndexOf("."));
        ObjectMetadata metaData =new ObjectMetadata();
        metaData.setContentLength(image.getSize());
        PutObjectResult putObjectResult=client.putObject(new PutObjectRequest(bucketName, fileName,image.getInputStream(),metaData));

        //new add for database
        String fileurl=this.preSingedUrl(fileName);


        Document data=new Document(fileName,empId, new Date() );
        repositery.save(data);
        return fileurl ;
    }


    public List<String> allFiles() {

        ListObjectsV2Request listObjectReqest=new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result listObjectsV2Result=client.listObjectsV2(listObjectReqest);
        List<S3ObjectSummary> objectSummaries=  listObjectsV2Result.getObjectSummaries();
        List<String> listOfFile= objectSummaries.stream().map(item->this.preSingedUrl(item.getKey())).collect(Collectors.toList());
        return listOfFile;
    }





    public String preSingedUrl(String fileName) {

        Date expiretionDate=new Date();
        long time=expiretionDate.getTime();
        int hour=2;
        time=time+hour*60*60*1000;
        expiretionDate.setTime(time);

        GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bucketName,fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiretionDate);

        URL url= client.generatePresignedUrl(request);
        return url.toString();
    }


    public String getImagetUrlByName(String fileName) {

        S3Object object=  client.getObject(bucketName,fileName);
        String key=object.getKey();
        String url=preSingedUrl(key);

        return url;
    }


}
