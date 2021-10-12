package com.example.service2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootTest
class Service2ApplicationTests {

	@Test
	void contextLoads() {
		Flux flux=Flux.fromIterable(List.of(1,2,3))
				.doOnNext(data-> System.out.println("data "+ data))
				.doOnNext(data-> System.out.println("data2 " + data*data));
		flux.subscribe();
	}

	@Test
	void contextLoads2() {
		Flux flux=Flux.fromIterable(List.of(10,20,30))
				.flatMap(data-> Flux.just(data+5))
				.log()
//				.doOnNext(data->System.out.println("data1 "+data))
				.flatMap(data-> Flux.just(data*data))
				.log();
		//.doOnNext(data->System.out.println("data2 "+data));
		flux.subscribe();
	}


	@Test
	void contextLoads3() {
		Flux flux=Flux.fromIterable(List.of(10,20,30))
				.flatMap(data->Mono.just(data*2/0))
				.log()
				.doOnNext(data->System.out.println(data))
				.doOnComplete(()-> System.out.println("completed"))
				.doOnError(err->System.out.println("Error "+ err.getMessage()));
		//.doOnNext(data->System.out.println("data2 "+data));
		flux.subscribe();
	}

}
