Feature: Filter Search and Sort the items by brand 

    Scenario: Users are able to search for an item

    Given User is on iPriceHomePage
    When User Search for item in iPriceHomePage
    Then Validate that the search results returned matches the search criteria
    
    
    
    Scenario: Users are able to filter for an item by brand under the Computing Laptop section
	
	
	Given User is on iPriceHomePage
	When User Navigate to the computing Laptop page
    And User Select brand of Laptops
	Then Validate that the results returned from first page matches the selected brand
	
	
	Scenario: Users are able to sort results under dresses by price in descending order
	
	
	Given User is on iPriceHomePage
	When User Navigate to the Clothing Dress page
    And Click on Price sorting until the indicator indicates that the list is sorted by price in descending order
	Then Validate that the results are sorted in descending order of Price