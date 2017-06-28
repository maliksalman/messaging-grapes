package com.smalik;

import com.smalik.multi.MultiGrapeListener;
import com.smalik.multi.MultiGrapeSink;
import com.smalik.single.SingleGrapeHandler;
import com.smalik.single.SingleGrapeListener;
import com.smalik.single.SingleGrapeSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding({SingleGrapeSink.class, MultiGrapeSink.class})
public class AmqpSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpSinkApplication.class, args);
	}

	@Autowired
	private SingleGrapeSink sink;

	@Bean
	@ConditionalOnProperty("use.message.listener")
	public SingleGrapeListener createListener() {
		return new SingleGrapeListener();
	}

	@Bean
	@ConditionalOnMissingBean(SingleGrapeListener.class)
	public SingleGrapeHandler createHandler() {
		return new SingleGrapeHandler(sink.input());
	}

	@Bean
	public MultiGrapeListener createMultiGrapeListener() {
		return new MultiGrapeListener();
	}
}
