package com.rest.api.controller.common;

import com.google.gson.Gson;

import com.rest.api.service.social.KakaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"3. Kakao"})
@RequiredArgsConstructor
@Controller
@RequestMapping("/social/login")
public class SocialController {
    private final Environment env;
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final KakaoService kaKaoService;

    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    /*
    * 카카오 로그인 페이지
    *
    * */
    @ApiOperation(value = "카카오 로그인", notes = "카카오 회원 로그인을 한다.")
    @GetMapping
    public ModelAndView socialLogin(@ApiParam(value = "ModelAndView", required = true) ModelAndView mav) {
        StringBuilder loginUrl = new StringBuilder()
                .append(env.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

        mav.addObject("loginUrl", loginUrl);
        System.out.println("Login URL : " + loginUrl);
        mav.setViewName("social/login");
        return mav;
    }

    /**
     * 카카오 인증 완료 후 리다이렉트 화면
     */
    @GetMapping(value = "/kakao")
    public ModelAndView redirectKakao(ModelAndView mav, @RequestParam String code) {
        mav.addObject("authInfo", kaKaoService.getKakaoTokenInfo(code));
        mav.setViewName("social/redirectKakao");
        return mav;
    }

}
