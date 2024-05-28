
# Book Rental System

Book Rental System is an application which lets you manage the book Rental process.It has various Http endpoints to register, fetch, update and delete User, Books from the system.




## API Reference

#### Get all students

```http
  GET /learning-navigator/api/v1/books
```

#### Get book by id
```http
  GET /learning-navigator/api/v1/students/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of item to fetch |

#### Create a new Student

```http
  POST /learning-navigator/api/v1/students
```
Request Body
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. name of Student |


Takes name and create a new Student with auto-generated id
#### Public Postman collection to test API
https://www.postman.com/mission-saganist-78647024/workspace/coding-contest-apis/request/19091992-72789163-77cb-4806-8fd9-88f791555f6e



## Deployment

To run this project run

```bash
  ./gradlew bootrun
```

