# My registration form

- [x] Spring Boot
- [x] Spring Security
- [x] Java Mail
- [x] Email verification with expiry


## Main page(login page)
<img src="src/main/resources/static/screenshots/login.png"/>


## Registration page
<img src="src/main/resources/static/screenshots/example.png"/>


## Page with request confirm your email address
<img src="src/main/resources/static/screenshots/confirm.png"/>


## Email verification link with expiry
<img src="src/main/resources/static/screenshots/maildev.png"/>


## User account page
<img src="src/main/resources/static/screenshots/userAccount.png"/>


## Example request
<img src="src/main/resources/static/screenshots/registration.png"/>

### CURL
```
curl --location --request POST 'localhost:8080/api/v1/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Black",
    "lastName": "Montana",
    "email": "montana@gmail.com",
    "phoneNumber": "11111111",
    "password": "123password",
    "confirmPassword": "123password"
}'
```