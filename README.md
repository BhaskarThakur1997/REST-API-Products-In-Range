# REST-API-Products-In-Range

A REST API contains information about items in an inventory. Given the product category, minimum price, and maximum price parameters, the goal is to use the API to get the total number of products that belong to this category and have a price in the given range, inclusive. The API returns paginated data.

To access the inventory information, perform an HTTP GET request to: https://jsonmock.hackerrank.com/api/inventory?category=<category>&page=<pageNumber> where <category> is the category to get products for and <pageNumber> is an integer that denotes the page of the results to return.

For example, a GET request to https://jsonmock.hackerrank.com/api/inventory?category=Accessories&page=2 returns data associated with category 'Accessories, and on the second page of the results.

Similarly, a GET request to https://jsonmock.hackerrank.com/api/inventory?category=Tops&page=1 returns data associated with category Tops', and on the first page of the results.

The response to such a request is a JSON with the following 5 fields:

- page: The current page of the results.
- per_page: The maximum number of items returned per page.
- total: The total number of items in the inventory.
- total pages: The total number of pages with results.
- data: Either an empty array or an array of item records (Array of objects). Each item record has the following schema:
   - barcode: a string that denotes the barcode of the item
   - item a string that denotes the name of the item
   - category: a string that denotes the category of the item queried
   - price: a float that denotes the price of the item
   - discount: a float that denotes the discount on the item
   - available: an integer that denotes the number of units of the item available in the inventory (0 denotes unavailable, 1 denotes available)

Below is an example of an item record:

{
"barcode":"74600848", 
"item":"Chemise", 
"category":"Accessories", 
"price":898.0, 
"discount":13.1, 
"available":1
}

Given a string category, numerical minimum price minPrice, numerical maximum price maxPrice, return the total number of items that belong to the category with a price greater than or equal to minPrice and price less than or equal to maxPrice
