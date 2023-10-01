# E-commerce App API Backend

## Application Tier
- **Language**: Java
- **Framework**: Spring Boot

## Database Tier
- **Database**: MySQL

## Cart Controller

### Add Item to Cart
- **Endpoint**: POST /api/v1/cart/add
- **Description**: This endpoint allows users to add an item to their cart.
- **Request Body**:
  - Item details (e.g., productId, quantity)
- **Response**: Returns the updated cart with the added item.

### Delete Item from Cart
- **Endpoint**: DELETE /api/v1/cart/delete/{cartItemId}
- **Description**: This endpoint enables users to delete an item from their cart.
- **Path Parameter**:
  - cartItemId: ID of the item to be deleted
- **Response**: Returns the updated cart after removing the item.

### Get All Cart Items
- **Endpoint**: GET /api/v1/cart/getItem
- **Description**: Retrieves a list of all items in the user's cart.
- **Response**: Returns a list of cart items.

## Category Controller

### Get All Categories
- **Endpoint**: GET /api/v1/category/categoryList
- **Description**: This endpoint retrieves a list of all available categories.
- **Response**: Returns a list of categories.

### Create Category
- **Endpoint**: POST /api/v1/category/create
- **Description**: This endpoint allows the creation of a new category.
- **Request Body**:
  - Category details (e.g., name, description)
- **Response**: Returns the created category.

### Update Category
- **Endpoint**: PUT /api/v1/category/update/{categoryId}
- **Description**: This endpoint updates an existing category.
- **Path Parameter**:
  - categoryId: ID of the category to be updated
- **Request Body**:
  - Updated category details (e.g., name, description)
- **Response**: Returns the updated category.

## Product Controller

### Create Product
- **Endpoint**: POST /api/v1/product/addProduct
- **Description**: This endpoint creates a new product.
- **Request Body**:
  - Product details (e.g., name, price, categoryId)
- **Response**: Returns the created product.

### Get All Products
- **Endpoint**: GET /api/v1/product/getAllProduct
- **Description**: Retrieves a list of all available products.
- **Response**: Returns a list of products.

### Update Product
- **Endpoint**: PUT /api/v1/product/updateProduct/{productId}
- **Description**: This endpoint updates an existing product.
- **Path Parameter**:
  - productId: ID of the product to be updated
- **Request Body**:
  - Updated product details (e.g., name, price, categoryId)
- **Response**: Returns the updated product.

## Authentication

### User Controller

#### Sign In
- **Endpoint**: POST /api/v1/user/signin
- **Description**: Authenticates a user and generates an access token.
- **Request Body**:
  - User credentials (e.g., username, password)
- **Response**: Returns the access token.

#### Sign Up
- **Endpoint**: POST /api/v1/user/signup
- **Description**: Registers a new user.
- **Request Body**:
  - User details (e.g., username, email, password)
- **Response**: Returns the created user.

## Wishlist Controller

### Add Item to Wishlist
- **Endpoint**: POST /api/v1/wishlist/addToWishList
- **Description**: Adds an item to the user's wishlist.
- **Request Body**:
  - Item details (e.g., productId, userId)
- **Response**: Returns the updated wishlist.

### Get Wishlist Items
- **Endpoint**: GET /api/v1/wishlist/getWishListItem/{token}
- **Description**: Retrieves the user's wishlist items.
- **Path Parameter**:
  - token: User's access token
- **Response**: Returns a list of wishlist items.
