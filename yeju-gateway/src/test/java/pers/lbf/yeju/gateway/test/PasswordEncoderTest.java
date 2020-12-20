/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.gateway.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/20 23:53
 */
public class PasswordEncoderTest {

  public static void main(String[] args) {
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      String encode = passwordEncoder.encode("yeju@123");

      boolean flag = passwordEncoder.matches("yeju@123","$2a$10$7.b4iiQhh5h80mzqH2Skdev9ywifqaRrP1FHopqrHo5IxTn0cfGRq");
      System.out.println(encode.length());
      System.out.println(flag);

      boolean matches = passwordEncoder.matches("yeju@123","$2a$10$yb0CL/LeZF3GTvGg7WbPe.0mqIoMDKHWaq/UmajU6aEAAFKpcU3tS");
      System.out.println(matches);
  }
}
