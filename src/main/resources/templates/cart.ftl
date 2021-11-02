<#include "base.ftl"/>

<#macro content>
    <table class="table table-hover">
        <thead>
            <tr>
                <th class="col-lg-1	col-xl-1">Id</th>
                <th class="col-lg-3	col-xl-2">Name</th>
                <th class="col-lg-3	col-xl-2">Price</th>
                <th class="col-lg-3	col-xl-2">Creation date</th>
                <th class="col-lg-3	col-xl-3">Description</th>
                <th class="col-lg-1	col-xl-2"></th>
            </tr>
        </thead>
        <tbody>
            <#if products?has_content>
            <#list products as product>
            <tr>
                <td class="col-lg-1	col-xl-1">${product.id}</td>
                <td class="col-lg-1	col-xl-2">${product.name}</td>
                <td class="col-lg-1	col-xl-2">${product.price?string["0.00"]}</td>
                <td class="col-lg-1	col-xl-2">${product.creationDate}</td>
                <td class="col-lg-1	col-xl-3">${product.description}</td>
                <td class="col-lg-1	col-xl-2">
                    <form action="/cart/remove" method="POST">
                        <button name="id" value=${product.id} type="submit" class="btn btn-outline-danger">
                            <span class="btn-label">
                                <i class="bi bi-bag-x"></i>
                            </span>
                        </button>
                    </form>
                </td>
            </tr>
            </#list>
            </#if>
        </tbody>
    </table>
</#macro>

<@base/>