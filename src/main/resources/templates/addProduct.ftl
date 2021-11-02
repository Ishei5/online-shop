<#include "base.ftl"/>

<#macro content>
    <h1 align="center">Add new product</h1>
    <form action="/product/add" method="POST">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" class="form-control" name="price">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" name="description">
        </div>
        <button type="submit" class="btn btn-outline-primary">Submit</button>
    </form>
</#macro>

<@base/>