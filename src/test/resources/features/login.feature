Feature: 用户登录测试

  Scenario Outline: 登录-用户名或密码错误
    When 使用错误用户名 <UserName> 和密码 <Password> 来登录
    Then 不正确的用户名或密码登录失败

    Examples:
      | UserName | Password |
      | qatest1  | 123456   |
      | qatest2  | 123456   |
      | qatest3  | 654321   |

    @debug
    Examples:
      | UserName | Password |
      | qatest1  | 123456   |