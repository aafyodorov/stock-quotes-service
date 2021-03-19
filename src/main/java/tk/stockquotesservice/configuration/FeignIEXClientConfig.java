package tk.stockquotesservice.configuration;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.stockquotesservice.exception.GenericFeignException;
import tk.stockquotesservice.externalServicesClients.IEXClient;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Andrey Fyodorov
 * Created on 18.03.2021
 */

//@Configuration
//public class FeignIEXClientConfig {
//
//  @Bean("feignClientErrorDecoder")
//  ErrorDecoder decoder() {
//    return (s, response) -> {
//	};
//  }
//}
