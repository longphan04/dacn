function register(){
    document.querySelector("#loginForm").setAttribute("action", "/register");
    document.querySelector("#loginForm").setAttribute("method", "get");
    document.querySelector("#loginForm").submit();
}