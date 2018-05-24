# piTest

## Overview 
Tests are written in Java using Selenium and TestNG
Browser: Mozilla Firefox.
This project tests the following:
* Login 
* Searching products
* Adding products to the cart
* Removing products from the cart
* Checkout process

## Pages
Pages that are tested in this project are:

1. Home page ( https://thepihut.com/) 
2. Search results page ( https://thepihut.com/pages/search-results?q=YOUR_QUERY)
3. Login page (https://thepihut.com/account/login) 
4. Shopping Cart page (https://thepihut.com/cart )
5. Account page (https://thepihut.com/account )
6. Product page ( https://thepihut.com/products/PRODUCT_NAME )
7. Checkout page
8. Shipping page

## Tests
* Add to cart test ( 4 tests )
* Login test ( 2 tests )
* Checkout test 
* Search test ( 3 tests )

# Test flow
## Add to cart

### Initialize empty cart 
Before any test using the cart initialize empty cart.
1. Go to home page
2. Go to cart 
3. Check if shopping cart title is displayed correctly
4. Get number of items in the cart
5. If cart is not empty remove all items from the cart
6. Check if shopping cart is now empty
7. Go back to home page

### Add one item to cart test ( type: positive )
1. Initialize empty cart.
2. Go to home page
3. Click on some product and go to product page
4. Check if product page is displayed correctly
5. Get price of product
6. Add the product to the cart
7. Go to the cart
8. Check if shopping cart title is displayed correctly
9. Get price from shopping cart 
10. Check if prices from shopping cart and product page are the same
11. Check if product counter is now set to 1
12. Go back to home page

### Add multiple same items to cart test ( type: positive ) 
Initialize empty cart. All the steps are the same as the last test except:
5.  Add the product to the cart multiple times 
10.  Check if product counter is now set to number of additions


### Add  multiple different items test ( type: positive )
Before any test using the cart initialize empty cart.
1. Go to home page
2. Click on some product and go to product page
3. Check if product page is displayed correctly
4. Get price of product
5. Add the product to the cart
6. Go back to home page
7. Repeat steps 2 -  5 
8. Go to the cart
9. Check if shopping cart title is displayed correctly
10. Get price from shopping cart 
11. Check if prices from shopping cart and product page are the same
12. Check if product counter is now set to 2
13. Go back to home page

### Remove item from cart test ( type: positive )
Before any test using the cart initialize empty cart.
1. Go to home page
2. Click on some product and go to product page
3. Check if product page is displayed correctly
4. Add the product to the cart
5. Go to the cart
6. Check if shopping cart title is displayed correctly
7. Check if product counter is now set to 2
8. Remove all items from cart 
9. Check if product counter is now set to 2
10. Check if empty cart message is displayed
11. Go back to home page

#Search test

###Search for invalid item test ( type: negative ) 
1. Go to home page
2. Search for some item that doesn't exist 
3. Check if search page is displayed correctly
4. Check if number of results is zero 
5. Check if the result message is displayed correctly

### Price search test  ( type: positive )
1. Go to home page
2. Search for some valid item 
3. Check if search page is opened and text is correctly displayed
4. Get number of results and check if the number is correct
5. Select first price range and  check if price range is correct
6. Check if prices of items displayed are in selected range
7. Check if number of results has changed
8. Go back to home page

### Category search test ( type: positive ) 
1. Go to home page
2. Search for some valid item
3. Check if search page is opened and text is correctly displayed
4. Get the title of some category from sidebar
5. Click on that category
6. Check if title of the page is the same as category from the sidebar
7. Go back to home page

## Login test
Before any test using the login - If logged in then log out

### Positive log in ( type: positive )
1. Go to home page
2. Click on login button and check if login page is opened
3. Sign into account
4. Check if name, logout button and order history title are displayed correctly
5. Click on logout button
6. Check if user is returned to home page

### Invalid credentials login ( type: negative )
1. Go to home page
2. Click on login button and check if login page is opened
3. Sign in with invalid credentials
4. Check if error message is displayed correctly 
5. Return to home page
