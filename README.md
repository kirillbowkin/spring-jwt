# Jwt Rest API
https://spring-jwt-application.herokuapp.com

API provide access to plants database, every plant has subsequent characteristics:

+ Id
+ Common name
+ Family
+ Scientific name

**Plant example:**

```json
{
        "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
        "commonName": "Saunders' Widelip Orchid",
        "family": "Orchidaceae",
        "scientificName": "Liparis saundersiana Rchb. f."
}
``` 

### Authentication

Since this api implements jwt, all requests must be authorized which means that with any request you must specify jwt token in corresponded header field,
in order to get token you'll need to make post request to authentication endpoint,
in request body you must provide your credentials, for simplicity this api has no registration option, only user with *ADMIN* role can add one's,
hence you only able to use existing users now, in further you can populate user database using *ADMIN* user.

**Authentication endpoint**  `https://spring-jwt-application.herokuapp.com/api/v1/auth/login`

**POST example:**

```json
{
  "username" : "kirill",
  "password" : "kirill"
}
```

If credentials are valid response will be the following:
```json
{
    "username": "kirill",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzMjk1NCwiZXhwIjoxNTk4MTM2NTU0fQ.umny0VKvT3F2ZUhraH6TNgQ6DJ7YVNB_CHDZM0UQM0M"
}
```

In case credentials are not valid you'll get `400 BAD_REQUEST` in response.

---
#### Users
username | password | roles
--- | --- | :---
kirill|kirill|ADMIN, USER
userone|userone|USER

>USER role gives access only to plants endpoint, there you can fetch either all plants in database or specific one by its id.

>ADMIN role gives access to plants endpoint, and allows CRUD operations there, it also gives authority to manipulate users at a related endpoint.

---
## Requests

Once you've authenticated now you are able to make authorized requests with your jwt token, so you'd better not to lose it, for if you'll try to make any request without providing a token, you'll get `403 Forbidden` in response.

In header value you must provide following sequence: `Bearer_` + `your_token`,
for instance if your token was `ABCDE`
the final value that you must specify in the key of a header is `Bearer_ABCDE`
#### USER requests:

method | url|description
---|:---:|:---:
GET|https://spring-jwt-application.herokuapp.com/api/v1/plants|fetches all plants
GET|https://spring-jwt-application.herokuapp.com/api/v1/plants/{id}|fetches a specific plant by id

#### Examples:

#### ```GET https://spring-jwt-application.herokuapp.com/api/v1/plants```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Response`
```json
[   
    {
         "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
         "commonName": "Saunders' Widelip Orchid",
         "family": "Orchidaceae",
         "scientificName": "Liparis saundersiana Rchb. f."
     },
     {
         "id": "3216209d-4d1c-45f6-8df9-c732c86d1756",
         "commonName": "Hyssopleaf Thoroughwort",
         "family": "Asteraceae",
         "scientificName": "Eupatorium hyssopifolium L. var. calcaratum Fernald & B.G. Schub."
     },
     {
         "id": "83b3d7b3-ceb7-43be-a40b-5296f27fd0c7",
         "commonName": "Woody Rockcress",
         "family": "Brassicaceae",
         "scientificName": "Arabis suffrutescens S. Watson"
     }
]
  

```
---
#### ```GET https://spring-jwt-application.herokuapp.com/api/v1/plants/bd464194-37aa-4319-95d6-aba5ef933ffa```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE
#### `Response`
```json
{
        "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
        "commonName": "Saunders' Widelip Orchid",
        "family": "Orchidaceae",
        "scientificName": "Liparis saundersiana Rchb. f."
}
```

---
#### ADMIN requests:
+ **Plants requests**

method | url|description
---|:---:|:---:
GET|https://spring-jwt-application.herokuapp.com/api/v1/plants|fetches all plants
GET|https://spring-jwt-application.herokuapp.com/api/v1/plants/{id}|fetches a specific plant by id
POST|https://spring-jwt-application.herokuapp.com/api/v1/plants|add a new plant
PUT|https://spring-jwt-application.herokuapp.com/api/v1/plants|Update existing plant
DELETE|https://spring-jwt-application.herokuapp.com/api/v1/plants/{id}|Delete existing plant by its id

#### Examples:

#### ```GET https://spring-jwt-application.herokuapp.com/api/v1/plants/```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Response`
#### `Status: 200 OK`

```json
[   
    {
         "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
         "commonName": "Saunders' Widelip Orchid",
         "family": "Orchidaceae",
         "scientificName": "Liparis saundersiana Rchb. f."
     },
     {
         "id": "3216209d-4d1c-45f6-8df9-c732c86d1756",
         "commonName": "Hyssopleaf Thoroughwort",
         "family": "Asteraceae",
         "scientificName": "Eupatorium hyssopifolium L. var. calcaratum Fernald & B.G. Schub."
     },
     {
         "id": "83b3d7b3-ceb7-43be-a40b-5296f27fd0c7",
         "commonName": "Woody Rockcress",
         "family": "Brassicaceae",
         "scientificName": "Arabis suffrutescens S. Watson"
     },
    
     

]
  

```
---
#### ```GET https://spring-jwt-application.herokuapp.com/api/v1/plants/bd464194-37aa-4319-95d6-aba5ef933ffa```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE
#### `Response`
#### `Status: 200 OK`

