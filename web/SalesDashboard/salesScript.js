const hamBurger = document.querySelector(".toggle-btn");

hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});

document.getElementById('logoutClickableText').addEventListener('click', function() {
    event.preventDefault();
    document.getElementById('logoutForm').submit();
    console.log('Logout form submitted');
});
