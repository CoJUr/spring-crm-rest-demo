package com.luv2code.springdemo.config;

public class Solution405 {

    /*

    If you encounter the following error message:

HTTP Status 405 ? Method Not Allowed

Type Status Report

Message Request method ‘POST’ not supported

Description The method received in the request-line is known by the origin server but not supported by the target resource.

Resolve by disabling CSRF with this: http.csrf().disable();
e.g.

public class SecurityConfig extends WebSecurityConfigurerAdapter {
  ...
  protected void configure(HttpSecurity http){...}
  ...
    http.csrf().disable();
  ...
}


     */

}
