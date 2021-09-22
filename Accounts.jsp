<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accounts</title>
</head>
 <body>
        <h2>Accounts</h2> 
        <form action="bank" method="post">
        <table border="1">
            <thead>
                <tr>
                	<th></th>
                    <th>Customer Id</th>
                    <th>Account Number</th>
                    <th>Balance</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${accounts}" var="account">
                <tr>
                	<td><input type="checkbox"name="accountNumber" value="${account.getAccountNumber()}">
          		   	<input type="hidden" name="customerId" value="${account.getCustomerId()}">
                	</td>
                    <td>${account.customerId}</td>
                    <td>${account.accountNumber}</td>
                    <td>${account.balance}</td>
                </tr>
                </c:forEach>   
            </tbody>
            </table>
      
            <button type="submit"name="path" value="DeleteAccount" > Delete Account</button>
            <button type="reset" >Reset </button>
            </form>
             <form action="bank"method="post"><button type="submit"name="path" value="AddAccount" >Add Account</button></form>
            </body>
</html>