//package server.spring.auth.oauth.naver;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.util.DefaultUriBuilderFactory;
//import server.spring.auth.oauth.OauthProperties;
//
//@RequiredArgsConstructor
//@Configuration
//public class NaverWebClientConfig {
//
//  private final OauthProperties naverProperties;
//  private final WebClientLoggingFilter webClientLoggingFilter;
//
//  /**
//   * 카카오 AUTH client
//   *
//   * @return WebClient
//   */
//  @Bean("naverAuthClient")
//  public WebClient naverAuthClient() {
//    return WebClient.builder()
//        .baseUrl(naverProperties.getAuthHost())
//        .filter(webClientLoggingFilter.logRequest())
//        .filter(webClientLoggingFilter.logResponse())
//        .uriBuilderFactory(uriBuilderConfig(naverProperties.getAuthHost()))
//        .build();
//  }
//
//  /**
//   * 카카오 API client
//   *
//   * @return WebClient
//   */
//  @Bean("naverApiClient")
//  public WebClient naverApiClient() {
//    return WebClient.builder()
//        .baseUrl(naverProperties.getApiHost())
//        .filter(webClientLoggingFilter.logRequest())
//        .filter(webClientLoggingFilter.logResponse())
//        .uriBuilderFactory(uriBuilderConfig(naverProperties.getApiHost()))
//        .build();
//  }
//
//  // EncodingMode.NONE : webclient가 나갈 때 encoding 하지 않음.
//  private DefaultUriBuilderFactory uriBuilderConfig(String baseUrl) {
//    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
//    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
//    return factory;
//  }
//}
