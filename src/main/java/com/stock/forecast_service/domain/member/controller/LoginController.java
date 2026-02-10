package com.stock.forecast_service.domain.member.controller;

import com.stock.forecast_service.domain.member.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class LoginController {

  private final LoginService loginService;

}
