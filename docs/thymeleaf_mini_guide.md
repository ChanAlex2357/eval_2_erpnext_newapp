# Thymeleaf Templating Guide for Your Spring Boot Application

Thymeleaf is a powerful server-side Java template engine that works well with Spring Boot. Here's how to implement templating in your application:

## 1. Basic Template Structure

Create a `layout.html` file in `src/main/resources/templates`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title th:text="${pageTitle} + ' | Newapp'">Newapp</title>
    <link rel="stylesheet" th:href="@{/css/main.css}">
    <link rel="icon" th:href="@{/favicon.jpeg}" type="image/jpeg">
</head>
<body>
    <!-- Header Fragment -->
    <header th:replace="~{fragments/header :: header}"></header>
    
    <!-- Main Content Section -->
    <main class="main-content">
        <div layout:fragment="content"></div>
    </main>
    
    <!-- Footer Fragment -->
    <footer th:replace="~{fragments/footer :: footer}"></footer>
</body>
</html>
```

## 2. Create Fragments

In `src/main/resources/templates/fragments`:

### header.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <header class="header" th:fragment="header">
        <div class="header-content">
            <a th:href="@{/}" class="logo">Newapp</a>
            <nav class="nav">
                <a th:href="@{/about}">About</a>
                <a th:href="@{/contact}">Contact</a>
            </nav>
        </div>
    </header>
</body>
</html>
```

### footer.html
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <footer class="footer" th:fragment="footer">
        <div class="footer-content">
            <p class="copyright">© 2023 Newapp. Tous droits réservés.</p>
        </div>
    </footer>
</body>
</html>
```

## 3. Create Your Login Page

`login.html` in `src/main/resources/templates`:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{layout}">
<head>
    <title>Connexion</title>
</head>
<body>
    <div layout:fragment="content">
        <div class="login-container">
            <h1>Bienvenue dans Newapp</h1>
            <form th:action="@{/login}" method="post">
                <div class="form-group">
                    <label for="username">Nom d'utilisateur</label>
                    <input type="text" name="username" id="username" 
                           placeholder="Entrez votre nom d'utilisateur" required>
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" name="password" id="password" 
                           placeholder="Entrez votre mot de passe" required>
                </div>
                <div class="form-group">
                    <button type="submit">Se connecter</button>
                </div>
                <hr>
                <div class="form-group">
                    <p>
                        Vous n'avez pas encore de compte?
                        <a th:href="@{/register}">Créer un compte</a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

## 4. Controller Setup

Create a `LoginController.java`:

```java
@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("pageTitle", "Connexion");
        return "login";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, 
                             @RequestParam String password) {
        // Your login logic here
        return "redirect:/dashboard";
    }
    
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("pageTitle", "Créer un compte");
        return "register";
    }
}
```

## 5. Advanced Thymeleaf Features

### Conditional Rendering
```html
<div th:if="${user != null}">
    Bienvenue, <span th:text="${user.name}"></span>!
</div>
<div th:unless="${user != null}">
    <a th:href="@{/login}">Se connecter</a>
</div>
```

### Iteration
```html
<ul>
    <li th:each="item : ${items}" th:text="${item.name}"></li>
</ul>
```

### URL Handling
```html
<a th:href="@{/user/{id}(id=${userId})}">View Profile</a>
```

### Internationalization
1. Create `messages.properties` and `messages_fr.properties` in `src/main/resources`
2. In HTML:
```html
<h1 th:text="#{welcome.message}">Welcome</h1>
```

## 6. Form Handling with Thymeleaf

For registration page (`register.html`):

```html
<form th:action="@{/register}" th:object="${user}" method="post">
    <div class="form-group">
        <label th:text="#{username}">Username</label>
        <input type="text" th:field="*{username}" class="form-control">
        <small th:if="${#fields.hasErrors('username')}" 
               th:errors="*{username}" class="text-danger"></small>
    </div>
    <!-- Other form fields -->
</form>
```

## 7. Security Integration

For Spring Security integration:

```html
<div sec:authorize="isAuthenticated()">
    Welcome, <span sec:authentication="name"></span>!
    <form th:action="@{/logout}" method="post">
        <button type="submit">Logout</button>
    </form>
</div>
<div sec:authorize="!isAuthenticated()">
    <a th:href="@{/login}">Login</a>
</div>
```

## 8. Configuration

Add to `application.properties`:
```properties
# Thymeleaf properties
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
```

## Best Practices

1. Keep templates focused on presentation only
2. Move reusable components to fragments
3. Use layout dialect for consistent page structure
4. Externalize strings for internationalization
5. Prefer Thymeleaf attributes over inline expressions
6. Use proper model attributes from controllers
7. Leverage Thymeleaf's utility objects (#dates, #strings, etc.)

This setup gives you a clean, maintainable structure for your Spring Boot application with Thymeleaf templating, following the minimalist design principles you requested.