```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "commonName": "Saunders' Widelip Orchid",
    "family": "Orchidaceae",
    "scientificName": "Liparis saundersiana Rchb. f."
}
```
---
#### ```POST https://spring-jwt-application.herokuapp.com/api/v1/plants/```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Body`
```json
{
    "commonName" : "new common name",
    "family" : "new family",
    "scientificName" : "new scientific name"
}
```

#### `Response`
#### `Status: 200 OK`

```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "commonName" : "new common name",
    "family" : "new family",
    "scientificName" : "new scientific name"
}
```

---
#### ```PUT https://spring-jwt-application.herokuapp.com/api/v1/plants/```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Body`
```json
{
    "id" : "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "commonName" : "new common name",
    "family" : "new family",
    "scientificName" : "new scientific name"
}
```

#### `Response`
#### `Status: 200 OK`

```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "commonName" : "new common name",
    "family" : "new family",
    "scientificName" : "new scientific name"
}
```
---

#### ```DELETE https://spring-jwt-application.herokuapp.com/api/v1/plants/bd464194-37aa-4319-95d6-aba5ef933ffa```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE


#### `Response`
#### `Status: 200 OK`
```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "commonName" : "new common name",
    "family" : "new family",
    "scientificName" : "new scientific name"
}
```
---

+ **User requests**

method | url|description
---|:---:|:---:
GET|https://spring-jwt-application.herokuapp.com/api/v1/admin/users|fetches all users
GET|https://spring-jwt-application.herokuapp.com/api/v1/admin/users/{id}|fetches a specific user by id
POST|https://spring-jwt-application.herokuapp.com/api/v1/admin/users|add a new user
PUT|https://spring-jwt-application.herokuapp.com/api/v1/admin/users|Update existing user
DELETE|https://spring-jwt-application.herokuapp.com/api/v1/admin/users/{id}|Delete existing user by its id

####Examples:

---
#### ```GET https://spring-jwt-application.herokuapp.com/api/v1/users/```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Response`
#### `Status: 200 OK`

```json
[
    {
        "id": "6d3ddc48-3b95-474a-9595-55319b9ff85d",
        "username": "kirill",
        "firstName": "kirill",
        "lastName": "shishkin",
        "email": "kirill@gmail.com"
    },
    {
        "id": "33cbbda7-364a-4c0c-90ba-c788e18a56ce",
        "username": "userone",
        "firstName": "test",
        "lastName": "test",
        "email": "userone@gmail.com"
    }
]
  

```
---
#### ```GET https://spring-jwt-application.herokuapp.com/api/v1/users/6d3ddc48-3b95-474a-9595-55319b9ff85d```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE
#### `Response`
#### `Status: 200 OK`

```json
{
        "id": "6d3ddc48-3b95-474a-9595-55319b9ff85d",
        "username": "kirill",
        "firstName": "kirill",
        "lastName": "shishkin",
        "email": "kirill@gmail.com"
}
```
---
#### ```POST https://spring-jwt-application.herokuapp.com/api/v1/users/```
####` Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Body`
```json
{
        "username": "newuser",
        "firstName": "newuser",
        "lastName": "newuser",
        "email": "newuser@gmail.com"
}
```

#### `Response`
#### `Status: 200 OK`

```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "username": "newuser",
    "firstName": "newuser",
    "lastName": "newuser",
    "email": "newuser@gmail.com"
}
```

---
#### ```PUT https://spring-jwt-application.herokuapp.com/api/v1/users/```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE

#### `Body`
```json
{
    "username": "newuser",
    "firstName": "newuser",
    "lastName": "newuser",
    "email": "newuser@gmail.com"
}
```

#### `Response`
#### `Status: 200 OK`

```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "username": "newuser",
    "firstName": "newuser",
    "lastName": "newuser",
    "email": "newuser@gmail.com"
}
```
---

#### ```DELETE https://spring-jwt-application.herokuapp.com/api/v1/users/bd464194-37aa-4319-95d6-aba5ef933ffa```
#### `Headers`

key|value
---|:---
Authorization|Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraXJpbGwiLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImlhdCI6MTU5ODEzNDI5NCwiZXhwIjoxNTk4MTM3ODk0fQ.BFZVaflw8H3FrN7VUckBGiIm_iODMSdQX41SAEPWdCE


#### `Response`
#### `Status: 200 OK`
```json
{
    "id": "bd464194-37aa-4319-95d6-aba5ef933ffa",
    "username": "newuser",
    "firstName": "newuser",
    "lastName": "newuser",
    "email": "newuser@gmail.com"
}
```
---







