Feature: Add items to cart

  Scenario Outline: Add two items by selection and one from search bar to the cart
    Given I open Chrome browser
    When I open the Vodafone E-shop homepage to Login
    And I enter <number> and <password> to login
    And I add <item1> and <item2> to the cart by selecting from the page
    And I search and add <item3> to the cart using the search bar
    Then I verify that <item1> and <item2> and <item3> are added to the cart and remove them

    Examples: 
      | item1                | item2                            | item3                | number       | password       |
      | "Samsung Galaxy A25" | "iPhone 15"                      | "iPhone 13 128GB"    | #Your_Number | #Your_Password |
      | "JBL Charge 5"       | "Lenovo IdeaPad 5 Laptop - 11th" | "Redmi Smart Band 2" | #Your_Number | #Your_Password |
