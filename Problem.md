# Problem


You're a successful Shopify merchant with many products in your store! You’d like an easier way of
 browsing your products from your phone based on the “tags” on each product. Create an Android app
 that displays a **Tags List Page** and a **Products List Page**. Your app will need to fetch data
 from the [Shopify Products REST API](https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6).


**Tags List Page:** A simple list of every unique tag that has been added to one of your products
 (e.g. Aerodynamic, Clock, Concrete). Clicking on a tag launches the Products List Page.


**Products List Page:** A list of every product that contains the selected tag. Each row in the list
 needs to contain, at a minimum, the name of the product and the total available inventory across
 all variants of the product.


Note that the [API link](https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6)
 is only for the first page of orders (for this challenge you can ignore any
 additional pages). Here are a couple simple libraries that you can use to fetch the data (these are
 optional, you can pick one or use any other alternative).


Android:
[http://square.github.io/retrofit/](http://square.github.io/retrofit/)
[http://square.github.io/okhttp/](http://square.github.io/okhttp/)


## Extra
Feeling adventurous? Show the product image for each row of the **Products List Page**




## What you need to submit:
1. A screenshot of your app showing the **Tags List Page**
2. A screenshot of your app showing the **Products List Page** after clicking on “Aerodynamic” from the
 Tags List Page
3. Your project code.
