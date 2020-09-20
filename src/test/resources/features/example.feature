Feature: 用户信息测试

  @demo
  Scenario: 创建用户并校验信息
    When 创建一个用户
    Then 获取用户信息


  Scenario Outline: test
    When 参数 <c> 和 <d>
    Then 给一个参数 <a> 和 <b>

    Examples:
      | c | d |a | b|
      | 4444 | 0 | 1 | 1 |
      | 5555 | 0 | 1 | 1 |
      | 6666 | 0 | 1 | 1 |
      | 7777 | 0 | 1 | 1 |

    @debug
    Examples:
      | c | d |a | b|
      | 4444 | 0 | 1 | 1 |
      | 5555 | 0 | 1 | 1 |

