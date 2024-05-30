# notesapp
note app exam

* Gradle 8.7
* Java 11
* spring boot 2.7.5
* Swagger

# build project
./gradlew build

# run project
./gradlew bootRun

# Accessing api docs
http://localhost:8080/swagger-ui/index.html

# test build
./gradlew test

# test client
* for GUI use postman

* Using command line with curl
    * create note
    > curl -X 'POST' \
    'http://localhost:8080/notes' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '{
    "id": 2,
    "title": "my title",
    "body": "your body"
    }'
  
    * get note by id
    > curl -X 'GET' \
  'http://localhost:8080/notes/1' \
  -H 'accept: */*'
  
   * get all notes
    >curl -X 'GET' \
  'http://localhost:8080/notes' \
  -H 'accept: */*'
  
   * update notes
    > curl -X 'PUT' \
  'http://localhost:8080/notes/1' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "title": "updated title",
  "body": "updated body"
  }'
  
  * delete note
  > curl -X 'DELETE' \
    'http://localhost:8080/notes/1' \
    -H 'accept: */*'
