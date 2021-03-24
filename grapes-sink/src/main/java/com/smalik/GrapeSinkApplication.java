package com.smalik;

import com.smalik.multi.MultiGrapeSink;
import com.smalik.single.SingleGrapeSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({SingleGrapeSink.class, MultiGrapeSink.class})
public class GrapeSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeSinkApplication.class, args);
	}
}
