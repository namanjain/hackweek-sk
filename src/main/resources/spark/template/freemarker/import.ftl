<!DOCTYPE html>
<html>
<body>

<a href="https://cloud.merchantos.com/oauth/authorize.php?response_type=code&client_id=Dev-Roshan&scope=employee:inventory_read">Set permission in lightspeed to set access scope first before importing</a><br><br>

Square: <input type="text" id="Square"><br>

<button onclick="myImport()">Import</button>

<script>
  function myImport() {
    var SqToken = document.getElementById("Square").value;
    console.log(SqToken);

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:5000/import/" + SqToken , false);
    xmlHttp.send(null);
  }
</script>

</body>
</html>