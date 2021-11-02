<#macro base>
    <!DOCTYPE html>
    <html lang="en">
    	<head>
    		<meta charset="UTF-8">
    		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"/>
            <title>Online-shop</title>
    	</head>
    	<body>
    		<div class="container">
    		    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                  <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                          <a class="nav-link active" aria-current="page" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="/product/add">Add product</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="/cart">Cart</a>
                        </li>
                      </ul>
                      <form class="d-flex" action="/products" method="GET">
                        <input class="form-control me-2" name="search" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                      </form>
                    </div>
                  </div>
                </nav>
                <@content/>
    		</div>
    		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"/>
    	</body>
    </html>
</#macro>