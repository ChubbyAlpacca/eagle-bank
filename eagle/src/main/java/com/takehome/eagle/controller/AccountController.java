package com.takehome.eagle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.api.AccountApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AccountController extends AccountApi {



}
