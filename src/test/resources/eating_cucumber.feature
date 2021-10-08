Feature:  Eating too many cucumbers may not be good for you
  Eating too much of anything may not be good for you

  Scenario: Eating a few is no problem
    Given Alice is hungry
    When she eats 3 cucumbers
    Then she will be full

  Scenario: Eating too many is problem
    Given Ivan is hungry
    When He eats 10 cucumbers
    Then he faints
