<#include "base.ftl"/>

<#macro content>
    <h1 align="center">Edit product</h1>
    <form action="/product/edit" method="POST">
        <div class="mb-3">
            <label for="name" class="form-label">ID</label>
            <input type="text" class="form-control" name="id" value=${product.id} readonly>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" value="${product.name}">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" class="form-control" name="price" value=${product.price}>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Creation date</label>
            <input type="text" class="form-control" name="creationDate" value=${product.creationDate} readonly>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" name="description" value="${product.description}">
        </div>

        <button class="btn btn-outline-primary">Update</button>
        <button type="button" name="id" class="btn btn-outline-danger" onclick="window.location.href='/';">
            Cancel
        </button>
    </form>
</#macro>

<@base/>