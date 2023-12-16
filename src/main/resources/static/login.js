function register(){
    document.querySelector("#loginForm").setAttribute("action", "/auth/register");
    document.querySelector("#loginForm").setAttribute("method", "get");
    document.querySelector("#loginForm").submit();
}