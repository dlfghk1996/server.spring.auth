//package server.spring.auth.oauth.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.mommaeat.server.common.enums.ResponseCode;
//import io.mommaeat.server.common.exception.MommaException;
//import io.mommaeat.server.common.service.AbstractRevisionServiceImpl;
//import io.mommaeat.server.common.util.EncodeUtil;
//import io.mommaeat.server.member.domain.NaverToken;
//import io.mommaeat.server.member.domain.QNaverToken;
//import io.mommaeat.server.member.enums.LoginType;
//import io.mommaeat.server.member.integration.naver.NaverProperties;
//import io.mommaeat.server.member.repository.NaverTokenRepository;
//import io.mommaeat.server.member.service.NaverTokenService;
//import io.mommaeat.server.member.service.UserService;
//import io.mommaeat.server.member.web.vo.NaverTokenData;
//import io.mommaeat.server.member.web.vo.NaverTokenData.NaverTokenRequest;
//import java.time.Duration;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//import javax.annotation.Resource;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Slf4j
//@RequiredArgsConstructor
//@Transactional
//@Service
//public class NaverTokenServiceImpl
//    extends AbstractRevisionServiceImpl<
//        NaverToken, Long, NaverTokenRepository, Long, NaverTokenRequest, NaverTokenData>
//    implements NaverTokenService {
//
//  private static final int REQUEST_TIMEOUT_SECONDS = 10;
//  private final NaverProperties naverProperties;
//  private final UserService userService;
//
//  static QNaverToken naverToken = QNaverToken.naverToken;
//
//  @Resource(name = "naverAuthClient")
//  private WebClient naverAuthClient;
//
//  @Override
//  public NaverTokenData disableData(NaverTokenData.NaverTokenRequest request) {
//    NaverToken temp = find(request.getId());
//    if (Objects.nonNull(temp)) {
//      if (temp.isDisabled()) {
//        log.debug("Enable naverToken : " + request);
//        temp = enable(temp);
//      } else {
//        log.debug("Disable naverToken : " + request);
//        temp = disable(temp);
//      }
//      return mapper.map(temp, NaverTokenData.class);
//    } else {
//      throw new MommaException(ResponseCode.RESULT_NOT_FOUND);
//    }
//  }
//
//  @Override
//  public NaverToken updateEntity(NaverToken naverToken) {
//    Optional<NaverToken> optionalNaverToken = repository.findByNaver(naverToken.getNaver());
//
//    if (optionalNaverToken.isPresent()) {
//      NaverToken oldNaverToken = optionalNaverToken.get();
//
//      BeanUtils.copyProperties(naverToken, oldNaverToken, "id");
//      naverToken = super.update(oldNaverToken);
//    } else {
//      naverToken = super.update(naverToken);
//    }
//    return naverToken;
//  }
//
//  @Override
//  public NaverToken reissueNaverAccessToken(NaverToken naverToken) {
//
//    Object response =
//        naverAuthClient
//            .get()
//            .uri(
//                uriBuilder ->
//                    uriBuilder
//                        .path("/token")
//                        .queryParam("client_id", naverProperties.getClientId())
//                        .queryParam("client_secret", naverProperties.getClientSecret())
//                        .queryParam("refresh_token", naverToken.getRefreshToken())
//                        .queryParam("grant_type", "refresh_token")
//                        .build())
//            .retrieve()
//            .onStatus(
//                status -> status.is4xxClientError() || status.is5xxServerError(),
//                clientResponse ->
//                    clientResponse
//                        .bodyToMono(Object.class)
//                        .map(body -> new MommaException(ResponseCode.ERROR_INTEGRATION)))
//            .bodyToMono(Object.class)
//            .log()
//            .block(Duration.ofSeconds(REQUEST_TIMEOUT_SECONDS));
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    Map<String, String> responseMap = objectMapper.convertValue(response, Map.class);
//
//    if (responseMap.get("error") != null) {
//      return null;
//    }
//
//    String accessToken = responseMap.get("access_token");
//    String expiresIn = responseMap.get("expires_in");
//
//    naverToken.setAccessToken(accessToken);
//
//    return super.update(naverToken);
//  }
//
//  @Override
//  public void unlinkNaver(NaverToken naverToken) {
//
//    Object response =
//        naverAuthClient
//            .get()
//            .uri(
//                uriBuilder ->
//                    uriBuilder
//                        .path("/token")
//                        .queryParam("grant_type", "delete")
//                        .queryParam("client_id", naverProperties.getClientId())
//                        .queryParam("client_secret", naverProperties.getClientSecret())
//                        .queryParam(
//                            "access_token",
//                            EncodeUtil.onlySpecialCharsEncode(naverToken.getAccessToken()))
//                        .queryParam("service_provider", LoginType.NAVER.getCode())
//                        .build())
//            .retrieve()
//            .onStatus(
//                status -> status.is4xxClientError() || status.is5xxServerError(),
//                clientResponse ->
//                    clientResponse
//                        .bodyToMono(Object.class)
//                        .map(body -> new MommaException(ResponseCode.ERROR_INTEGRATION)))
//            .bodyToMono(Object.class)
//            .log()
//            .block(Duration.ofSeconds(REQUEST_TIMEOUT_SECONDS));
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    Map<String, String> responseMap = objectMapper.convertValue(response, Map.class);
//
//    String result = responseMap.get("result");
//
//    // 응답이 success가 아니면 에러처리
//    if (!"success".equals(responseMap.get("result"))) {
//      throw new MommaException(ResponseCode.ERROR_INTEGRATION);
//    }
//  }
//}
