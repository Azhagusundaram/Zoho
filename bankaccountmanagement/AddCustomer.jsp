<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Customer</title>
</head>
<body>
<form action="bank"method="post">
<table>
<tr><td>
 <label for="name">Name</label>
</td><td>
<input type="text" id="name" name="name" placeholder="Enter the name.."required>
</td>
</tr>
<tr><td>
 <label for="emailid">Email Id</label>
</td><td>
<input type="text" id="emailid" name="emailid" placeholder="Enter the emailid.."required>
</td>
</tr>
<tr><td>
 <label for="phone">Phone Number</label>
</td><td>
<input type="number" id="phone" name="phone" placeholder="Enter the Phone Number.."required>
</td>
</tr>
<tr><td>

 <label for="amount">Deposit Amount</label>
</td><td>
<input type="number" id="amount" name="amount" placeholder="Enter the amount.."required>
</td>
</tr><tr>
     <td>
                <input type="submit"name="path" value="addCustomer">
            </td>
            <td>
                <input type="reset" value="Reset">
            </td>
        </tr></table>

</form>
</body>
</html>