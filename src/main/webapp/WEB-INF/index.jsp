<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Products Demo</title>
	<style>
		table {
		    border-collapse: collapse;
		    width: 100%;
		}
		
		td, th {
		    border: 1px solid #dddddd;
		    text-align: left;
		    padding: 8px;
		}
		
		tr:nth-child(even) {
		    background-color: #dddddd;
		}
	</style>
</head>
<body>
	<c:if test="${!empty products}">
	<h2>Products</h2>
	<table>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>id</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>
				<td>${product.id}</td>
				<td><a href="/products/${product.id}" class="link_delete">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	
	<h2>New Product</h2>
	<form id="form_new_product">
		<label for="name">Name: </label>
		<input type="text" id="name" name="name">
		<br><br>
		<label for="description">Description: </label>
		<input type="text" id="description" name="description">
		<br><br>
		<label for="price">Price: </label>
		<input type="text" id="price" name="price">
		<br><br>
		<input type="submit" value="Create New">
	</form>
	
	<h2>Update Product</h2>
	<form id="form_update_product">
		<label for="id">Product ID: </label>
		<input type="text" id="id" name="id">
		<br><br>
		<label for="name_edit">Name: </label>
		<input type="text" id="name_edit" name="name">
		<br><br>
		<label for="description_edit">Description: </label>
		<input type="text" id="description_edit" name="description">
		<br><br>
		<label for="price_edit">Price: </label>
		<input type="text" id="price_edit" name="price">
		<br><br>
		<input type="submit" value="Update">
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$("#form_new_product").submit(function(e) {
			e.preventDefault();
			var data = $(this).serialize().split("&");
			var obj = {};
			for (var key in data) {
				obj[data[key].split("=")[0]] = data[key].split("=")[1];
			}
			for (var key in obj) {
				obj[key] = obj[key].replace(/%20/g, " ");
			}
			$.ajax({
				url: "/products",
				method: "POST",
				data: JSON.stringify(obj),
				contentType: 'application/json',
				success: function(res) {
					location.reload();
				}
			});
		});
		
		$(".link_delete").click(function(e) {
			e.preventDefault();
			$.ajax({
				url: $(this).attr("href"),
				method: "DELETE",
				success: function(res) {
					location.reload();
				}
			});
		});
		
		$("#form_update_product").submit(function(e) {
			e.preventDefault();
			var data = $(this).serialize().split("&");
			var obj = {};
			for (var key in data) {
				obj[data[key].split("=")[0]] = data[key].split("=")[1];
			}
			for (var key in obj) {
				obj[key] = obj[key].replace(/%20/g, " ");
			}
			var id = obj["id"];
			delete obj["id"];
			console.log(obj);
			$.ajax({
				url: "/products/" + id,
				method: "PUT",
				data: JSON.stringify(obj),
				contentType: 'application/json',
				success: function(res) {
					location.reload();
				}
			});
		});
	</script>
</body>
</html>