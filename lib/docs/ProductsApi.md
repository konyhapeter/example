# ProductsApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteProduct**](ProductsApi.md#deleteProduct) | **DELETE** /products/{id} | 
[**listProducts**](ProductsApi.md#listProducts) | **GET** /products | 

<a name="deleteProduct"></a>
# **deleteProduct**
> deleteProduct(id)



Deletes a product by id

### Example
```java
// Import classes:
//import productservice.client.ApiException;
//import productservice.api.ProductsApi;


ProductsApi apiInstance = new ProductsApi();
Long id = 789L; // Long | the id of the product to delete from the database
try {
    apiInstance.deleteProduct(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#deleteProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| the id of the product to delete from the database |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="listProducts"></a>
# **listProducts**
> List&lt;Product&gt; listProducts()



Returns a list of products

### Example
```java
// Import classes:
//import productservice.client.ApiException;
//import productservice.api.ProductsApi;


ProductsApi apiInstance = new ProductsApi();
try {
    List<Product> result = apiInstance.listProducts();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#listProducts");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Product&gt;**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

