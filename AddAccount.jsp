<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Account</title>
</head>
<body>
<form action="bank"method="post">
<table>
<tr><td>
 <label for="customerId">Customer Id</label>
</td><td>
<input type="number" id="customerId" name="customerId" placeholder="Enter the customer Id.."required>
</td>
</tr>
<tr><td>
 <label for="amount">Deposit Amount</label>
</td><td>
<input type="number" id="amount" name="amount" placeholder="Enter the amount.."required>
</td>
</tr><tr>
     <td>
                <input type="submit" name="path"value="addAccount">
            </td>
            <td>
                <input type="reset" value="Reset">
            </td>
        </tr></table>

</form>
</body>
</html>