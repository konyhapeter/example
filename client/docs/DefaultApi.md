# DefaultApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteProduct**](DefaultApi.md#deleteProduct) | **DELETE** /products/{id} | 

<a name="deleteProduct"></a>
# **deleteProduct**
> deleteProduct()



Deletes a product by id

### Example
```java
// Import classes:
//import productservice.client.ApiException;
//import productservice.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.deleteProduct();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteProduct");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

