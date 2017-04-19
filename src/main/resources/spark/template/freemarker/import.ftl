<!DOCTYPE html>
<html>
<body>

<a href="https://cloud.merchantos.com/oauth/authorize.php?response_type=code&client_id=Dev-Roshan&scope=employee:inventory_read">Click to authorize the application for lightspeed before importing</a><br><br>
<a href="https://connect.squareup.com/oauth2/authorize?client_id=sq0idp-Ux0S-9iMftQuozTkDpSjDw">Click to authorize the application for square before importing</a><br><br>

<button onclick="myImport()">Import</button>

<script>
  function myImport() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:5000/import/" + SqToken , false);
    xmlHttp.send(null);
  }
</script>

</body>
</html>