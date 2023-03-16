package com.example.APIwiz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APIwizApplication {

	public static void main(String[] args){
		SpringApplication.run(APIwizApplication.class, args);
	}


	// to update 30 days of data
//	@Bean
//	public CommandLineRunner run(UpdateData updateData) throws Exception {
//		return args -> {
//			updateData.callAPI();
//		};
//	}

}
