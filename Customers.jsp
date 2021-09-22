<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customers</title>
</head>
 <body>
        <h2>Customers</h2> 
        <form action="bank" method="post">
        <table border="1">
            <thead>
                <tr>
                	<th></th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>PhoneNumber</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${customers}" var="customer">
                <tr>
                <td> <input type="checkbox" name="customerId" value="${customer.customerId}"></td>
                    <td>${customer.getCustomerId()}</td>
                    <td>${customer.name}</td>
                    <td>${customer.address}</td>
                    <td>${customer.phoneNumber}</td>
                </tr>
                </c:forEach>   
            </tbody>
            </table>
           
            <button type="submit" name="path" value="DeleteCustomer"> Delete Customer</button>
            <button type="reset" >Reset </button>
            </form>
             <form action="bank"method="post"><button type="submit" name="path" value="AddCustomer">Add Customer</button></form>
            </body>
</html>