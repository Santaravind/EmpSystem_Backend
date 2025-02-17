package emp.EMSystem.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3config {
    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private  String regin;


    @Bean
    public AmazonS3 client(){

//        ClientConfiguration clientConfig = new ClientConfiguration()
//                .withMaxConnections(100) // Increase connection pool size
//                .withConnectionTimeout(5000) // Set connection timeout
//                .withSocketTimeout(5000) // Set socket timeout
//                .withMaxErrorRetry(3); // Retry up to 3 times on

        AWSCredentials credentials=new BasicAWSCredentials(awsAccessKey,awsSecretKey);
        AmazonS3 amazonS3= AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(regin)
                .build();
        return  amazonS3;
    }
}
