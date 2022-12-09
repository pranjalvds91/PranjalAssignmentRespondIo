Feature: Add Book  
Scenario: Users are able to add booking of hotels with all details 
	
	Given User enter the URL of adding book
	When User enter the details of book
    Then User validate the status of book added
    
    
  Scenario: Users are able to add booking of hotels Without Addtional needs
	
	Given User enter the URL of adding book
	When User enter the details of book Without Addtional needs
    Then User validate the status of book added Without Addtional needs
	
	
	 Scenario: Users are able to add booking of hotels With Old Dates
	
	Given User enter the URL of adding book
	When User enter the details of book With Old Dates
    Then User validate the status of book added With Old Dates
    # Comment : this must be failed because user cannot book hotels for old dates
	
	  Scenario: Users are able to add booking of hotels with Blank Checkout Date and Checkin Date
	
	Given User enter the URL of adding book
	When User enter the details of book with Blank Checkout Date and Checkin Date
    Then User validate the status of book added with Blank Checkout Date and Checkin Date
    
    # Comment :this must be failed because user cannot book hotels with blank Checkout Date and Checkin Date
    
    
    Scenario: Users are able to add booking of hotels with Blank details of Both First and Last Name
	
	Given User enter the URL of adding book
	When User enter the details of book with Blank details of Both First and Last Name
    Then User validate the status of book added with Blank details of Both First and Last Name
    
      # Comment :this must be failed because user cannot book hotels with Blank details of Both First and Last Name
    
    
    Scenario: Users are able to add booking of hotels With Negative money value
	
	Given User enter the URL of adding book
	When User enter the details of book With Negative money value
    Then User validate the status of book added With Negative money value
    
# Comment :this must be failed because user cannot book hotels With Negative money value