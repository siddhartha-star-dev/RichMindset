function myfun(){
  let x=5;
  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;
  console.log(email);
  console.log(password);
  if(email === password)
  window.location.href = "home.html";
  else
  window.location.href = "failure.html";
}
