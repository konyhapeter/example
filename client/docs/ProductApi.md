# ProductApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteProduct**](ProductApi.md#deleteProduct) | **DELETE** /products/{id} | 
[**listProducts**](ProductApi.md#listProducts) | **GET** /products | 

<a name="deleteProduct"></a>
# **deleteProduct**
> deleteProduct(id)



Deletes a product by id

### Example
```java
// Import classes:
//import productservice.client.ApiException;
//import productservice.api.ProductApi;


ProductApi apiInstance = new ProductApi();
Long id = 56L; // Long | the id of the product to delete from the database
try {
    apiInstance.deleteProduct(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductApi#deleteProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Long**](.md)| the id of the product to delete from the database |

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
//import productservice.api.ProductApi;


ProductApi apiInstance = new ProductApi();
try {
    List<Product> result = apiInstance.listProducts();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductApi#listProducts");
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

