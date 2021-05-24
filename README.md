### FirstLineSoftware Rybalkin A.N.


Required: 
* internet connection
* Java 11 jdk-11.0.10.9-hotspot
* Maven 3.5.4
* Allure commandline 2.13.5
* Firefox 88.0.1

Run test:  
```mvn clean test```  
Run report:  
```allure serve path_to_allure-results```

### TASK 2 A, B, C Solutions
a. Create a test plan for validating the API of retrieving users with consideration of  Pagination  Filtering  Sorting 
* Check pagination limits;
* Check pagination contract and payload key-values;
* Complete filter by every payload key;
* Sort ascend by every payload key;
* Sort descend by every payload key;

b. Create a test plan before marking the feature as completed in terms of regular story in the team sprint 
* define entry and exit criterias
* assure that entry criteria are passed
* spec testing
* static analyze is passed and unit test coverage in enough
* smoke testing
* api testing
* end-2-end scenario
* test report
* assure that exit criteria are passed

c. Create a test plan to validate the new system and its compatibility to an already existing product before deploying to production
* define entry and exit criterias
* assure that entry criteria are passed
* spec testing
* smoke testing
* contract testing

---

### Instructions:  
Below are two tasks that you need to fulfil and share your code and notes with us. Please see each task for specific requirements. You should deliver either via a zip file or a Github private repository. You can use Selenium and Java/Python. We will evaluate your answers based on code performance, organization, and accuracy of the tasks.

Task 1: 
Scenario to automate (Selenium) 
Go to https://www.amazon.de/; 
create a new user;
login with this; 
Search for “Drucker”; 
Select the first product from list and add to basket; 
Go back to search result; 
Select the 2nd product from list and add to basket; 
Go to shopping basket 
1. Validate added items are visible on “Shopping basket” list  
2. Validate that user able to save the product for later purchase by clicking on “Save for  later”  button.
3. Validate that user can move the product back to basket by clicking on “Move to Basket”
Demonstrate: Framework development 

Task 2: 
Sample API tests: From: https://reqres.in/ 
1. GET list of all users 
2. GET first names of all users on page 2 
3. Create a new user with the name "Arnold" and Job as "Dev" and print new users details name and ID
4. Update the user's name to "Sam" 
5. Delete the user 
a. Create a test plan for validating the API of retrieving users with consideration of  Pagination  Filtering  Sorting 
b. Create a test plan before marking the feature as completed in terms of regular story in the team sprint 
c. Create a test plan to validate the new system and its compatibility to an already existing product before deploying to production
