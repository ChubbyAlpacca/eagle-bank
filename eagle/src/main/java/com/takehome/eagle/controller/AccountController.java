package com.takehome.eagle.controller;

import com.takehome.eagle.api.AccountApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AccountController implements AccountApi {



}
